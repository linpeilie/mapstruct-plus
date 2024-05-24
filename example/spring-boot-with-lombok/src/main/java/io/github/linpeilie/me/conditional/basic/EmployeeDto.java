package io.github.linpeilie.me.conditional.basic;

import io.github.linpeilie.annotations.AutoMapper;

@AutoMapper(target = BasicEmployee.class, reverseConvertGenerate = false, uses = ConditionalMethodAndBeanPresenceCheckMapper.class)
public class EmployeeDto {
    private final String name;

    public EmployeeDto(String name) {
        this.name = name;
    }

    public boolean hasName() {
        return false;
    }

    public String getName() {
        return name;
    }

}
