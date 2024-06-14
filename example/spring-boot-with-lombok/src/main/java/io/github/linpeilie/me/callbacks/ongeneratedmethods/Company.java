/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.callbacks.ongeneratedmethods;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;
import org.mapstruct.ReportingPolicy;

/**
 * @author Sjaak Derksen
 */
@AutoMapper(target = CompanyDto.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CompanyMapperPostProcessing.class)
public class Company {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
