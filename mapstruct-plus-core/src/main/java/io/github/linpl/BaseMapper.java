package io.github.linpl;

import org.mapstruct.MappingTarget;

public interface BaseMapper<S, T> {

    T convert(S source);

    T convert(S source, @MappingTarget T target);

}
