package io.github.linpeilie.me.callbacks.typematching;

public class ElectricCarEntity extends Identifiable {
    private long batteryCapacity;

    public long getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(long batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
