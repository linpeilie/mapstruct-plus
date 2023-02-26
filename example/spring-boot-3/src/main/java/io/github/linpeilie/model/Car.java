package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@AutoMapper(target = CarDto.class)
@Data
public class Car {

    private String make;
    private SeatConfiguration seatConfiguration;
    private CarType type;

    @AutoMapping(target = "wheels", ignore = true)
    private Wheels wheels;

}
