package io.github.linpl.mapstruct;

import io.github.linpl.Converter;
import io.github.linpl.mapstruct.dto.Car;
import io.github.linpl.mapstruct.dto.CarDto;
import io.github.linpl.mapstruct.dto.CarType;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class Startup {

    private Converter converter;

    @PostConstruct
    public void start() {
        Car car = new Car();
        car.setType(CarType.OTHER);
        CarDto catDto = converter.convert(car, CarDto.class);
        System.out.println(catDto);
    }

    @Autowired
    public void setConverter(final Converter converter) {
        this.converter = converter;
    }
}
