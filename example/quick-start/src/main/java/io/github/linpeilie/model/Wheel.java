package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.Objects;

@AutoMapper(target = WheelDto.class)
public class Wheel {
    private WheelPosition position;
    private int diameter;

    public WheelPosition getPosition() {
        return position;
    }

    public void setPosition(WheelPosition position) {
        this.position = position;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return diameter == wheel.diameter && position == wheel.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, diameter);
    }
}
