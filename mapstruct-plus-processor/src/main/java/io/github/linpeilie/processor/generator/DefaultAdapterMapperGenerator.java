package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.AbstractAdapterMapperGenerator;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import java.util.Collection;
import javax.lang.model.element.Modifier;

public class DefaultAdapterMapperGenerator extends AbstractAdapterMapperGenerator {

    public TypeSpec createTypeSpec(Collection<AbstractAdapterMethodMetadata> adapterMethods, String adapterClassName) {
        TypeSpec.Builder adapterBuilder = TypeSpec.classBuilder(
                ClassName.get(adapterPackage(), adapterClassName))
            .addModifiers(Modifier.PUBLIC);

        adapterMethods.forEach(adapterMethod -> adapterBuilder.addMethod(buildDefaultProxyMethod(adapterMethod)));

        return adapterBuilder.build();
    }

    private String firstWordToLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private MethodSpec buildDefaultProxyMethod(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        ParameterSpec parameterSpec = ParameterSpec.builder(adapterMethodMetadata.getSource(),
            firstWordToLower(adapterMethodMetadata.getSource().simpleName())).build();
        return MethodSpec.methodBuilder(adapterMethodMetadata.getMethodName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .returns(adapterMethodMetadata.getReturn())
            .addStatement("return ($T.getMapper($T.class)).$N($N)",
                ClassName.get("org.mapstruct.factory", "Mappers"), adapterMethodMetadata.getMapper(),
                adapterMethodMetadata.getMapperMethodName(),
                firstWordToLower(adapterMethodMetadata.getSource().simpleName()))
            .build();
    }

}
