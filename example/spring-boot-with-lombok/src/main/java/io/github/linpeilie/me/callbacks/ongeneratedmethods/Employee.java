/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.callbacks.ongeneratedmethods;

import io.github.linpeilie.annotations.AutoMapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Sjaak Derksen
 */
@AutoMapper(target = EmployeeDto.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CompanyMapperPostProcessing.class, mapperNameSuffix = "$1")
public class Employee {

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
