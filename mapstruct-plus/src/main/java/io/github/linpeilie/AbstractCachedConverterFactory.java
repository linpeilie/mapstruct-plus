package io.github.linpeilie;

import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCachedConverterFactory implements ConverterFactory {

    private final ConcurrentHashMap<String, BaseMapper> mapperMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, BaseCycleAvoidingMapper> cycleAvoidingMapper = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, BaseMapMapper> mapMapperMap = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> BaseMapper<S, T> getMapper(final Class<S> sourceType, final Class<T> targetType) {
        Class<?> source = wrapperClass(sourceType);
        Class<?> target = wrapperClass(targetType);
        String key = key(source, target);
        if (mapperMap.containsKey(key)) {
            return mapperMap.get(key);
        }
        BaseMapper mapper = findMapper(source, target);
        if (mapper != null) {
            mapperMap.put(key, mapper);
            return mapper;
        }
        return null;
    }

    @Override
    public <S, T> BaseCycleAvoidingMapper<S, T> getCycleAvoidingMapper(Class<S> sourceType, Class<T> targetType) {
        BaseMapper<S, T> mapper = getMapper(sourceType, targetType);
        if (mapper == null) {
            return null;
        }
        if (mapper instanceof BaseCycleAvoidingMapper) {
            return (BaseCycleAvoidingMapper<S, T>) mapper;
        }
        return null;
    }

    @Override
    public <S> BaseMapMapper<S> getMapMapper(final Class<S> sourceType) {
        final Class<?> source = wrapperClass(sourceType);
        final String key = source.getName();
        if (mapMapperMap.containsKey(key)) {
            return mapMapperMap.get(key);
        }
        final BaseMapMapper mapper = findMapMapper(source);
        if (mapper != null) {
            mapMapperMap.put(key, mapper);
            return mapper;
        }
        return null;
    }

    protected Class<?> wrapperClass(final Class<?> clazz) {
        return clazz;
    }

    protected abstract <S, T> BaseMapper findMapper(Class<S> source, Class<T> target);

    protected abstract <S> BaseMapMapper findMapMapper(final Class<?> source);

    private String key(Class<?> source, Class<?> target) {
        return source.getName() + "__" + target.getName();
    }

}
