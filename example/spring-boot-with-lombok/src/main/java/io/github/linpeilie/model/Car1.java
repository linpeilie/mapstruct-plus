package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.time.LocalDateTime;
import lombok.Data;

@AutoMapper(target = CarDto.class, imports = LocalDateTime.class)
@Data
public class Car1 {

    @AutoMapping(target = "seatMaterial", source = "seatConfiguration.seatMaterial", reverseConvertGenerate = false)
    private SeatConfiguration seatConfiguration;

}
