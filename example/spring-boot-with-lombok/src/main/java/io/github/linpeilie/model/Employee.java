package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;
import lombok.Data;

@Data
@AutoMapper(target = EmployeeDto.class, cycleAvoiding = true)
public class Employee {

    private String name;
    private Employee reportsTo;
    private List<Employee> team;

}
