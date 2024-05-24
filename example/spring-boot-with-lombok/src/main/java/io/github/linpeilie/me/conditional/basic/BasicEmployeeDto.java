/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.conditional.basic;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author Filip Hrisafov
 */
@AutoMappers({
    @AutoMapper(target = BasicEmployee.class, reverseConvertGenerate = false, uses = ConditionalMethodInMapper.class),
    @AutoMapper(target = BasicEmployee1.class, reverseConvertGenerate = false, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = ConditionalMethodWithMappingTargetInUpdateMapper.class),
    @AutoMapper(target = BasicEmployee2.class, reverseConvertGenerate = false, uses = ConditionalMethodWithSourceParameterAndValueMapper.class),
    @AutoMapper(target = BasicEmployee3.class, reverseConvertGenerate = false, uses = ConditionalMethodWithSourceParameterMapper.class),
    @AutoMapper(target = BasicEmployee4.class, reverseConvertGenerate = false, uses = ConditionalMethodWithTargetType.class),
})
public class BasicEmployeeDto {

    private final String name;
    private final String strategy;

    public BasicEmployeeDto(String name) {
        this(name, "default");
    }

    public BasicEmployeeDto(String name, String strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public String getStrategy() {
        return strategy;
    }
}
