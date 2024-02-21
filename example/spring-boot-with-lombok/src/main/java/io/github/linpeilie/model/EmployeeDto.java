package io.github.linpeilie.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class EmployeeDto {

    private String employeeName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EmployeeDto reportsTo;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<EmployeeDto> team;

}
