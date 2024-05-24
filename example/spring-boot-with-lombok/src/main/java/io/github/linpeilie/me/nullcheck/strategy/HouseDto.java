/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.nullcheck.strategy;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@AutoMappers({
    @AutoMapper(target = HouseEntity.class, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS),
    @AutoMapper(target = HouseEntity1.class, nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION),
    @AutoMapper(target = HouseEntity2.class, nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION),
})
public class HouseDto {

    private String owner;

    @AutoMapping(targetClass = HouseEntity2.class, target = "number", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    private Integer number;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
