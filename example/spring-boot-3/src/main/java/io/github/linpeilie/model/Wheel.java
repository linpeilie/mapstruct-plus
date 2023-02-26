package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@AutoMapper(target = WheelDto.class)
@Data
public class Wheel {
    private WheelPosition position;
    private int diameter;
}
