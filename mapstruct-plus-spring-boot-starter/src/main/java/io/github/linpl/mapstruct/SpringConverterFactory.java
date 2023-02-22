package io.github.linpl.mapstruct;

import io.github.linpl.BaseMapper;
import io.github.linpl.ConverterFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

public class SpringConverterFactory implements ConverterFactory {

    private final ApplicationContext applicationContext;

    public SpringConverterFactory(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <S, T> BaseMapper<S, T> getMapper(final Class<S> sourceType, final Class<T> targetType) {
        ResolvableType type = ResolvableType.forClassWithGenerics(
            BaseMapper.class, sourceType, targetType);
        String[] beanNames = applicationContext.getBeanNamesForType(type);
        if (beanNames.length == 0) {
            return null;
        }
        return (BaseMapper<S, T>) applicationContext.getBean(beanNames[0]);
    }
}
