package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.List;

import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AutoMapper(target = EmployeeDto.class)
public class Employee {

    @AutoMapping(target = "employeeName", source = "name")
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee reportsTo;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Employee> team;
    @AutoMapping(target = "b")
    private A a;

}
