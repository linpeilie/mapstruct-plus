package io.github.linpeilie;

import cn.hutool.core.collection.CollectionUtil;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseCycleAvoidingMapper<S, T> {

    T convert(S source, @Context CycleAvoidingMappingContext context);

    T convert(S source, @MappingTarget T target, @Context CycleAvoidingMappingContext context);

    default List<T> convert(List<S> sourceList, @Context CycleAvoidingMappingContext context) {
        if (CollectionUtil.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return sourceList.stream().map(source -> convert(source, context)).collect(Collectors.toList());
    }

}
