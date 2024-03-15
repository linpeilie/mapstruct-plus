package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.AutoMapperProperties;
import io.github.linpeilie.utils.CollectionUtils;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

import static javax.tools.Diagnostic.Kind.ERROR;

public class MapperConfigGenerator {

    public void write(ProcessingEnvironment processingEnv, String mapstructConfigName, String adapterClassName, List<TypeMirror> uses) {
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(AutoMapperProperties.getConfigPackage() + "." + mapstructConfigName)
            .openWriter()) {
            JavaFile.builder(AutoMapperProperties.getConfigPackage(), createConfigTypeSpec(mapstructConfigName, adapterClassName, uses)).build().writeTo(writer);
        } catch (IOException e) {
            processingEnv.getMessager()
                .printMessage(ERROR,
                    "Error while opening " + AutoMapperProperties.getConfigClassName() + " output file: " +
                    e.getMessage());
        }
    }

    private TypeSpec createConfigTypeSpec(final String mapstructConfigName,
        final String adapterClassName,
        final List<TypeMirror> uses) {
        return TypeSpec.interfaceBuilder(mapstructConfigName)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(buildMapperConfigAnnotationSpec(adapterClassName, uses))
            .build();
    }

    private AnnotationSpec buildMapperConfigAnnotationSpec(final String adapterClassName, final List<TypeMirror> uses) {
        CodeBlock.Builder usesCodeBuilder = CodeBlock.builder().add("{");
        usesCodeBuilder.add("$T.class", ClassName.get(AutoMapperProperties.getAdapterPackage(), adapterClassName));
        if (CollectionUtils.isNotEmpty(uses)) {
            uses.forEach(use -> {
                usesCodeBuilder.add(", $T.class", use);
            });
        }
        CodeBlock usesCodeBlock = usesCodeBuilder.add("}").build();
        final AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassName.get("org.mapstruct", "MapperConfig"))
            .addMember("componentModel",
                CodeBlock.builder().add("$S", AutoMapperProperties.getComponentModel()).build())
            .addMember("uses", usesCodeBlock);
        // unmappedSourcePolicy
        if (AutoMapperProperties.getUnmappedSourcePolicy() != null) {
            CodeBlock unmappedSourcePolicyCodeBlock = CodeBlock.builder().add("$T.$L",
                ClassName.get("org.mapstruct", "ReportingPolicy"),
                AutoMapperProperties.getUnmappedSourcePolicy()).build();
            builder.addMember("unmappedSourcePolicy", unmappedSourcePolicyCodeBlock);
        }

        // unmappedTargetPolicy
        if (AutoMapperProperties.getUnmappedTargetPolicy() != null) {
            CodeBlock unmappedTargetPolicyCodeBlock = CodeBlock.builder().add("$T.$L",
                ClassName.get("org.mapstruct", "ReportingPolicy"),
                AutoMapperProperties.getUnmappedTargetPolicy()).build();
            builder.addMember("unmappedTargetPolicy", unmappedTargetPolicyCodeBlock);
        }

        // nullValueMappingStrategy
        if (AutoMapperProperties.getNullValueMappingStrategy() != null) {
            CodeBlock nullValueMappingStrategyCodeBlock = CodeBlock.builder().add("$T.$L",
                    ClassName.get("org.mapstruct", "NullValueMappingStrategy"),
                    AutoMapperProperties.getNullValueMappingStrategy()).build();
            builder.addMember("nullValueMappingStrategy", nullValueMappingStrategyCodeBlock);
        }

        // nullValuePropertyMappingStrategy
        if (AutoMapperProperties.getNullValuePropertyMappingStrategy() != null) {
            CodeBlock nullValuePropertyMappingStrategyCodeBlock = CodeBlock.builder().add("$T.$L",
                    ClassName.get("org.mapstruct", "NullValuePropertyMappingStrategy"),
                    AutoMapperProperties.getNullValuePropertyMappingStrategy()).build();
            builder.addMember("nullValuePropertyMappingStrategy", nullValuePropertyMappingStrategyCodeBlock);
        }

        // builder
        CodeBlock builderCodeBlock = CodeBlock.builder()
            .add("@$T(buildMethod = $S, disableBuilder = $L)", ClassName.get("org.mapstruct", "Builder"),
                AutoMapperProperties.getBuildMethod(), AutoMapperProperties.isDisableBuilder())
            .build();
        builder.addMember("builder", builderCodeBlock);
        return builder.build();
    }

}
