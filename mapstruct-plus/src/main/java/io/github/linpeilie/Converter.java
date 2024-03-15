package io.github.linpeilie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (source == null) {
            return null;
        }
        BaseMapper<S, T> mapper = (BaseMapper<S, T>) converterFactory.getMapper(source.getClass(), targetType);
        if (mapper != null) {
            return mapper.convert(source);
        }
        throw new ConvertException(
            "cannot find converter from " + source.getClass().getSimpleName() + " to " + targetType.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, T target) {
        if (source == null) {
            return null;
        }
        if (target == null) {
            return null;
        }
        BaseMapper<S, T> mapper = (BaseMapper<S, T>) converterFactory.getMapper(source.getClass(), target.getClass());
        if (mapper != null) {
            return mapper.convert(source, target);
        }
        throw new ConvertException("cannot find converter from " + source.getClass().getSimpleName() + " to " +
                                   target.getClass().getSimpleName());
    }

    public <S, T> List<T> convert(List<S> source, Class<T> targetType) {
        if (source == null || source.isEmpty()) {
            return new ArrayList<>();
        }
        return source.stream().map(item -> convert(item, targetType)).collect(Collectors.toList());
    }


    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, Class<T> target, CycleAvoidingMappingContext context) {
        if (source == null) {
            return null;
        }
        BaseCycleAvoidingMapper<S, T> mapper =
            (BaseCycleAvoidingMapper<S, T>) converterFactory.getCycleAvoidingMapper(source.getClass(), target);
        if (mapper != null) {
            return mapper.convert(source, context);
        }
        throw new ConvertException("cannot find converter from " + source.getClass().getSimpleName() + " to " +
                                   target.getSimpleName());
    }

    public <S, T> List<T> convert(List<S> source, Class<T> targetType, CycleAvoidingMappingContext context) {
        if (source == null || source.isEmpty()) {
            return new ArrayList<>();
        }
        return source.stream().map(item -> convert(item, targetType, context)).collect(Collectors.toList());
    }



    public <T> T convert(Map<String, Object> map, Class<T> target) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        if (map.values().stream().allMatch(Objects::isNull)) {
            return null;
        }
        final BaseMapMapper<T> mapper = converterFactory.getMapMapper(target);
        if (mapper != null) {
            return mapper.convert(map);
        }
        throw new ConvertException("cannot find converter from " + map.getClass().getName() + " to " +
                                   target.getSimpleName());
    }

}
