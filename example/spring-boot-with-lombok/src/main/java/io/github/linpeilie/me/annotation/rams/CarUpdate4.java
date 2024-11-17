package io.github.linpeilie.me.annotation.rams;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@UpdateMapper
@AutoMapper(target = CarDTO4.class)
public class CarUpdate4 {

    private String name;

    private String operatorUserId;

}
