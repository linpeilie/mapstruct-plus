/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullvaluemapping;

import io.github.linpeilie.me.nullvaluemapping._target.CarDto;
import io.github.linpeilie.me.nullvaluemapping.source.Car;
import java.util.List;
import java.util.UUID;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(imports = UUID.class)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    @BeanMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    @Mapping(target = "seatCount", source = "numberOfSeats")
    @Mapping(target = "model", constant = "ModelT")
    @Mapping(target = "catalogId", expression = "java( UUID.randomUUID().toString() )")
    CarDto carToCarDto(Car car);

    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    List<CarDto> carsToCarDtos(List<Car> cars);

}
