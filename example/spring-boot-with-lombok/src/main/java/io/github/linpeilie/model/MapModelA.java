package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapMapper;
import lombok.Data;

@Data
@AutoMapMapper
public class MapModelA {

    private String str;
    private int i1;
    private Long l2;
    private MapModelB mapModelB;

}
