package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.Date;

@AutoMapper(target = Goods.class)
public class GoodsDto {

    @AutoMapping(target = "takeDownTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date takeDownTime;

    @AutoMapping(target = "price", numberFormat = "$#.00")
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public Date getTakeDownTime() {
        return takeDownTime;
    }

    public void setTakeDownTime(final Date takeDownTime) {
        this.takeDownTime = takeDownTime;
    }

    @Override
    public String toString() {
        return "GoodsDto{" +
               "takeDownTime=" + takeDownTime +
               ", price=" + price +
               '}';
    }
}
