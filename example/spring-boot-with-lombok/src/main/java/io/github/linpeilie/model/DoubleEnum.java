package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@AutoEnumMapper("value")
public enum DoubleEnum {

    DEFAULT(0);

    private final double value;

}
