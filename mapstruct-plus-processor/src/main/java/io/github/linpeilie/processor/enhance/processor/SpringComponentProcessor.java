package io.github.linpeilie.processor.enhance.processor;

import io.github.linpeilie.processor.ContextConstants;
import io.github.linpeilie.processor.enhance.model.MapObjectConverterMapperReference;
import io.github.linpeilie.processor.enhance.model.SpringDelayInjectMapperReference;
import java.util.Collections;
import java.util.List;
import org.mapstruct.ap.internal.gem.InjectionStrategyGem;
import org.mapstruct.ap.internal.model.Annotation;
import org.mapstruct.ap.internal.model.Field;
import org.mapstruct.ap.internal.model.Mapper;
import org.mapstruct.ap.internal.model.common.Type;
import org.mapstruct.ap.internal.processor.AnnotationBasedComponentModelProcessor;

public class SpringComponentProcessor extends AnnotationBasedComponentModelProcessor {

    @Override
    protected String getComponentModelIdentifier() {
        return ContextConstants.ComponentModelConfig.springLazy;
    }

    /**
     * spring 线注册通道整体替换：生成物不再携带 {@code @Component}，
     * 注册统一由 ModuleMapperRegistrar 依据编译期清单完成；字段装配仍保留 spring-lazy 延迟注入
     */
    @Override
    protected List<Annotation> getTypeAnnotations(Mapper mapper) {
        return Collections.emptyList();
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
        Type refType = originalReference.getType();
        // MapObjectConverter 实现类通过 getInstance() 获取单例，不走 Spring Bean 注入
        Type converterInterface = getTypeFactory().getType(
            ContextConstants.MapObjectConverter.packageName + "." + ContextConstants.MapObjectConverter.className);
        if (refType.isAssignableTo(converterInterface)) {
            return new MapObjectConverterMapperReference(refType, originalReference.getVariableName(),
                originalReference.isUsed(), converterInterface);
        }
        return new SpringDelayInjectMapperReference(refType, originalReference.getVariableName(),
            originalReference.isUsed(),
            getTypeFactory().getType("io.github.linpeilie.mapstruct.SpringContextUtils4Msp"));
    }
}
