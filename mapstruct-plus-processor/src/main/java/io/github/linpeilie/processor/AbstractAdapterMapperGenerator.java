package io.github.linpeilie.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import io.github.linpeilie.processor.metadata.AdapterMapMethodMetadata;
import io.github.linpeilie.processor.metadata.AdapterMethodMetadata;
import io.github.linpeilie.processor.utils.ClassUtil;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;

import static javax.tools.Diagnostic.Kind.ERROR;

public abstract class AbstractAdapterMapperGenerator {

    protected static final String PARAM__PARAMETER_NAME = "param";

    protected static final String CONTEXT__PARAMETER_NAME = "context";

    public void write(ProcessingEnvironment processingEnv,
        Collection<AbstractAdapterMethodMetadata> adapterMethods,
        String adapterClassName,
        boolean cycle) {
        write(processingEnv, createAdapterTypeSpec(adapterClassName, adapterMethods, cycle));
    }

    private void write(ProcessingEnvironment processingEnv, TypeSpec typeSpec) {
        if (typeSpec == null) {
            return;
        }
        // write Adapter
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(adapterPackage() + "." + typeSpec.name)
            .openWriter()) {
            JavaFile.builder(adapterPackage(), typeSpec)
                .build()
                .writeTo(writer);
        } catch (IOException e) {
            processingEnv.getMessager()
                .printMessage(ERROR, "Error while opening " + typeSpec.name + " output file: " + e.getMessage());
        }
    }

    protected TypeSpec createAdapterTypeSpec(String adapterClassName,
        Collection<AbstractAdapterMethodMetadata> adapterMethods,
        boolean cycle) {
        List<MethodSpec> methods = adapterMethods.stream()
            .filter(method -> !cycle || method.needCycleAvoiding())
            .map(method -> buildProxyMethod(method, cycle))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        if (methods.isEmpty()) {
            return null;
        }
        return createTypeSpec(methods, adapterClassName,
            cycle ? ClassName.get(adapterPackage(), AutoMapperProperties.getAdapterClassName()) : null);
    }

    protected TypeSpec createTypeSpec(List<MethodSpec> methods, String adapterClassName, ClassName superClass) {
        TypeSpec.Builder adapterBuilder = TypeSpec.classBuilder(ClassName.get(adapterPackage(), adapterClassName))
            .addModifiers(Modifier.PUBLIC);

        if (superClass != null) {
            adapterBuilder.superclass(superClass);
        }

        // adapter methods
        adapterBuilder.addMethods(methods);

        return adapterBuilder.build();
    }

    protected String adapterPackage() {
        return AutoMapperProperties.getAdapterPackage();
    }

    private TypeName wrapperTypeName(TypeName source) {
        if (source.isPrimitive() || source.isBoxedPrimitive()) {
            return source;
        }
        if ("java.util.Map".contentEquals(source.toString())) {
            return ParameterizedTypeName.get((ClassName) source, ClassName.get("java.lang", "String"),
                ClassName.get("java.lang", "Object"));
        }
        return source;
    }

    private List<MethodSpec> buildProxyMethod(AbstractAdapterMethodMetadata adapterMethod, boolean cycle) {
        List<MethodSpec> methodSpecs = new ArrayList<>();
        if (cycle) {
            methodSpecs.addAll(buildCycleAvoidingProxyMethod(adapterMethod));
        } else {
            methodSpecs.addAll(buildDefaultProxyMethod(adapterMethod, null));
        }
        return methodSpecs;
    }

    protected List<MethodSpec> buildDefaultProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata,
        ClassName annotation) {
        List<MethodSpec> methodSpecs = new ArrayList<>();

        ParameterSpec parameterSpec =
            ParameterSpec.builder(wrapperTypeName(adapterMethodMetadata.getSource()), PARAM__PARAMETER_NAME).build();
        MethodSpec methodSpecForSingle =
            buildDefaultProxyMethod(adapterMethodMetadata, parameterSpec, adapterMethodMetadata.getReturn(),
                annotation);
        methodSpecs.add(methodSpecForSingle);

        // 自定义类型转换
        if (adapterMethodMetadata instanceof AdapterMethodMetadata) {
            ParameterSpec listParameter = ParameterSpec.builder(
                ParameterizedTypeName.get(ClassName.get("java.util", "List"), adapterMethodMetadata.getSource()),
                PARAM__PARAMETER_NAME
            ).build();
            MethodSpec methodSpecForList =
                buildDefaultProxyMethod(adapterMethodMetadata, listParameter, ClassName.get("java.util", "List"),
                    annotation);
            methodSpecs.add(methodSpecForList);
        }
        // 自定义类型与 Map 进行转换
        else if (adapterMethodMetadata instanceof AdapterMapMethodMetadata) {
            methodSpecs.add(buildObjConversionProxyMethod(adapterMethodMetadata, annotation));
        }

        return methodSpecs;
    }

    private MethodSpec buildObjConversionProxyMethod(AbstractAdapterMethodMetadata adapterMethod,
        ClassName annotation) {
        CodeBlock code = CodeBlock.builder()
            .add("if($N == null) {\n", PARAM__PARAMETER_NAME)
            .add("    return null;\n")
            .add("}\n")
            .add("if($N instanceof $T) {\n", PARAM__PARAMETER_NAME, ClassName.get("java.util", "Map"))
            .add("    return $N((Map<$T, $T>) $N);\n",
                adapterMethod.getMethodName(), ClassName.get("java.lang", "String"),
                ClassName.get("java.lang", "Object"), PARAM__PARAMETER_NAME)
            .add("}\n")
            .add("return null;\n")
            .build();
        MethodSpec.Builder methodSpecBuilder =
            MethodSpec.methodBuilder("objTo" + ClassUtil.simplifyQualifiedName(adapterMethod.getReturn().toString()))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(
                    ParameterSpec.builder(ClassName.get("java.lang", "Object"), PARAM__PARAMETER_NAME)
                        .build()
                )
                .returns(adapterMethod.getReturn())
                .addCode(code);
        if (annotation != null) {
            methodSpecBuilder.addAnnotation(annotation);
        }
        return methodSpecBuilder.build();
    }

    private MethodSpec buildDefaultProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata,
        ParameterSpec parameterSpec,
        TypeName returns,
        ClassName annotation) {
        CodeBlock targetCode = adapterMethodMetadata.isStatic()
                               ? CodeBlock.of("return $T.$N($N);", adapterMethodMetadata.getMapper(),
            adapterMethodMetadata.getMapperMethodName(), "param")
                               : proxyMethodTarget(adapterMethodMetadata);
        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(adapterMethodMetadata.getMethodName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .returns(returns)
            .addCode(targetCode);
        if (annotation != null) {
            methodSpecBuilder.addAnnotation(annotation);
        }
        return methodSpecBuilder.build();
    }

    protected List<MethodSpec> buildCycleAvoidingProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata) {

        // default impl
        List<MethodSpec> defaultMethods = buildDefaultProxyMethod(adapterMethodMetadata,
            ClassName.get("io.github.linpeilie.annotations", "DoIgnore"));
        List<MethodSpec> methodSpecs = new ArrayList<>(defaultMethods);

        ParameterSpec parameterSpec =
            ParameterSpec.builder(wrapperTypeName(adapterMethodMetadata.getSource()), PARAM__PARAMETER_NAME).build();
        methodSpecs.add(
            buildCycleAvoidingProxyMethod(adapterMethodMetadata, parameterSpec, adapterMethodMetadata.getReturn()));

        if (adapterMethodMetadata instanceof AdapterMethodMetadata) {
            ParameterSpec listParameter = ParameterSpec.builder(
                ParameterizedTypeName.get(ClassName.get("java.util", "List"), adapterMethodMetadata.getSource()),
                "param"
            ).build();
            MethodSpec methodSpecForList =
                buildCycleAvoidingProxyMethod(adapterMethodMetadata, listParameter,
                    ClassName.get("java.util", "List"));
            methodSpecs.add(methodSpecForList);
        }

        return methodSpecs;
    }

    private MethodSpec buildCycleAvoidingProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata,
        ParameterSpec parameterSpec,
        TypeName returns
    ) {
        CodeBlock targetCode = adapterMethodMetadata.isStatic()
                               ? CodeBlock.of("return $T.$N($N, $N);", adapterMethodMetadata.getMapper(),
            adapterMethodMetadata.getMapperMethodName(), "param", CONTEXT__PARAMETER_NAME)
                               : cycleAvoidingMethodTarget(adapterMethodMetadata);
        ParameterSpec contextParameterSpec =
            ParameterSpec.builder(
                    ClassName.get("io.github.linpeilie", "CycleAvoidingMappingContext"),
                    CONTEXT__PARAMETER_NAME)
                .addAnnotation(ClassName.get("org.mapstruct", "Context"))
                .build();
        return MethodSpec.methodBuilder(adapterMethodMetadata.getMethodName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .addParameter(contextParameterSpec)
            .returns(returns)
            .addCode(targetCode)
            .build();
    }

    protected abstract CodeBlock proxyMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata);

    protected abstract CodeBlock cycleAvoidingMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata);

}
