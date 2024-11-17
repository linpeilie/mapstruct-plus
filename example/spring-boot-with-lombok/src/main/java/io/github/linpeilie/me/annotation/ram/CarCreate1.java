package io.github.linpeilie.me.annotation.ram;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@CreateMapper
@AutoMapper(target = CarDTO1.class)
public class CarCreate1 {

    private String name;

    private String operatorUserId;

}
