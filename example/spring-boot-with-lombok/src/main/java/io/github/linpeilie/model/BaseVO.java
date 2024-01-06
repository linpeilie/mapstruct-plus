package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@Data
public class BaseVO {

    @AutoMapping(target = "success", targetClass = BaseDTO.class, ignore = true)
    private Integer success;

}
