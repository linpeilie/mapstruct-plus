package io.github.linpeilie.processor.generator;

import cn.hutool.core.collection.CollectionUtil;
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
import java.util.List;
import javax.lang.model.element.Modifier;

public class SpringAdapterMapperGenerator extends IocAdapterMapperGenerator {

    private AnnotationSpec component() {
        return AnnotationSpec
            .builder(ClassName.get("org.springframework.stereotype", "Component"))
            .build();
    }

    private AnnotationSpec autowired() {
        return AnnotationSpec
            .builder(ClassName.get("org.springframework.beans", "Autowired"))
            .build();
    }

    private AnnotationSpec lazy() {
        return AnnotationSpec
            .builder(ClassName.get("org.springframework.context.annotation", "Lazy"))
            .build();
    }

    @Override
    protected AnnotationSpec componentAnnotation() {
        return component();
    }

    @Override
    protected List<AnnotationSpec> injectAnnotations() {
        return CollectionUtil.newArrayList(autowired(), lazy());
    }

}
