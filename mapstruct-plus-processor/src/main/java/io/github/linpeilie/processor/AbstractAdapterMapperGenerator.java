package io.github.linpeilie.processor;

import cn.hutool.core.collection.ListUtil;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;

import static javax.tools.Diagnostic.Kind.ERROR;

public abstract class AbstractAdapterMapperGenerator {

    public void write(ProcessingEnvironment processingEnv,
        Collection<AbstractAdapterMethodMetadata> adapterMethods,
        String adapterClassName) {
        // write Adapter
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(adapterPackage() + "." + adapterClassName)
            .openWriter()) {
            JavaFile.builder(adapterPackage(), createTypeSpec(adapterMethods, adapterClassName))
                .build()
                .writeTo(writer);
        } catch (IOException e) {
            processingEnv.getMessager()
                .printMessage(ERROR, "Error while opening " + adapterClassName + " output file: " + e.getMessage());
        } catch (Exception e) {
            processingEnv.getMessager()
                .printMessage(ERROR, "Error while generate adapter mapper " + adapterClassName + " output file: " + e.getMessage());
        }
    }

    protected abstract TypeSpec createTypeSpec(Collection<AbstractAdapterMethodMetadata> adapterMethods,
        String adapterClassName);

    protected String adapterPackage() {
        return AutoMapperProperties.getAdapterPackage();
    }

    private TypeName wrapperTypeName(TypeName source) {
        if (source.isPrimitive() || source.isBoxedPrimitive()) {
            return source;
        }
        if ("java.util.Map".contentEquals(source.toString())) {
            return ParameterizedTypeName.get((ClassName) source,
                ClassName.get("java.lang", "String"),
                ClassName.get("java.lang", "Object"));
        }
        return source;
    }

    protected List<MethodSpec> buildProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        CodeBlock targetCode = adapterMethodMetadata.isStatic()
                               ? CodeBlock.of("return $T.$N($N);",
            adapterMethodMetadata.getMapper(), adapterMethodMetadata.getMapperMethodName(), "param")
                               : proxyMethodTarget(adapterMethodMetadata);
        ParameterSpec parameterSpec = ParameterSpec
            .builder(wrapperTypeName(adapterMethodMetadata.getSource()), "param")
            .build();

        final MethodSpec methodSpec = MethodSpec.methodBuilder(adapterMethodMetadata.getMethodName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .returns(wrapperTypeName(adapterMethodMetadata.getReturn()))
            .addCode(targetCode)
            .build();
        if (!"java.util.Map".contentEquals(adapterMethodMetadata.getSource().toString())
            && !"java.util.Map".contentEquals(adapterMethodMetadata.getReturn().toString())) {
            return ListUtil.toList(methodSpec);
        }
        // array
        CodeBlock arrayTargetCode = CodeBlock.of("if($N == null) return new $T(); return $N.stream().map(this::$N).collect($T.toList());",
            "param",
            ClassName.get("java.util", "ArrayList"),
            "param",
            adapterMethodMetadata.getMethodName(),
            ClassName.get("java.util.stream", "Collectors"));
        ParameterizedTypeName arrayParameter = ParameterizedTypeName.get(ClassName.get("java.util", "List"),
            wrapperTypeName(adapterMethodMetadata.getSource()));
        ParameterSpec arrayParameterSpec = ParameterSpec.builder(arrayParameter, "param").build();

        ParameterizedTypeName arrayResult = ParameterizedTypeName.get(ClassName.get("java.util", "List"),
            wrapperTypeName(adapterMethodMetadata.getReturn()));

        final MethodSpec arrayMethodSpec = MethodSpec.methodBuilder(adapterMethodMetadata.getMethodName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(arrayParameterSpec)
            .returns(arrayResult)
            .addCode(arrayTargetCode)
            .build();
        return ListUtil.toList(methodSpec, arrayMethodSpec);
    }

    protected abstract CodeBlock proxyMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata);

}
