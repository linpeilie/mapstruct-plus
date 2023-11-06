package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@AutoMapper(target = Car.InnerClass.class)
public class InnerClassTarget {

    private String f;

}
