/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.dependency.Address;
import io.github.linpeilie.me.dependency.AddressDto;
import io.github.linpeilie.me.dependency.Person;
import io.github.linpeilie.me.dependency.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class OrderingTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldApplyChainOfDependencies() {
        Address source = new Address();
        source.setFirstName("Bob");
        source.setMiddleName("J.");
        source.setLastName("McRobb");

        AddressDto target = converter.convert(source, AddressDto.class);

        assertThat(target).isNotNull();
        assertThat(target.getFullName()).isEqualTo("Bob J. McRobb");
    }

    @Test
    public void shouldApplySeveralDependenciesConfiguredForOneProperty() {
        Person source = new Person();
        source.setFirstName("Bob");
        source.setMiddleName("J.");
        source.setLastName("McRobb");

        PersonDto target = converter.convert(source, PersonDto.class);

        assertThat(target).isNotNull();
        assertThat(target.getFullName()).isEqualTo("Bob J. McRobb");
    }

}
