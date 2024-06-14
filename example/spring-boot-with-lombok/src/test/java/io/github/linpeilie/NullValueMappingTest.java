/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.nullvaluemapping._target.CarDto;
import io.github.linpeilie.me.nullvaluemapping.source.Car;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class NullValueMappingTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldMapExpressionAndConstantRegardlessNullArg() {
        //given
        Car car = new Car("Morris", 2);

        //when
        CarDto carDto1 = converter.convert(car, CarDto.class);

        //then
        assertThat(carDto1).isNotNull();
        assertThat(carDto1.getMake()).isEqualTo(car.getMake());
        assertThat(carDto1.getSeatCount()).isEqualTo(car.getNumberOfSeats());
        assertThat(carDto1.getModel()).isEqualTo("ModelT");
        assertThat(carDto1.getCatalogId()).isNotEmpty();
    }

    @Test
    public void shouldMapIterableWithNullArg() {

        //given
        Car car = new Car("Morris", 2);

        //when
        List<CarDto> carDtos1 = converter.convert(Arrays.asList(car), CarDto.class);

        //then
        assertThat(carDtos1).isNotNull();
        assertThat(carDtos1.size()).isEqualTo(1);

    }

}
