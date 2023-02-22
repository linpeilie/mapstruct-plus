package io.github.linpeilie;

public interface ConverterFactory {

    <S, T> BaseMapper<S, T> getMapper(Class<S> sourceType, Class<T> targetType);

}
