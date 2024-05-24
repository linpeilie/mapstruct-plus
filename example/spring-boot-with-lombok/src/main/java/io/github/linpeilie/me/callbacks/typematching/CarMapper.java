/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.callbacks.typematching;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

/**
 * @author Andreas Gudian
 */
@Component
public class CarMapper {

    @AfterMapping
    protected void neverMatched(ElectricCarDto electricDto) {
        throw new RuntimeException("must not be called");
    }

    @AfterMapping
    protected void neverMatched(@MappingTarget ElectricCarEntity electricEntity) {
        throw new RuntimeException("must not be called");
    }

    @AfterMapping
    protected void isCalled(@MappingTarget Object any) {
        if (any instanceof CarEntity) {
            CarEntity car = (CarEntity) any;
            if (car.getSeatCount() == 0) {
                car.setSeatCount(5);
            }
        }
    }

    @AfterMapping
    protected void incrementsTargetId(@MappingTarget Identifiable identifiable) {
        identifiable.setId(identifiable.getId() + 1);
    }

    @BeforeMapping
    protected void incrementsSourceId(Identifiable identifiable) {
        identifiable.setId(identifiable.getId() + 1);
    }
}
