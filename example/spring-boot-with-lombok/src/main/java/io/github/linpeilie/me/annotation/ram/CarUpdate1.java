package io.github.linpeilie.me.annotation.ram;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@UpdateMapper
@AutoMapper(target = CarDTO1.class)
public class CarUpdate1 {

    private String name;

    private String operatorUserId;

}
