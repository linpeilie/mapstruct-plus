package io.github.linpeilie;

import io.github.linpeilie.annotations.DoIgnore;
import io.github.linpeilie.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.MappingTarget;

public interface BaseMapper<S, T> {

    @DoIgnore
    T convert(S source);

    @DoIgnore
    T convert(S source, @MappingTarget T target);

    @DoIgnore
    default List<T> convert(List<S> sourceList) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return sourceList.stream().map(this::convert).collect(Collectors.toList());
    }

}
