package io.github.linpeilie.me.callbacks.typematching;

public class ElectricCarDto extends CarDto {
    private long batteryCapacity;

    public long getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(long batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
