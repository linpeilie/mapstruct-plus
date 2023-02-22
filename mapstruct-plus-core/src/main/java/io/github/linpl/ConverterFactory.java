package io.github.linpl;

public interface ConverterFactory {

    <S, T> BaseMapper<S, T> getMapper(Class<S> sourceType, Class<T> targetType);

}
