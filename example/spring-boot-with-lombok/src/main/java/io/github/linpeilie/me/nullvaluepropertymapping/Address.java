/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.nullvaluepropertymapping;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

@AutoMappers({
    @AutoMapper(target = AddressDTO.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT),
    @AutoMapper(target = AddressDTO1.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
    @AutoMapper(target = AddressDTO2.class)
})
public class Address {

    @AutoMappings({
        @AutoMapping(targetClass = AddressDTO.class, target = "houseNo", defaultValue = "0"),
        @AutoMapping(targetClass = AddressDTO1.class, target = "houseNo"),
        @AutoMapping(targetClass = AddressDTO2.class, target = "houseNo")
    })
    private Integer houseNumber;

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
}
