package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@Data
public class PDto {

    @AutoMapping(targetClass = PVO.class, expression = "java(source.getSuccess() ? 1 : 0)")
    private Boolean success;

}
