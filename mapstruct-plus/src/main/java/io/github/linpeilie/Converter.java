package io.github.linpeilie;

import java.util.Collections;
import java.util.List;
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
            return mapper.convert(source);
        }
        throw new ConvertException("cannot find converter from " + source.getClass().getSimpleName() + " to " +
                                   target.getClass().getSimpleName());
    }

    public <S, T> List<T> convert(List<S> source, Class<T> targetType) {
        if (source == null || source.size() == 0) {
            return Collections.emptyList();
        }
        return source.stream().map(item -> convert(item, targetType)).collect(Collectors.toList());
    }

}
