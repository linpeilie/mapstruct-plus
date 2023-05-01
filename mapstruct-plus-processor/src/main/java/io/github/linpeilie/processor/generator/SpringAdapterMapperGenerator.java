package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.AbstractAdapterMapperGenerator;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import java.util.Collection;
import javax.lang.model.element.Modifier;

public class SpringAdapterMapperGenerator extends AbstractAdapterMapperGenerator {

    @Override
    protected TypeSpec createTypeSpec(Collection<AbstractAdapterMethodMetadata> adapterMethods, String adapterClassName) {
        TypeSpec.Builder adapterBuilder = TypeSpec.classBuilder(ClassName.get(adapterPackage(), adapterClassName))
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(ClassName.get("org.springframework.stereotype", "Component"));

        adapterMethods.stream()
            .filter(adapterMethodMetadata -> !adapterMethodMetadata.isStatic())
            .map(AbstractAdapterMethodMetadata::getMapper)
            .distinct()
            .forEach(mapper -> adapterBuilder.addField(buildMapperField(mapper))
                .addMethod(buildMapperSetterMethod(mapper)));

        adapterMethods.forEach(adapterMethod ->
            adapterBuilder.addMethods(buildProxyMethod(adapterMethod)));
        return adapterBuilder.build();
    }

    private FieldSpec buildMapperField(ClassName mapper) {
        return FieldSpec.builder(mapper, firstWordToLower(mapper.simpleName()), Modifier.PRIVATE).build();
    }

    private String firstWordToLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    @Override
    protected CodeBlock proxyMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        return CodeBlock.builder()
            .add("return $N.$N($N);", firstWordToLower(adapterMethodMetadata.getMapper().simpleName()),
                adapterMethodMetadata.getMapperMethodName(),
                "param")
            .build();
    }

    private MethodSpec buildMapperSetterMethod(ClassName mapper) {
        ParameterSpec parameterSpec = buildMapperSetterParameter(mapper);
        return MethodSpec.methodBuilder("set" + mapper.simpleName())
            .addModifiers(Modifier.PUBLIC)
            .addParameter(parameterSpec)
            .addAnnotation(
                AnnotationSpec.builder(ClassName.get("org.springframework.beans.factory.annotation", "Autowired"))
                    .build())
            .addStatement("this.$N = $N", buildMapperField(mapper), parameterSpec)
            .build();
    }

    private ParameterSpec buildMapperSetterParameter(ClassName mapper) {
        return ParameterSpec.builder(mapper, firstWordToLower(mapper.simpleName()))
            .addAnnotation(
                AnnotationSpec.builder(ClassName.get("org.springframework.context.annotation", "Lazy")).build())
            .build();
    }

}
