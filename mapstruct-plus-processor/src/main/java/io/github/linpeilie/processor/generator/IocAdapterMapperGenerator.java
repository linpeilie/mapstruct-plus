package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.AbstractAdapterMapperGenerator;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import java.util.Collection;
import java.util.List;
import javax.lang.model.element.Modifier;

public abstract class IocAdapterMapperGenerator extends AbstractAdapterMapperGenerator {

    protected abstract AnnotationSpec componentAnnotation();

    protected abstract List<AnnotationSpec> injectAnnotations();

    @Override
    protected TypeSpec createTypeSpec(final Collection<AbstractAdapterMethodMetadata> adapterMethods,
        final String adapterClassName) {
        TypeSpec.Builder adapterBuilder = TypeSpec.classBuilder(ClassName.get(adapterPackage(), adapterClassName))
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(componentAnnotation());

        adapterMethods.stream()
            .filter(adapterMethodMetadata -> !adapterMethodMetadata.isStatic())
            .map(AbstractAdapterMethodMetadata::getMapper)
            .distinct()
            .forEach(mapper -> adapterBuilder.addField(buildMapperField(mapper)));

        adapterMethods.forEach(adapterMethod -> adapterBuilder
            .addMethod(buildProxyMethod(adapterMethod)));

        return adapterBuilder.build();
    }

    private FieldSpec buildMapperField(ClassName mapper) {
        return FieldSpec.builder(mapper, firstWordToLower(mapper.simpleName()), Modifier.PRIVATE)
            .addAnnotations(injectAnnotations())
            .build();
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

}
