package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.metadata.AutoEnumMapperMetadata;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import javax.lang.model.element.Modifier;

public class AutoEnumMapperGenerator {

    public void write(AutoEnumMapperMetadata metadata, Writer writer) {
        try {
            JavaFile.builder(metadata.mapperPackage(), createTypeSpec(metadata)).build().writeTo(writer);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private TypeSpec createTypeSpec(final AutoEnumMapperMetadata metadata) {
        return TypeSpec.classBuilder(metadata.mapperName())
            .addModifiers(Modifier.PUBLIC)
            .addMethod(buildToEnumMethod(metadata))
            .addMethod(buildToValueMethod(metadata))
            .build();
    }

    private MethodSpec buildToValueMethod(final AutoEnumMapperMetadata metadata) {
        final CodeBlock.Builder builder = CodeBlock.builder();
        builder.add("if(e == null) { ");
        if (metadata.getReturnType().isPrimitive()) {
            final String returnType = metadata.getReturnType().toString();
            switch (returnType) {
                case "char":
                    builder.add("return '\u0000';");
                    break;
                case "boolean":
                    builder.add("return false;");
                    break;
                default:
                    builder.add("return 0;");
                    break;
            }
        } else {
            builder.add("return null;");
        }
        builder.add("}");
        builder.add("return e." + metadata.getGetter() + "();");
        return MethodSpec.methodBuilder(metadata.toValueMethodName())
            .addParameter(ParameterSpec.builder(metadata.getSourceClassName(), "e").build())
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addCode(builder.build())
            .returns(metadata.getReturnType())
            .build();
    }

    private MethodSpec buildToEnumMethod(final AutoEnumMapperMetadata metadata) {
        final MethodSpec.Builder builder = MethodSpec.methodBuilder(metadata.toEnumMethodName())
            .addParameter(ParameterSpec.builder(metadata.getReturnType(), "value").build())
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
        if (!metadata.getReturnType().isPrimitive()) {
            builder.addCode(CodeBlock.builder().add("if(value == null) { return null; }").build());
        }
        return builder.addCode(CodeBlock.builder()
                .add("$T[] enums = $T.values();", metadata.getSourceClassName(), metadata.getSourceClassName()).build())
            .addCode(CodeBlock.builder().add("for($T e : enums) {", metadata.getSourceClassName()).build())
            .addCode(CodeBlock.builder()
                .add("if(String.valueOf(e.$L()).contentEquals(String.valueOf(value))) { return e; }",
                    metadata.getGetter())
                .build())
            .addCode("} return null;")
            .returns(metadata.getSourceClassName())
            .build();
    }

}
