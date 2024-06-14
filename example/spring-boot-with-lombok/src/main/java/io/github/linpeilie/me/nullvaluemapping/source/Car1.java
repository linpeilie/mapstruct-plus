/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.nullvaluemapping.source;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import io.github.linpeilie.me.nullvaluemapping._target.CarDto1;
import java.util.UUID;
import org.mapstruct.NullValueMappingStrategy;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@AutoMapper(target = CarDto1.class,
    imports = UUID.class,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
    reverseConvertGenerate = false)
public class Car1 {

    private String make;

    @AutoMappings({
        @AutoMapping(target = "seatCount", source = "numberOfSeats"),
        @AutoMapping(target = "catalogId", expression = "java( UUID.randomUUID().toString() )"),
        @AutoMapping(target = "model", constant = "ModelT")
    })
    private int numberOfSeats;

    public Car1() {
    }

    public Car1(String make, int numberOfSeats) {
        this.make = make;
        this.numberOfSeats = numberOfSeats;

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

}
