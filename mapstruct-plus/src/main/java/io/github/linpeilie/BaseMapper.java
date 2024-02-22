package io.github.linpeilie;

import cn.hutool.core.collection.CollectionUtil;
import io.github.linpeilie.annotations.DoIgnore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.MappingTarget;

public interface BaseMapper<S, T> {

    @DoIgnore
    T convert(S source);

    @DoIgnore
    T convert(S source, @MappingTarget T target);

    default List<T> convert(List<S> sourceList) {
        if (CollectionUtil.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return sourceList.stream().map(this::convert).collect(Collectors.toList());
    }

}
