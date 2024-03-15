package io.github.linpeilie;

public interface ConverterFactory {

    <S, T> BaseMapper<S, T> getMapper(Class<S> sourceType, Class<T> targetType);

    <S, T> BaseCycleAvoidingMapper<S, T> getCycleAvoidingMapper(Class<S> sourceType, Class<T> targetType);

    <S> BaseMapMapper<S> getMapMapper(Class<S> sourceType);

}
