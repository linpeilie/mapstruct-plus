package io.github.linpeilie.mapstruct;

import io.github.linpeilie.AbstractCachedConverterFactory;
import io.github.linpeilie.BaseCycleAvoidingMapper;
import io.github.linpeilie.BaseMapMapper;
import io.github.linpeilie.BaseMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

public class SpringConverterFactory extends AbstractCachedConverterFactory {

    private final ApplicationContext applicationContext;

    public SpringConverterFactory(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <S, T> BaseMapper<S, T> findMapper(final Class<S> sourceType, final Class<T> targetType) {
        ResolvableType type = ResolvableType.forClassWithGenerics(
            BaseMapper.class, sourceType, targetType);
        String[] beanNames = applicationContext.getBeanNamesForType(type);
        if (beanNames.length == 0) {
            return null;
        }
        return (BaseMapper<S, T>) applicationContext.getBean(beanNames[0]);
    }

    @Override
    public <S, T> BaseCycleAvoidingMapper<S, T> findCycleAvoidingMapper(final Class<S> sourceType, final Class<T> targetType) {
        ResolvableType type = ResolvableType.forClassWithGenerics(
            BaseCycleAvoidingMapper.class, sourceType, targetType);
        String[] beanNames = applicationContext.getBeanNamesForType(type);
        if (beanNames.length == 0) {
            return null;
        }
        return (BaseCycleAvoidingMapper<S, T>) applicationContext.getBean(beanNames[0]);
    }

    @Override
    protected <S> BaseMapMapper findMapMapper(final Class<?> source) {
        ResolvableType type = ResolvableType.forClassWithGenerics(
            BaseMapMapper.class, source);
        String[] beanNames = applicationContext.getBeanNamesForType(type);
        if (beanNames.length == 0) {
            return null;
        }
        return (BaseMapMapper) applicationContext.getBean(beanNames[0]);
    }
}
