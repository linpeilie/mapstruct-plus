package io.github.linpeilie.processor;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import java.util.Collection;
import javax.lang.model.element.Modifier;

public class SpringAdapterMapperGenerator extends AbstractAdapterMapperGenerator {

    @Override
    protected TypeSpec createTypeSpec(Collection<AdapterMethodMetadata> adapterMethods) {
        TypeSpec.Builder adapterBuilder = TypeSpec.classBuilder(
                ClassName.get(AutoMapperProperties.getAdapterPackage(), AutoMapperProperties.getAdapterClassName()))
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(ClassName.get("org.springframework.stereotype", "Component"));

        adapterMethods.forEach(adapterMethod -> adapterBuilder.addField(buildMapperField(adapterMethod.getMapper()))
            .addMethod(buildMapperSetterMethod(adapterMethod.getMapper()))
            .addMethod(buildProxyMethod(adapterMethod)));

        return adapterBuilder.build();
    }

    private FieldSpec buildMapperField(ClassName mapper) {
        return FieldSpec.builder(mapper,
            firstWordToLower(mapper.simpleName()), Modifier.PRIVATE).build();
    }

    private String firstWordToLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private MethodSpec buildProxyMethod(AdapterMethodMetadata adapterMethodMetadata) {
        ParameterSpec parameterSpec = ParameterSpec.builder(adapterMethodMetadata.getSource(),
            firstWordToLower(adapterMethodMetadata.getSource().simpleName())).build();
        return MethodSpec.methodBuilder(firstWordToLower(adapterMethodMetadata.getSource().simpleName()) + "To" +
                                        adapterMethodMetadata.getTarget().simpleName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .returns(adapterMethodMetadata.getTarget())
            .addStatement("return $N.convert($N)", firstWordToLower(adapterMethodMetadata.getMapper().simpleName()),
                firstWordToLower(adapterMethodMetadata.getSource().simpleName()))
            .build();
    }

    private MethodSpec buildMapperSetterMethod(ClassName mapper) {
        ParameterSpec parameterSpec = buildMapperSetterParameter(mapper);
        return MethodSpec.methodBuilder("set" + mapper.simpleName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .addAnnotation(AnnotationSpec.builder(
                    ClassName.get("org.springframework.beans.factory.annotation", "Autowired"))
                .build())
            .addStatement("this.$N = $N", buildMapperField(mapper), parameterSpec)
            .build();
    }

    private ParameterSpec buildMapperSetterParameter(ClassName mapper) {
        return ParameterSpec.builder(mapper,
                firstWordToLower(mapper.simpleName()))
            .addAnnotation(
                AnnotationSpec.builder(ClassName.get("org.springframework.context.annotation", "Lazy")).build())
            .build();
    }
}
