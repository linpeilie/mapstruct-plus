package io.github.linpeilie.model;

public class Goods {

    private String price;

    private String takeDownTime;

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

    @Override
    public String toString() {
        return "Goods{" +
               "price='" + price + '\'' +
               ", takeDownTime='" + takeDownTime + '\'' +
               '}';
    }
}
