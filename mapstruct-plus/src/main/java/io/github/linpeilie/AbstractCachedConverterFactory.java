package io.github.linpeilie;

import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCachedConverterFactory implements ConverterFactory {

    private final ConcurrentHashMap<String, BaseMapper> mapperMap = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> BaseMapper<S, T> getMapper(final Class<S> sourceType, final Class<T> targetType) {
        final String key = key(sourceType, targetType);
        if (mapperMap.contains(key)) {
            return mapperMap.get(key);
        }
        final BaseMapper mapper = findMapper(sourceType, targetType);
        if (mapper != null) {
            mapperMap.put(key, mapper);
            return mapper;
        }
        return null;
    }

    protected abstract <S, T> BaseMapper findMapper(final Class<S> source, final Class<T> target);

    private String key(Class<?> source, Class<?> target) {
        return source.getName() + "__" + target.getName();
    }

}
