package io.github.linpeilie.me.annotation.rams;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@CreateMapper
@AutoMapper(target = CarDTO4.class)
public class CarCreate4 {

    private String name;

    private String operatorUserId;

}
