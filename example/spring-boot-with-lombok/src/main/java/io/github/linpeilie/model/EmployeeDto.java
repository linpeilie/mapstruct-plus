package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;
import lombok.Data;

@Data
@AutoMapper(target = Employee.class)
public class EmployeeDto {

    private String employeeName;
    private EmployeeDto reportsTo;
    private List<EmployeeDto> team;

}
