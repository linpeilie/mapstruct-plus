package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@Data
public class A {

    @AutoMapping(target = "createBy.id")
    private Long createBy;

}
