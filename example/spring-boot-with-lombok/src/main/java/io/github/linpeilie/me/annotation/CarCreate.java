package io.github.linpeilie.me.annotation;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@CreateMapper
@AutoMapper(target = CarDTO.class)
public class CarCreate {

    private String name;

    private String operatorUserId;

}
