package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@AutoMapper(target = BB.class)
@Data
public class AA extends A {

    private String name;

}
