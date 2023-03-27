package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@AutoEnumMapper("value")
public enum CharEnum {

    DEFAULT('c');

    private final char value;

}
