package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.ContextConstants;
import io.github.linpeilie.processor.metadata.AutoMapperMetadata;
import io.github.linpeilie.processor.metadata.AutoMappingMetadata;
import io.github.linpeilie.utils.CollectionUtils;
import io.github.linpeilie.utils.StrUtil;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class AutoMapperGenerator {

    public static final String CONVERT_METHOD_NAME = "convert";

    public void write(AutoMapperMetadata metadata, final ProcessingEnvironment processingEnv, Writer writer) {
        try {
            JavaFile.builder(metadata.mapperPackage(), createTypeSpec(processingEnv, metadata)).build().writeTo(writer);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private TypeSpec createTypeSpec(ProcessingEnvironment processingEnv, AutoMapperMetadata metadata) {
        ParameterizedTypeName converterName =
            ParameterizedTypeName.get(metadata.getSuperClass(), metadata.getSuperGenerics());

        final ClassName targetClassName = metadata.getTargetClassName();

        TypeSpec.Builder builder = TypeSpec.interfaceBuilder(metadata.mapperName())
            .addSuperinterface(converterName)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(buildGeneratedMapperAnnotationSpec(metadata));

        final ParameterSpec source = ParameterSpec.builder(metadata.getSourceClassName(), "source").build();
        final ParameterSpec target = ParameterSpec.builder(targetClassName, "target")
            .addAnnotation(AnnotationSpec.builder(ClassName.get("org.mapstruct", "MappingTarget")).build())
            .build();
        final ParameterSpec context =
            ParameterSpec.builder(ClassName.get("io.github.linpeilie", "CycleAvoidingMappingContext"), "context")
                .addAnnotation(ClassName.get("org.mapstruct", "Context"))
                .build();
        if (metadata.getFieldMappingList() != null && !metadata.getFieldMappingList().isEmpty()) {
            builder.addMethod(addConvertMethodSpec(
                metadata.isCycleAvoiding() ? CollectionUtils.newArrayList(source, context) : Collections.singletonList(source),
                metadata.getFieldMappingList(),
                targetClassName,
                CONVERT_METHOD_NAME));
        }

        boolean targetIsImmutable = classIsImmutable(processingEnv, targetClassName);
        if (targetIsImmutable) {
            builder.addMethod(
                addEmptyConvertMethodForImmutableEntity(
                    metadata.isCycleAvoiding() ? CollectionUtils.newArrayList(source, target,
                        context) : CollectionUtils.newArrayList(source, target),
                    targetClassName,
                    CONVERT_METHOD_NAME));
        } else if (metadata.getFieldMappingList() != null && !metadata.getFieldMappingList().isEmpty()) {
            builder.addMethod(addConvertMethodSpec(
                metadata.isCycleAvoiding() ? CollectionUtils.newArrayList(source, target,
                    context) : CollectionUtils.newArrayList(source, target),
                metadata.getFieldMappingList(),
                targetClassName,
                CONVERT_METHOD_NAME));
        }

        return builder.build();
    }

    private MethodSpec addEmptyConvertMethodForImmutableEntity(List<ParameterSpec> parameterSpecs,
        ClassName targetClassName,
        String methodName) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(methodName)
            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
            .addParameters(parameterSpecs)
            .returns(targetClassName)
            .addCode("return target;");
        return builder.build();
    }

    private boolean classIsImmutable(ProcessingEnvironment processingEnv, ClassName className) {
        final TypeElement targetElement = processingEnv.getElementUtils()
            .getTypeElement(className.reflectionName().replaceAll("\\$", "."));
        final List<? extends AnnotationMirror> annotationMirrors = targetElement.getAnnotationMirrors();
        for (AnnotationMirror annotationMirror : annotationMirrors) {
            if (annotationMirror.getAnnotationType().asElement().getSimpleName().contentEquals("Immutable")) {
                return true;
            }
        }
        return false;
    }

    private MethodSpec addConvertMethodSpec(List<ParameterSpec> parameterSpecs,
        List<AutoMappingMetadata> autoMappingMetadataList,
        ClassName target, String methodName) {
        final MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(methodName)
            .addParameters(parameterSpecs)
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .returns(target);
        if (CollectionUtils.isNotEmpty(autoMappingMetadataList)) {
            methodSpecBuilder.addAnnotations(buildMappingAnnotations(autoMappingMetadataList));
        }
        return methodSpecBuilder.build();
    }

    private List<AnnotationSpec> buildMappingAnnotations(final List<AutoMappingMetadata> autoMappingMetadataList) {
        return autoMappingMetadataList.stream().map(autoMappingMetadata -> {
            final AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassName.get("org.mapstruct", "Mapping"))
                .addMember("target", CodeBlock.builder().add("$S", autoMappingMetadata.getTarget()).build())
                .addMember("ignore", CodeBlock.builder().add(String.valueOf(autoMappingMetadata.isIgnore())).build());
            if (StrUtil.isNotEmpty(autoMappingMetadata.getDateFormat())) {
                builder.addMember("dateFormat",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getDateFormat()).build());
            }
            if (StrUtil.isNotEmpty(autoMappingMetadata.getNumberFormat())) {
                builder.addMember("numberFormat",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getNumberFormat()).build());
            }
            if (StrUtil.isNotEmpty(autoMappingMetadata.getDefaultValue())) {
                builder.addMember("defaultValue",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getDefaultValue()).build());
            }
            if (StrUtil.isNotEmpty(autoMappingMetadata.getExpression())) {
                builder.addMember("expression",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getExpression()).build());
            } else {
                builder.addMember("source", CodeBlock.builder().add("$S", autoMappingMetadata.getSource()).build());
            }
            if (StrUtil.isNotEmpty(autoMappingMetadata.getDefaultExpression())) {
                builder.addMember("defaultExpression",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getDefaultExpression()).build());
            }
            if (StrUtil.isNotEmpty(autoMappingMetadata.getConditionExpression())) {
                builder.addMember("conditionExpression",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getConditionExpression()).build());
            }
            if (ArrayUtil.isNotEmpty(autoMappingMetadata.getQualifiedByName())) {
                builder.addMember("qualifiedByName", CodeBlock.builder().add("$L",
                        "{" + ArrayUtil.join(autoMappingMetadata.getQualifiedByName(), ",", "\"", "\"") + "}").build());
            }
            if (ArrayUtil.isNotEmpty(autoMappingMetadata.getConditionQualifiedByName())) {
                builder.addMember("conditionQualifiedByName", CodeBlock.builder().add("$L",
                        "{" + ArrayUtil.join(autoMappingMetadata.getConditionQualifiedByName(), ",", "\"", "\"") + "}").build());
            }
            if (ArrayUtil.isNotEmpty(autoMappingMetadata.getDependsOn())) {
                builder.addMember("dependsOn", CodeBlock.builder().add("$L",
                        "{" + ArrayUtil.join(autoMappingMetadata.getDependsOn(), ",", "\"", "\"") + "}").build());
            }
            return builder.build();
        }).collect(Collectors.toList());
    }

    private AnnotationSpec buildGeneratedMapperAnnotationSpec(AutoMapperMetadata metadata) {
        List<ClassName> usesClassNameList =
            Optional.ofNullable(metadata.getUsesClassNameList()).orElse(new ArrayList<>());

        List<ClassName> importsClassNameList =
            Optional.ofNullable(metadata.getImportsClassNameList()).orElse(new ArrayList<>());

        // config
        CodeBlock configCodeBlock = CodeBlock.builder()
            .add("$T.class", metadata.getMapstructConfigClass())
            .build();

        // uses
        CodeBlock.Builder usesCodeBuilder = CodeBlock.builder().add("{");
        for (int i = 0; i < usesClassNameList.size(); i++) {
            usesCodeBuilder.add("$T.class", usesClassNameList.get(i));
            if (i != usesClassNameList.size() - 1) {
                usesCodeBuilder.add(",");
            }
        }
        CodeBlock usesCodeBlock = usesCodeBuilder.add("}").build();

        // imports
        final CodeBlock.Builder importsCodeBuilder = CodeBlock.builder().add("{");
        for (int i = 0; i < importsClassNameList.size(); i++) {
            importsCodeBuilder.add("$T.class", importsClassNameList.get(i));
            if (i != importsClassNameList.size() - 1) {
                importsCodeBuilder.add(",");
            }
        }
        final CodeBlock importsCodeBlock = importsCodeBuilder.add("}").build();

        AnnotationSpec.Builder builder =
            AnnotationSpec.builder(
                    ClassName.get(ContextConstants.Mapper.packageName, ContextConstants.Mapper.className))
                .addMember("config", configCodeBlock)
                .addMember("uses", usesCodeBlock)
                .addMember("imports", importsCodeBlock);
        return builder.build();
    }

}
