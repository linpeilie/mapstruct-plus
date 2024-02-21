package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AutoMapper(target = Employee.class)
public class EmployeeDto {

    private String employeeName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EmployeeDto reportsTo;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<EmployeeDto> team;
    @AutoMapping(target = "a")
    private B b;

}
