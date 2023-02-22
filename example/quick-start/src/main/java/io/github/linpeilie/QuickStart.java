package io.github.linpeilie;

import io.github.linpeilie.model.Car;
import io.github.linpeilie.model.CarDto;
import io.github.linpeilie.model.CarType;

public class QuickStart {

    private static Converter converter = new Converter();

    public static void main(String[] args) {

        final Car car = new Car();
        car.setType(CarType.OTHER);

        final CarDto carDto = converter.convert(car, CarDto.class);
        System.out.println(carDto);
    }

}