package io.github.linpeilie.model;

public class SeatConfigurationDto {
    private int seatCount;
    private String material;

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(final int seatCount) {
        this.seatCount = seatCount;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(final String material) {
        this.material = material;
    }
}
