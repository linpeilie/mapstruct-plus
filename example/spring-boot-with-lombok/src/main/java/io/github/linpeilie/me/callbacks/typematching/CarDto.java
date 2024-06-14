package io.github.linpeilie.me.callbacks.typematching;

import io.github.linpeilie.annotations.AutoMapper;

@AutoMapper(target = CarEntity.class, uses = CarMapper.class)
public class CarDto extends Identifiable {
    private int seatCount;

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
