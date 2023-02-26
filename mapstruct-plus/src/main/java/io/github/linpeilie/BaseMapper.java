package io.github.linpeilie;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.MappingTarget;

public interface BaseMapper<S, T> {

    T convert(S source);

    T convert(S source, @MappingTarget T target);

    default List<T> convert(List<S> sourceList) {
        return sourceList.stream().map(this::convert).collect(Collectors.toList());
    }

}
