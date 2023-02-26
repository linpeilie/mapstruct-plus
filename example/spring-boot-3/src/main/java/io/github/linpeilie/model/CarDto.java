package io.github.linpeilie.model;

import java.util.List;
import lombok.Data;

@Data
public class CarDto {
    private String make;
    private SeatConfigurationDto seatConfiguration;
    private String type;
    private List<WheelDto> wheels;

}
