package io.github.linpeilie.me.annotation.ams;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@CreateMapper
@AutoMapper(target = CarDTO2.class)
public class CarCreate2 {

    private String name;

    private String operatorUserId;

}
