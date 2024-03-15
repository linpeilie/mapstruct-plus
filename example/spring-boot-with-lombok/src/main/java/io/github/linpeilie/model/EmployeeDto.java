package io.github.linpeilie.model;

import java.util.List;
import lombok.Data;

@Data
public class EmployeeDto {

    private String employeeName;
    private EmployeeDto reportsTo;
    private List<EmployeeDto> team;

}
