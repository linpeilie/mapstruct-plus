/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.dependency;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import org.mapstruct.Mapping;

@AutoMapper(target = AddressDto.class, reverseConvertGenerate = false)
public class Address {

    @AutoMapping(target = "givenName")
    private String firstName;

    @AutoMapping(target = "middleName", dependsOn = "givenName")
    private String middleName;

    @AutoMapping(target = "surName", dependsOn = "middleName")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
