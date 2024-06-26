/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullcheck.strategy;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface HouseMapper {

    HouseMapper INSTANCE = Mappers.getMapper( HouseMapper.class );

    HouseEntity mapWithNvcsOnMapper(HouseDto in);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
    HouseEntity mapWithNvcsOnBean(HouseDto in);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
    @Mapping(target = "number", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    HouseEntity mapWithNvcsOnMapping(HouseDto in);

}
