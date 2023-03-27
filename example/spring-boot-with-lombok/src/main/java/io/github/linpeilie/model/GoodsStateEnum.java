package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoEnumMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@AutoEnumMapper("state")
public enum GoodsStateEnum {
    ENABLED(1, "启用"),
    DISABLED(0, "禁用");

    private final Integer state;
    private final String desc;

}
