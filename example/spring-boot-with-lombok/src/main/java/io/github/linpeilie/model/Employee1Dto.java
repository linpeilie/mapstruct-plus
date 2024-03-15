package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@AutoMapper(target = Employee1.class)
public class Employee1Dto {

    private EmployeeDto employee;

}
