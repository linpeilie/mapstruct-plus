package io.github.linpeilie.processor;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import org.mapstruct.MappingConstants;

import static javax.tools.Diagnostic.Kind.ERROR;

public class DefaultAdapterMapperGenerator extends AbstractAdapterMapperGenerator {

    public TypeSpec createTypeSpec(Collection<AdapterMethodMetadata> adapterMethods) {
        TypeSpec.Builder adapterBuilder = TypeSpec.classBuilder(
                ClassName.get(AutoMapperProperties.getAdapterPackage(), AutoMapperProperties.getAdapterClassName()))
            .addModifiers(Modifier.PUBLIC);

        if (AutoMapperProperties.getComponentModel().contentEquals(MappingConstants.ComponentModel.SPRING)) {
            adapterMethods.forEach(adapterMethod -> adapterBuilder.addField(buildMapperField(adapterMethod.getMapper()))
                .addMethod(buildMapperSetterMethod(adapterMethod.getMapper()))
                .addMethod(buildSpringProxyMethod(adapterMethod)));
        } else {
            adapterMethods.forEach(adapterMethod -> adapterBuilder.addMethod(buildDefaultProxyMethod(adapterMethod)));
        }

        return adapterBuilder.build();
    }

    private MethodSpec buildSpringProxyMethod(final AdapterMethodMetadata adapterMethodMetadata) {
        ParameterSpec parameterSpec = ParameterSpec.builder(adapterMethodMetadata.getSource(),
            firstWordToLower(adapterMethodMetadata.getSource().simpleName())).build();
        return MethodSpec.methodBuilder(firstWordToLower(adapterMethodMetadata.getSource().simpleName()) + "To" +
                                        adapterMethodMetadata.getTarget().simpleName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .returns(adapterMethodMetadata.getTarget())
            .addStatement("return $N.convert($N)", firstWordToLower(adapterMethodMetadata.getMapper().simpleName()))
            .build();
    }

    private FieldSpec buildMapperField(ClassName mapper) {
        return FieldSpec.builder(mapper, firstWordToLower(mapper.simpleName()), Modifier.PRIVATE).build();
    }

    private String firstWordToLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private MethodSpec buildDefaultProxyMethod(AdapterMethodMetadata adapterMethodMetadata) {
        ParameterSpec parameterSpec = ParameterSpec.builder(adapterMethodMetadata.getSource(),
            firstWordToLower(adapterMethodMetadata.getSource().simpleName())).build();
        return MethodSpec.methodBuilder(firstWordToLower(adapterMethodMetadata.getSource().simpleName()) + "To" +
                                        adapterMethodMetadata.getTarget().simpleName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .returns(adapterMethodMetadata.getTarget())
            .addStatement("return ($T.getMapper($T.class)).convert($N)",
                ClassName.get("org.mapstruct.factory", "Mappers"), adapterMethodMetadata.getMapper(),
                firstWordToLower(adapterMethodMetadata.getSource().simpleName()))
            .build();
    }

    private MethodSpec buildMapperSetterMethod(ClassName mapper) {
        ParameterSpec parameterSpec = buildMapperSetterParameter(mapper);
        return MethodSpec.methodBuilder("set" + mapper.simpleName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .addStatement("this.$N = $N", buildMapperField(mapper), parameterSpec)
            .build();
    }

    private ParameterSpec buildMapperSetterParameter(ClassName mapper) {
        return ParameterSpec.builder(mapper, firstWordToLower(mapper.simpleName())).build();
    }

}
