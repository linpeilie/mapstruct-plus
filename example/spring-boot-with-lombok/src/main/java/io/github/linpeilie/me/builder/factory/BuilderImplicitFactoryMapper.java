/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.builder.factory;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * @author Filip Hrisafov
 */
@Component
public class BuilderImplicitFactoryMapper {

    public ImplicitPerson.PersonBuilder personBuilder() {
        return new ImplicitPerson.PersonBuilder("Implicit Factory");
    }

}
