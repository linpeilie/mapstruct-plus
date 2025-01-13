package io.github.linpeilie;

import io.github.linpeilie.utils.CollectionUtils;
import io.github.linpeilie.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class Converter {

    private final ConverterFactory converterFactory;

    public Converter() {
        this.converterFactory = new DefaultConverterFactory();
    }

    public Converter(final ConverterFactory converterFactory) {
        this.converterFactory = converterFactory;
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, Class<T> targetType) {
        if (ObjectUtils.isAnyNull(source, targetType)) {
            return null;
        }
        Class<?> sourceType = source.getClass();
        BaseMapper<S, T> mapper = (BaseMapper<S, T>) converterFactory.getMapper(sourceType, targetType);
        if (ObjectUtils.isNull(mapper)) {
            throw new ConvertException("cannot find converter from " + sourceType.getSimpleName() + " to " + targetType.getSimpleName());
        }
        return mapper.convert(source);
    }

    public <S, T> T convert(S source, Class<T> targetType, Consumer<T> beanConsumer) {
        T bean = convert(source, targetType);
        return ObjectUtils.handleIfNull(bean, beanConsumer);
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, T target) {
        if (ObjectUtils.isAnyNull(source, target)) {
            return null;
        }
        Class<?> sourceType = source.getClass();
        Class<?> targetType = target.getClass();
        BaseMapper<S, T> mapper = (BaseMapper<S, T>) converterFactory.getMapper(sourceType, targetType);
        if (ObjectUtils.isNull(mapper)) {
            throw new ConvertException("cannot find converter from " + sourceType.getSimpleName() + " to " + targetType.getSimpleName());
        }
        return mapper.convert(source, target);
    }

    public <S, T> T convert(S source, T target, Consumer<T> beanConsumer) {
        T bean = convert(source, target);
        return ObjectUtils.handleIfNull(bean, beanConsumer);
    }

    public <S, T> List<T> convert(List<S> sourceList, Class<T> targetType) {
        return convert(sourceList, targetType, (Consumer<T>) null);
    }

    @SuppressWarnings("unchecked")
    public <S, T> List<T> convert(List<S> sourceList, Class<T> targetType, Consumer<T> beanConsumer) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        if (ObjectUtils.isNull(targetType)) {
            throw new ConvertException("targetType cannot be null");
        }
        Class<?> sourceType = sourceList.getFirst().getClass();
        BaseMapper<S, T> mapper = (BaseMapper<S, T>) converterFactory.getMapper(sourceType, targetType);
        if (ObjectUtils.isNull(mapper)) {
            throw new ConvertException("cannot find converter from " + sourceType.getSimpleName() + " to " + targetType.getSimpleName());
        }
        // 如果 beanConsumer 本来就为 null，则不再调用带 Consumer 参数的 convert 方法，避免在循环中进行不必要的非空判断
        if (ObjectUtils.isNotNull(beanConsumer)) {
            return CollectionUtils.toList(sourceList, source -> {
                T bean = mapper.convert(source);
                beanConsumer.accept(bean);
                return bean;
            });
        }
        return mapper.convert(sourceList);
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, Class<T> targetType, CycleAvoidingMappingContext context) {
        if (ObjectUtils.isAnyNull(source, targetType)) {
            return null;
        }
        Class<?> sourceType = source.getClass();
        BaseCycleAvoidingMapper<S, T> mapper = (BaseCycleAvoidingMapper<S, T>) converterFactory.getCycleAvoidingMapper(sourceType, targetType);
        if (ObjectUtils.isNull(mapper)) {
            throw new ConvertException("cannot find converter from " + sourceType.getSimpleName() + " to " + targetType.getSimpleName());
        }
        return mapper.convert(source, context);
    }

    public <S, T> T convert(S source, Class<T> targetType, CycleAvoidingMappingContext context, Consumer<T> beanConsumer) {
        T bean = convert(source, targetType, context);
        return ObjectUtils.handleIfNull(bean, beanConsumer);
    }

    public <S, T> List<T> convert(List<S> sourceList, Class<T> targetType, CycleAvoidingMappingContext context) {
        return convert(sourceList, targetType, context, null);
    }

    @SuppressWarnings("unchecked")
    public <S, T> List<T> convert(List<S> sourceList, Class<T> targetType, CycleAvoidingMappingContext context, Consumer<T> beanConsumer) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        if (ObjectUtils.isNull(targetType)) {
            throw new ConvertException("targetType cannot be null");
        }
        Class<?> sourceType = sourceList.getFirst().getClass();
        BaseCycleAvoidingMapper<S, T> mapper = (BaseCycleAvoidingMapper<S, T>) converterFactory.getCycleAvoidingMapper(sourceType, targetType);
        if (ObjectUtils.isNull(mapper)) {
            throw new ConvertException("cannot find converter from " + sourceType.getSimpleName() + " to " + targetType.getSimpleName());
        }

        // 如果 beanConsumer 本来就为 null，则不再调用带 Consumer 参数的 convert 方法，避免在循环中进行不必要的非空判断
        if (ObjectUtils.isNotNull(beanConsumer)) {
            return CollectionUtils.toList(sourceList, source -> {
                T bean = mapper.convert(source, context);
                beanConsumer.accept(bean);
                return bean;
            });
        }
        return mapper.convert(sourceList, context);
    }

    public <T> T convert(Map<String, Object> map, Class<T> targetType) {
        if (CollectionUtils.isEmpty(map) || map.values().stream().allMatch(Objects::isNull)) {
            return null;
        }
        if (ObjectUtils.isNull(targetType)) {
            throw new ConvertException("targetType cannot be null");
        }

        final BaseMapMapper<T> mapper = converterFactory.getMapMapper(targetType);
        if (ObjectUtils.isNull(mapper)) {
            throw new ConvertException("cannot find converter from " + map.getClass().getName() + " to " + targetType.getSimpleName());
        }
        return mapper.convert(map);
    }

    public <T> T convert(Map<String, Object> map, Class<T> targetType, Consumer<T> beanConsumer) {
        T bean = convert(map, targetType);
        return ObjectUtils.handleIfNull(bean, beanConsumer);
    }

}
