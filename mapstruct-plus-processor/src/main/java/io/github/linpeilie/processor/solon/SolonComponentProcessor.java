package io.github.linpeilie.processor.solon;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import io.github.linpeilie.ComponentModelConstant;
import java.util.List;
import org.mapstruct.ap.internal.model.Annotation;
import org.mapstruct.ap.internal.model.Mapper;
import org.mapstruct.ap.internal.processor.AnnotationBasedComponentModelProcessor;

public class SolonComponentProcessor extends AnnotationBasedComponentModelProcessor {
    @Override
    protected String getComponentModelIdentifier() {
        return ComponentModelConstant.SOLON;
    }

    @Override
    protected List<Annotation> getTypeAnnotations(final Mapper mapper) {
        return CollectionUtil.newArrayList(component());
    }

    private Annotation component() {
        return new Annotation(getTypeFactory().getType("org.noear.solon.annotation.Component"));
    }

    private Annotation inject() {
        return new Annotation(getTypeFactory().getType("org.noear.solon.annotation.Inject"));
    }

    @Override
    protected List<Annotation> getMapperReferenceAnnotations() {
        return CollectionUtil.newArrayList(inject());
    }

    @Override
    protected boolean requiresGenerationOfDecoratorClass() {
        return true;
    }
}
