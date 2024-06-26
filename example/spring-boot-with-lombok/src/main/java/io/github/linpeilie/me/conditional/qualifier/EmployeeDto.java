/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.conditional.qualifier;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;

@AutoMapper(target = Employee.class,
    reverseConvertGenerate = false,
    uses = ConditionalMethodWithClassQualifiersMapper.class)
public class EmployeeDto {

    private String name;
    private String country;

    @AutoMappings({
        @AutoMapping(target = "ssid", conditionQualifiedByName = "american"),
        @AutoMapping(target = "nin", conditionQualifiedByName = "british")
    })
    private String uniqueIdNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUniqueIdNumber() {
        return uniqueIdNumber;
    }

    public void setUniqueIdNumber(String uniqueIdNumber) {
        this.uniqueIdNumber = uniqueIdNumber;
    }
}
