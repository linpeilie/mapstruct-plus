package io.github.linpeilie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Converter {

    private final ConverterFactory converterFactory;

    private boolean defaultCycleAvoiding;

    public boolean isDefaultCycleAvoiding() {
        return defaultCycleAvoiding;
    }

    public void setDefaultCycleAvoiding(boolean defaultCycleAvoiding) {
        this.defaultCycleAvoiding = defaultCycleAvoiding;
    }

    public Converter() {
        this.converterFactory = new DefaultConverterFactory();
    }

    public Converter(final ConverterFactory converterFactory) {
        this.converterFactory = converterFactory;
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, Class<T> targetType) {
        return convert(source, targetType, defaultCycleAvoiding);
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, Class<T> targetType, boolean cycleAvoiding) {
        if (source == null) {
            return null;
        }
        if (!cycleAvoiding) {
            BaseMapper<S, T> mapper = (BaseMapper<S, T>) converterFactory.getMapper(source.getClass(), targetType);
            if (mapper != null) {
                return mapper.convert(source);
            }
        } else {
            BaseCycleAvoidingMapper<S, T> mapper = (BaseCycleAvoidingMapper<S, T>) converterFactory
                .getCycleAvoidingMapper(source.getClass(), targetType);
            if (mapper != null) {
                return mapper.convert(source, new CycleAvoidingMappingContext());
            }
        }
        throw new ConvertException(
            "cannot find converter from " + source.getClass().getSimpleName() + " to " + targetType.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, T target) {
        return convert(source, target, defaultCycleAvoiding);
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, T target, boolean cycleAvoiding) {
        if (source == null) {
            return null;
        }
        if (target == null) {
            return null;
        }
        if (!cycleAvoiding) {
            BaseMapper<S, T> mapper = (BaseMapper<S, T>) converterFactory.getMapper(source.getClass(), target.getClass());
            if (mapper != null) {
                return mapper.convert(source, target);
            }
        } else {
            BaseCycleAvoidingMapper<S, T> mapper = (BaseCycleAvoidingMapper<S, T>) converterFactory
                .getCycleAvoidingMapper(source.getClass(), target.getClass());
            if (mapper != null) {
                return mapper.convert(source, target, new CycleAvoidingMappingContext());
            }
        }
        throw new ConvertException("cannot find converter from " + source.getClass().getSimpleName() + " to " +
                                   target.getClass().getSimpleName());
    }

    public <S, T> List<T> convert(List<S> source, Class<T> targetType) {
        return convert(source, targetType, defaultCycleAvoiding);
    }

    public <S, T> List<T> convert(List<S> source, Class<T> targetType, boolean cycleAvoiding) {
        if (source == null || source.size() == 0) {
            return new ArrayList<>();
        }
        return source.stream().map(item -> convert(item, targetType, cycleAvoiding)).collect(Collectors.toList());
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
