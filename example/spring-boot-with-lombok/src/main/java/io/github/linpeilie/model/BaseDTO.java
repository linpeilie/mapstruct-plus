package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@Data
public class BaseDTO {

    @AutoMapping(target = "success", targetClass = BaseVO.class, ignore = true)
    private Boolean success;

}
