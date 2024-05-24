/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullvaluepropertymapping;

import java.util.List;

public class CustomerDTO {

    private AddressDTO1 address;
    private List<String> details;

    public AddressDTO1 getAddress() {
        return address;
    }

    public void setAddress(AddressDTO1 address) {
        this.address = address;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
