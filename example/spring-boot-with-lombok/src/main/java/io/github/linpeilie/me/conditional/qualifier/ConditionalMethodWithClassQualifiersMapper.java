/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.conditional.qualifier;

import org.mapstruct.Condition;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 * @author Filip Hrisafov
 */
@Component
public class ConditionalMethodWithClassQualifiersMapper {

    @Condition
    public boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Condition
    @Named("american")
    public boolean isAmericanCitizen(EmployeeDto employerDto) {
        return "US".equals(employerDto.getCountry());
    }

    @Condition
    @Named("british")
    public boolean isBritishCitizen(EmployeeDto employeeDto) {
        return "UK".equals(employeeDto.getCountry());
    }

}
