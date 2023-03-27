package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@AutoEnumMapper("value")
public enum FloatEnum {

    DEFAULT(0);

    private final float value;

}
