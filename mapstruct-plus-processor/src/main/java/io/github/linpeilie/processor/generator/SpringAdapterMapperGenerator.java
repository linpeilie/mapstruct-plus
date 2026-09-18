package io.github.linpeilie.processor.generator;

import cn.easii.tutelary.deps.com.squareup.javapoet.AnnotationSpec;
import cn.easii.tutelary.deps.com.squareup.javapoet.ClassName;
import io.github.linpeilie.utils.CollectionUtils;
import java.util.List;

public class SpringAdapterMapperGenerator extends IocAdapterMapperGenerator {

    private AnnotationSpec autowired() {
        return AnnotationSpec
            .builder(ClassName.get("org.springframework.beans.factory.annotation", "Autowired"))
            .build();
    }

    /**
     * spring 线注册通道整体替换：适配器不再携带 {@code @Component}，
     * 注册统一由 ModuleMapperRegistrar 依据编译期清单完成；{@code @Autowired} 字段装配语义不变
     */
    @Override
    protected AnnotationSpec componentAnnotation() {
        return null;
    }

    @Override
    protected List<AnnotationSpec> injectAnnotations() {
        return CollectionUtils.newArrayList(autowired());
    }

}
