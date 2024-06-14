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
import java.util.List;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@AutoMappers({
    @AutoMapper(target = UserDTO.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
        reverseConvertGenerate = false),
    @AutoMapper(target = UserDTO1.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        reverseConvertGenerate = false),
    @AutoMapper(target = CustomerDTO.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        reverseConvertGenerate = false),
    @AutoMapper(target = CustomerDTO1.class,
        reverseConvertGenerate = false)
})
public class Customer {

    @AutoMappings({
        @AutoMapping(targetClass = UserDTO.class, target = "homeDTO.addressDTO"),
        @AutoMapping(targetClass = UserDTO1.class, target = "homeDTO.addressDTO"),
        @AutoMapping(targetClass = CustomerDTO1.class, target = "address", nullValuePropertyMappingStrategy = IGNORE),
    })
    private Address address;

    @AutoMapping(targetClass = CustomerDTO1.class, target = "details", nullValuePropertyMappingStrategy = IGNORE)
    private List<String> details;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
