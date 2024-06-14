/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.conditional.basic;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class ConditionalMethodWithSourceParameterAndValueMapper {

    @Condition
    public boolean isPresent(BasicEmployeeDto source, String value) {
        if ( value == null ) {
            return false;
        }
        switch ( source.getStrategy() ) {
            case "blank":
                return !value.trim().isEmpty();
            case "empty":
                return !value.isEmpty();
            default:
                return true;
        }
    }

}
