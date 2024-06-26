/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.complex._target.CarDto;
import io.github.linpeilie.me.complex._target.PersonDto;
import io.github.linpeilie.me.complex.source.Car;
import io.github.linpeilie.me.complex.source.Category;
import io.github.linpeilie.me.complex.source.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class CarMapperTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldMapAttributeByName() {
        //given
        Car car = new Car(
            "Morris",
            2,
            new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime(),
            new Person("Bob"),
            new ArrayList<>()
        );

        //when
        CarDto carDto = converter.convert(car, CarDto.class);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getMake()).isEqualTo(car.getMake());
    }

    @Test
    public void shouldMapReferenceAttribute() {
        //given
        Car car = new Car(
            "Morris",
            2,
            new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime(),
            new Person("Bob"),
            new ArrayList<>()
        );

        //when
        CarDto carDto = converter.convert(car, CarDto.class);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getDriver()).isNotNull();
        assertThat(carDto.getDriver().getName()).isEqualTo("Bob");
    }

    @Test
    public void shouldReverseMapReferenceAttribute() {
        //given
        CarDto carDto = new CarDto("Morris", 2, "1980", new PersonDto("Bob"), new ArrayList<>());

        //when
        Car car = converter.convert(carDto, Car.class);

        //then
        assertThat(car).isNotNull();
        assertThat(car.getDriver()).isNotNull();
        assertThat(car.getDriver().getName()).isEqualTo("Bob");
    }

    @Test
    public void shouldMapAttributeWithCustomMapping() {
        //given
        Car car = new Car(
            "Morris",
            2,
            new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime(),
            new Person("Bob"),
            new ArrayList<>()
        );

        //when
        CarDto carDto = converter.convert(car, CarDto.class);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getSeatCount()).isEqualTo(car.getNumberOfSeats());
    }

    @Test
    public void shouldConsiderCustomMappingForReverseMapping() {
        //given
        CarDto carDto = new CarDto("Morris", 2, "1980", new PersonDto("Bob"), new ArrayList<>());

        //when
        Car car = converter.convert(carDto, Car.class);

        //then
        assertThat(car).isNotNull();
        assertThat(car.getNumberOfSeats()).isEqualTo(carDto.getSeatCount());
    }

    @Test
    public void shouldApplyConverter() {
        //given
        Car car = new Car(
            "Morris",
            2,
            new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime(),
            new Person("Bob"),
            new ArrayList<>()
        );

        //when
        CarDto carDto = converter.convert(car, CarDto.class);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getManufacturingYear()).isEqualTo("1980");
    }

    @Test
    public void shouldApplyConverterForReverseMapping() {
        //given
        CarDto carDto = new CarDto("Morris", 2, "1980", new PersonDto("Bob"), new ArrayList<>());

        //when
        Car car = converter.convert(carDto, Car.class);

        //then
        assertThat(car).isNotNull();
        assertThat(car.getManufacturingDate()).isEqualTo(
            new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime()
        );
    }

    @Test
    public void shouldMapIterable() {
        //given
        Car car1 = new Car(
            "Morris",
            2,
            new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime(),
            new Person("Bob"),
            new ArrayList<>()
        );
        Car car2 = new Car(
            "Railton",
            4,
            new GregorianCalendar(1934, Calendar.JANUARY, 1).getTime(),
            new Person("Bill"),
            new ArrayList<>()
        );

        //when
        List<CarDto> dtos = converter.convert(Arrays.asList(car1, car2), CarDto.class);

        //then
        assertThat(dtos).isNotNull();
        assertThat(dtos).hasSize(2);

        assertThat(dtos.get(0).getMake()).isEqualTo("Morris");
        assertThat(dtos.get(0).getSeatCount()).isEqualTo(2);
        assertThat(dtos.get(0).getManufacturingYear()).isEqualTo("1980");
        assertThat(dtos.get(0).getDriver().getName()).isEqualTo("Bob");

        assertThat(dtos.get(1).getMake()).isEqualTo("Railton");
        assertThat(dtos.get(1).getSeatCount()).isEqualTo(4);
        assertThat(dtos.get(1).getManufacturingYear()).isEqualTo("1934");
        assertThat(dtos.get(1).getDriver().getName()).isEqualTo("Bill");
    }

    @Test
    public void shouldReverseMapIterable() {
        //given
        CarDto car1 = new CarDto("Morris", 2, "1980", new PersonDto("Bob"), new ArrayList<>());
        CarDto car2 = new CarDto("Railton", 4, "1934", new PersonDto("Bill"), new ArrayList<>());

        //when
        List<Car> cars = converter.convert(Arrays.asList(car1, car2), Car.class);

        //then
        assertThat(cars).isNotNull();
        assertThat(cars).hasSize(2);

        assertThat(cars.get(0).getMake()).isEqualTo("Morris");
        assertThat(cars.get(0).getNumberOfSeats()).isEqualTo(2);
        assertThat(cars.get(0).getManufacturingDate()).isEqualTo(
            new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime()
        );
        assertThat(cars.get(0).getDriver().getName()).isEqualTo("Bob");

        assertThat(cars.get(1).getMake()).isEqualTo("Railton");
        assertThat(cars.get(1).getNumberOfSeats()).isEqualTo(4);
        assertThat(cars.get(1).getManufacturingDate()).isEqualTo(
            new GregorianCalendar(1934, Calendar.JANUARY, 1).getTime()
        );
        assertThat(cars.get(1).getDriver().getName()).isEqualTo("Bill");
    }

    @Test
    public void shouldMapIterableAttribute() {
        //given
        Car car = new Car(
            "Morris",
            2,
            new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime(),
            new Person("Bob"),
            Arrays.asList(new Person("Alice"), new Person("Bill"))
        );

        //when
        CarDto dto = converter.convert(car, CarDto.class);

        //then
        assertThat(dto).isNotNull();

        assertThat(dto.getPassengers()).hasSize(2);
        assertThat(dto.getPassengers().get(0).getName()).isEqualTo("Alice");
        assertThat(dto.getPassengers().get(1).getName()).isEqualTo("Bill");
    }

    @Test
    public void shouldReverseMapIterableAttribute() {
        //given
        CarDto carDto = new CarDto(
            "Morris",
            2,
            "1980",
            new PersonDto("Bob"),
            Arrays.asList(new PersonDto("Alice"), new PersonDto("Bill"))
        );

        //when
        Car car = converter.convert(carDto, Car.class);

        //then
        assertThat(car).isNotNull();

        assertThat(car.getPassengers()).hasSize(2);
        assertThat(car.getPassengers().get(0).getName()).isEqualTo("Alice");
        assertThat(car.getPassengers().get(1).getName()).isEqualTo("Bill");
    }

    @Test
    public void shouldMapEnumToString() {
        //given
        Car car = new Car();
        car.setCategory(Category.CONVERTIBLE);
        //when
        CarDto carDto = converter.convert(car, CarDto.class);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getCategory()).isEqualTo("CONVERTIBLE");
    }

    @Test
    public void shouldMapStringToEnum() {
        //given
        CarDto carDto = new CarDto();
        carDto.setCategory("CONVERTIBLE");
        //when
        Car car = converter.convert(carDto, Car.class);

        //then
        assertThat(car).isNotNull();
        assertThat(car.getCategory()).isEqualTo(Category.CONVERTIBLE);
    }
}
