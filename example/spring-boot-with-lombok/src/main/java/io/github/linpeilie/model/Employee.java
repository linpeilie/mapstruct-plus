package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.List;
import lombok.Data;

@Data
@AutoMapper(target = EmployeeDto.class)
public class Employee {

    private String name;
    private Employee reportsTo;
    private List<Employee> team;
    @AutoMapping(target = "b")
    private A a;

}
