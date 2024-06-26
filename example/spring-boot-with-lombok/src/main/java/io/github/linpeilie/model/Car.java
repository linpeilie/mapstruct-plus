package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.time.LocalDateTime;
import lombok.Data;

@AutoMapper(target = CarDto.class, imports = LocalDateTime.class)
@Data
public class Car {

    private String make;
    @AutoMapping(target = "seatMaterial", source = "seatConfiguration.seatMaterial")
    private SeatConfiguration seatConfiguration;
    private CarType type;
    private Tyre tyre;

    @AutoMapping(target = "wheels", ignore = true)
    private Wheels wheels;

    @Data
    public static class InnerClass {
        private String f;
    }

}
