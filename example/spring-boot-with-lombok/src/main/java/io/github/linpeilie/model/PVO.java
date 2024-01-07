package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@Data
public class PVO {

    @AutoMapping(targetClass = PDto.class, expression = "java(source.getSuccess().equals(1) ? true : false)")
    private Integer success;

}
