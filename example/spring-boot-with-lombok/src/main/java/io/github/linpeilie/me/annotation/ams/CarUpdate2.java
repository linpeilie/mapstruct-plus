package io.github.linpeilie.me.annotation.ams;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@UpdateMapper
@AutoMapper(target = CarDTO2.class)
public class CarUpdate2 {

    private String name;

    private String operatorUserId;

}
