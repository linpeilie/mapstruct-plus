package io.github.linpeilie.processor.enhance.processor;

import io.github.linpeilie.processor.ContextConstants;
import io.github.linpeilie.processor.enhance.model.SpringDelayInjectMapperReference;
import io.github.linpeilie.utils.CollectionUtils;
import java.util.Collections;
import java.util.List;
import org.mapstruct.ap.internal.gem.InjectionStrategyGem;
import org.mapstruct.ap.internal.model.Annotation;
import org.mapstruct.ap.internal.model.Field;
import org.mapstruct.ap.internal.model.Mapper;
import org.mapstruct.ap.internal.processor.AnnotationBasedComponentModelProcessor;

public class SpringComponentProcessor extends AnnotationBasedComponentModelProcessor {

    private Annotation component() {
        return new Annotation(getTypeFactory().getType("org.springframework.stereotype.Component"));
    }

    @Override
    protected String getComponentModelIdentifier() {
        return ContextConstants.ComponentModelConfig.springLazy;
    }

    @Override
    protected List<Annotation> getTypeAnnotations(Mapper mapper) {
        return CollectionUtils.newArrayList(component());
    }

    @Override
    protected List<Annotation> getMapperReferenceAnnotations() {
        return Collections.emptyList();
    }

    @Override
    protected boolean requiresGenerationOfDecoratorClass() {
        return true;
    }

    @Override
    protected Field replacementMapperReference(Field originalReference,
        List<Annotation> annotations,
        InjectionStrategyGem injectionStrategy) {
        return new SpringDelayInjectMapperReference(originalReference.getType(), originalReference.getVariableName(),
            originalReference.isUsed(),
            getTypeFactory().getType("io.github.linpeilie.mapstruct.SpringContextUtils"));
    }
}
