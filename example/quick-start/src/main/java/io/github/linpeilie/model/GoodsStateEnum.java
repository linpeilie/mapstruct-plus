package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoEnumMapper;

@AutoEnumMapper("state")
public enum GoodsStateEnum {
    ENABLED(1, "启用"),
    DISABLED(0, "禁用");

    GoodsStateEnum(final Integer state, final String desc) {
        this.state = state;
        this.desc = desc;
    }

    private final Integer state;
    private final String desc;

    public Integer getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }
}
