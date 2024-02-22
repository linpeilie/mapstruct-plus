package io.github.linpeilie;

import cn.hutool.core.collection.CollectionUtil;
import io.github.linpeilie.annotations.DoIgnore;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;

public interface BaseCycleAvoidingMapper<S, T> extends BaseMapper<S, T> {

    @DoIgnore
    T convertWithCycle(S source, @Context CycleAvoidingMappingContext context);

    @DoIgnore
    T convertWithCycle(S source, @MappingTarget T target, @Context CycleAvoidingMappingContext context);

    List<T> convertWithCycle(List<S> sourceList, @Context CycleAvoidingMappingContext context);

    @Override
    @DoIgnore
    default List<T> convert(List<S> sourceList) {
        return convertWithCycle(sourceList, new CycleAvoidingMappingContext());
    }

    @Override
    @DoIgnore
    default T convert(S source) {
        return convertWithCycle(source, new CycleAvoidingMappingContext());
    }

    @Override
    @DoIgnore
    default T convert(S source, T target) {
        return convertWithCycle(source, new CycleAvoidingMappingContext());
    }
}
