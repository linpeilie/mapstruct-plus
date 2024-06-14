/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie;

import io.github.linpeilie.me.nullvaluepropertymapping.AddressDTO;
import io.github.linpeilie.me.nullvaluepropertymapping.AddressDTO1;
import io.github.linpeilie.me.nullvaluepropertymapping.AddressDTO2;
import io.github.linpeilie.me.nullvaluepropertymapping.Customer;
import io.github.linpeilie.me.nullvaluepropertymapping.CustomerDTO;
import io.github.linpeilie.me.nullvaluepropertymapping.CustomerDTO1;
import io.github.linpeilie.me.nullvaluepropertymapping.HomeDTO;
import io.github.linpeilie.me.nullvaluepropertymapping.HomeDTO1;
import io.github.linpeilie.me.nullvaluepropertymapping.UserDTO;
import io.github.linpeilie.me.nullvaluepropertymapping.UserDTO1;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class NullValuePropertyMappingTest {

    @Autowired
    private Converter converter;

    @Test
    public void testStrategyAppliedOnForgedMethod() {

        Customer customer = new Customer();
        customer.setAddress( null );

        UserDTO1 userDTO = new UserDTO1();
        userDTO.setHomeDTO( new HomeDTO1() );
        userDTO.getHomeDTO().setAddressDTO( new AddressDTO1() );
        userDTO.getHomeDTO().getAddressDTO().setHouseNo( 5 );
        userDTO.setDetails( Arrays.asList( "green hair" ) );

        converter.convert( customer, userDTO );

        assertThat( userDTO.getHomeDTO() ).isNotNull();
        assertThat( userDTO.getHomeDTO().getAddressDTO() ).isNotNull();
        assertThat( userDTO.getHomeDTO().getAddressDTO().getHouseNo() ).isEqualTo( 5 );
        assertThat( userDTO.getDetails() ).isNotNull();
        assertThat( userDTO.getDetails() ).containsExactly( "green hair" );
    }

    @Test
    public void testHierarchyIgnoreOnMapping() {
        Customer customer = new Customer();
        customer.setAddress( null );

        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setAddress( new AddressDTO1() );
        customerDto.getAddress().setHouseNo( 5 );
        customerDto.setDetails( Arrays.asList( "green hair" ) );

        converter.convert( customer, customerDto );

        assertThat( customerDto.getAddress() ).isNotNull();
        assertThat( customerDto.getAddress().getHouseNo() ).isEqualTo( 5 );
        assertThat( customerDto.getDetails() ).isNotNull();
        assertThat( customerDto.getDetails() ).containsExactly( "green hair" );
    }

    @Test
    public void testHierarchyIgnoreOnPropertyMappingMethod() {
        Customer customer = new Customer();
        customer.setAddress( null );

        CustomerDTO1 customerDto = new CustomerDTO1();
        customerDto.setAddress( new AddressDTO2() );
        customerDto.getAddress().setHouseNo( 5 );
        customerDto.setDetails( Arrays.asList( "green hair" ) );

        converter.convert( customer, customerDto );

        assertThat( customerDto.getAddress() ).isNotNull();
        assertThat( customerDto.getAddress().getHouseNo() ).isEqualTo( 5 );
        assertThat( customerDto.getDetails() ).isNotNull();
        assertThat( customerDto.getDetails() ).containsExactly( "green hair" );
    }

    @Test
    public void testStrategyDefaultAppliedOnForgedMethod() {

        Customer customer = new Customer();
        customer.setAddress( null );

        UserDTO userDTO = new UserDTO();
        userDTO.setHomeDTO( new HomeDTO() );
        userDTO.getHomeDTO().setAddressDTO( new AddressDTO() );
        userDTO.getHomeDTO().getAddressDTO().setHouseNo( 5 );
        userDTO.setDetails( Arrays.asList( "green hair" ) );

        converter.convert( customer, userDTO );

        assertThat( userDTO.getHomeDTO() ).isNotNull();
        assertThat( userDTO.getHomeDTO().getAddressDTO() ).isNotNull();
        assertThat( userDTO.getHomeDTO().getAddressDTO().getHouseNo() ).isNull();
        assertThat( userDTO.getDetails() ).isNotNull();
        assertThat( userDTO.getDetails() ).isEmpty();
    }

}
