package io.github.linpeilie.processor.generator;

import cn.easii.tutelary.deps.com.squareup.javapoet.AnnotationSpec;
import cn.easii.tutelary.deps.com.squareup.javapoet.ClassName;
import io.github.linpeilie.utils.CollectionUtils;
import java.util.List;

public class SpringAdapterMapperGenerator extends IocAdapterMapperGenerator {

    private AnnotationSpec component() {
        return AnnotationSpec
            .builder(ClassName.get("org.springframework.stereotype", "Component"))
            .build();
    }

    private AnnotationSpec autowired() {
        return AnnotationSpec
            .builder(ClassName.get("org.springframework.beans.factory.annotation", "Autowired"))
            .build();
    }

    @Override
    protected AnnotationSpec componentAnnotation() {
        return component();
    }

    @Override
    protected List<AnnotationSpec> injectAnnotations() {
        return CollectionUtils.newArrayList(autowired());
    }

}
