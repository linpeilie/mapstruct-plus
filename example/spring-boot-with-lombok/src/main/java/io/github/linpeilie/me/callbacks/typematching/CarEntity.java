package io.github.linpeilie.me.callbacks.typematching;

public class CarEntity extends Identifiable {
    private int seatCount;

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
