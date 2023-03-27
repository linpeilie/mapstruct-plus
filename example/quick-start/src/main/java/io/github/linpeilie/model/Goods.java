package io.github.linpeilie.model;

public class Goods {

    private String price;

    private String takeDownTime;

    private GoodsStateEnum state;

    private GoodsTypeEnum type;

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getTakeDownTime() {
        return takeDownTime;
    }

    public void setTakeDownTime(final String takeDownTime) {
        this.takeDownTime = takeDownTime;
    }

    public GoodsStateEnum getState() {
        return state;
    }

    public void setState(final GoodsStateEnum state) {
        this.state = state;
    }

    public GoodsTypeEnum getType() {
        return type;
    }

    public void setType(final GoodsTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Goods{" +
               "price='" + price + '\'' +
               ", takeDownTime='" + takeDownTime + '\'' +
               ", state=" + state +
               ", type=" + type +
               '}';
    }
}
