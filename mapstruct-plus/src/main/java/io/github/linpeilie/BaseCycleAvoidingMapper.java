package io.github.linpeilie;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;

public interface BaseCycleAvoidingMapper<S, T> extends BaseMapper<S, T> {

    T convert(S source, @Context CycleAvoidingMappingContext context);

    T convert(S source, @MappingTarget T target, @Context CycleAvoidingMappingContext context);

    default List<T> convert(List<S> sourceList, @Context CycleAvoidingMappingContext context) {
        return sourceList.stream()
            .map(item -> convert(item, context))
            .collect(Collectors.toList());
    }

    @Override
    default T convert(S source) {
        return convert(source, new CycleAvoidingMappingContext());
    }

    @Override
    default T convert(S source, @MappingTarget T target) {
        return convert(source, new CycleAvoidingMappingContext());
    }


    @Override
    default List<T> convert(List<S> sourceList) {
        return convert(sourceList, new CycleAvoidingMappingContext());
    }

}
