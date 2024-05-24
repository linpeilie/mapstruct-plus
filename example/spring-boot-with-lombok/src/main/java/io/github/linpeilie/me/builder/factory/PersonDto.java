/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.builder.factory;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;

/**
 * @author Filip Hrisafov
 */
@AutoMappers({
    @AutoMapper(target = Person.class, uses = BuilderFactoryMapper.class, reverseConvertGenerate = false),
    @AutoMapper(target = ImplicitPerson.class, uses = BuilderImplicitFactoryMapper.class, reverseConvertGenerate = false),
})
public class PersonDto {

    private String name;

    public PersonDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
