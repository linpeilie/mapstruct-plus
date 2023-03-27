package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoEnumMapper;

@AutoEnumMapper("type")
public enum GoodsTypeEnum {

    PC(1);

    GoodsTypeEnum(final int type) {
        this.type = type;
    }

    private final int type;

    public int getType() {
        return type;
    }
}
