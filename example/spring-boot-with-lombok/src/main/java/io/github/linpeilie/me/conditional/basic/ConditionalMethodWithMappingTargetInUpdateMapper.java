/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.conditional.basic;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * @author Ben Zegveld
 */
@Component
public class ConditionalMethodWithMappingTargetInUpdateMapper {
    @Condition
    public boolean isNotBlankAndNotPresent(String value, @MappingTarget BasicEmployee1 targetEmployee) {
        return value != null && !value.trim().isEmpty() && targetEmployee.getName() == null;
    }
}
