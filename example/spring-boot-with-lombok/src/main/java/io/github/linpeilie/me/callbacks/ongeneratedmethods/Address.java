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
@AutoMapper(target = AddressDto.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CompanyMapperPostProcessing.class)
public class Address {

    private String addressLine;
    private String town;

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
