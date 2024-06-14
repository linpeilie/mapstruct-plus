/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.complex.source;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.me.complex._target.PersonDto;
import io.github.linpeilie.me.complex.other.DateMapper;

@AutoMapper(target = PersonDto.class, uses = DateMapper.class)
public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
