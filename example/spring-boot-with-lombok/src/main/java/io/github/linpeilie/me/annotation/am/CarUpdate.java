package io.github.linpeilie.me.annotation.am;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@UpdateMapper
@AutoMapper(target = CarDTO.class)
public class CarUpdate {

    private String name;

    private String operatorUserId;

}
