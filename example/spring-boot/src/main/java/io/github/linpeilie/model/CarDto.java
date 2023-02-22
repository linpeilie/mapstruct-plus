package io.github.linpeilie.model;

import java.util.List;

public class CarDto {
    private String make;
    private SeatConfigurationDto seatConfiguration;
    private String type;
    private List<WheelDto> wheels;

    public String getMake() {
        return make;
    }

    public void setMake(final String make) {
        this.make = make;
    }

    public SeatConfigurationDto getSeatConfiguration() {
        return seatConfiguration;
    }

    public void setSeatConfiguration(final SeatConfigurationDto seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public List<WheelDto> getWheels() {
        return wheels;
    }

    public void setWheels(List<WheelDto> wheels) {
        this.wheels = wheels;
    }

    @Override
    public String toString() {
        return "CarDto{" +
               "make='" + make + '\'' +
               ", seats=" + seatConfiguration +
               ", type='" + type + '\'' +
               ", wheels=" + wheels +
               '}';
    }
}
