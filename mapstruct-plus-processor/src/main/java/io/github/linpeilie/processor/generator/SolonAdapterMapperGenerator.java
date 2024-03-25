package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import io.github.linpeilie.utils.CollectionUtils;
import java.util.Collection;
import java.util.List;

public class SolonAdapterMapperGenerator extends IocAdapterMapperGenerator {

    private AnnotationSpec component() {
        return AnnotationSpec.builder(ClassName.get("org.noear.solon.annotation", "Component"))
            .build();
    }

    private AnnotationSpec inject() {
        return AnnotationSpec.builder(ClassName.get("org.noear.solon.annotation", "Inject"))
            .build();
    }

    @Override
    protected AnnotationSpec componentAnnotation() {
        return component();
    }

    @Override
    protected List<AnnotationSpec> injectAnnotations() {
        return CollectionUtils.newArrayList(inject());
    }
}
