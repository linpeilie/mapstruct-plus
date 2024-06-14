/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullvaluepropertymapping;

public class HomeDTO1 {

    private AddressDTO1 addressDTO;

    public AddressDTO1 getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO1 addressDTO) {
        this.addressDTO = addressDTO;
    }
}
