package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@AutoMapper(target = Employee1Dto.class)
public class Employee1 {

    private Employee employee;

}
