package io.github.linpl.processor;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;
import org.apache.commons.lang3.StringUtils;

import static io.github.linpl.processor.Constants.*;

public class AutoMapperGenerator {

    public void write(AutoMapperMetadata metadata, Writer writer) {
        try {
            JavaFile.builder(AutoMapperProperties.getMapperPackage(), createTypeSpec(metadata)).build().writeTo(writer);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (Exception e) {
            throw e;
        }
    }

    private TypeSpec createTypeSpec(AutoMapperMetadata metadata) {
        ParameterizedTypeName converterName =
            ParameterizedTypeName.get(ClassName.get(BASE_MAPPER_PACKAGE, BASE_MAPPER_CLASS_NAME),
                metadata.getSourceClassName(), metadata.getTargetClassName());

        TypeSpec.Builder builder = TypeSpec.interfaceBuilder(metadata.mapperName())
            .addSuperinterface(converterName)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(buildGeneratedMapperAnnotationSpec(metadata));
        if (metadata.getFieldMappingList() != null && !metadata.getFieldMappingList().isEmpty()) {
            final ParameterSpec source = ParameterSpec.builder(metadata.getSourceClassName(), "source").build();
            final ParameterSpec target = ParameterSpec.builder(metadata.getTargetClassName(), "target")
                .addAnnotation(AnnotationSpec.builder(ClassName.get("org.mapstruct", "MappingTarget")).build())
                .build();
            builder.addMethod(addConvertMethodSpec(Collections.singletonList(source), metadata.getFieldMappingList(),
                metadata.getTargetClassName()));
            builder.addMethod(addConvertMethodSpec(Arrays.asList(source, target), metadata.getFieldMappingList(),
                metadata.getTargetClassName()));
        }
        return builder.build();
    }

    private MethodSpec addConvertMethodSpec(List<ParameterSpec> parameterSpecs,
                                            List<AutoMappingMetadata> autoMappingMetadataList,
                                            ClassName target) {
        return MethodSpec.methodBuilder("convert")
            .addParameters(parameterSpecs)
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .addAnnotations(buildMappingAnnotations(autoMappingMetadataList))
            .returns(target)
            .build();
    }

    private List<AnnotationSpec> buildMappingAnnotations(final List<AutoMappingMetadata> autoMappingMetadataList) {
        return autoMappingMetadataList.stream().map(autoMappingMetadata -> {
            final AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassName.get("org.mapstruct", "Mapping"))
                .addMember("target", CodeBlock.builder().add("$S", autoMappingMetadata.getTarget()).build())

                .addMember("dateFormat",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getDateFormat()).build())
                .addMember("numberFormat",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getNumberFormat()).build())
                .addMember("ignore", CodeBlock.builder().add(String.valueOf(autoMappingMetadata.isIgnore())).build());
            if (StringUtils.isNoneEmpty(autoMappingMetadata.getExpression())) {
                builder.addMember("expression",
                    CodeBlock.builder().add("$S", autoMappingMetadata.getExpression()).build());
            } else {
                builder.addMember("source", CodeBlock.builder().add("$S", autoMappingMetadata.getSource()).build());
            }
            return builder.build();
        }).collect(Collectors.toList());
    }

    private AnnotationSpec buildGeneratedMapperAnnotationSpec(AutoMapperMetadata metadata) {
        List<ClassName> usesClassNameList =
            Optional.ofNullable(metadata.getUsesClassNameList()).orElse(new ArrayList<>());

        // config
        CodeBlock configCodeBlock = CodeBlock.builder()
            .add("$T.class",
                ClassName.get(AutoMapperProperties.getMapperPackage(), AutoMapperProperties.getConfigClassName()))
            .build();

        // uses
        CodeBlock.Builder usesCodeBuilder = CodeBlock.builder().add("{");
        usesClassNameList.forEach(item -> usesCodeBuilder.add("$T.class", item));
        CodeBlock usesCodeBlock = usesCodeBuilder.add("}").build();

        AnnotationSpec.Builder builder =
            AnnotationSpec.builder(ClassName.get(MAPSTRUCT_MAPPER_PACKAGE, MAPSTRUCT_MAPPER_CLASS_NAME))
                .addMember("config", configCodeBlock)
                .addMember("uses", usesCodeBlock);
        return builder.build();
    }

}
