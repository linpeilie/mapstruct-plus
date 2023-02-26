package io.github.linpeilie;

import io.github.linpeilie.model.Car;
import io.github.linpeilie.model.CarDto;
import io.github.linpeilie.model.CarType;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartUp {

    private Converter converter;

    @Autowired
    public void setConverter(final Converter converter) {
        this.converter = converter;
    }

    @PostConstruct
    public void start() {
        final Car car = new Car();
        car.setType(CarType.OTHER);

        final CarDto carDto = converter.convert(car, CarDto.class);
        System.out.println(carDto);
    }

}
