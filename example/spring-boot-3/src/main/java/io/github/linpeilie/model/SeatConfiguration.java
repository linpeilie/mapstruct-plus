package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@AutoMapper(target = SeatConfigurationDto.class)
public class SeatConfiguration {
    private int numberOfSeats;
    private SeatMaterial seatMaterial;

}
