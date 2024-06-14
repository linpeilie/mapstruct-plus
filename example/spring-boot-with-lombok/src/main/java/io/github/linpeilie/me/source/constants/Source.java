/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.source.constants;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;

/**
 * @author Sjaak Derksen
 */
@AutoMapper(target = Target.class, uses = StringList1Mapper.class)
public class Source {

    @AutoMappings({
        @AutoMapping(target = "stringConstant", constant = "stringConstant"),
        @AutoMapping(target = "integerConstant", constant = "14"),
        @AutoMapping(target = "longWrapperConstant", constant = "3001L"),
        @AutoMapping(target = "dateConstant", dateFormat = "dd-MM-yyyy", constant = "09-01-2014"),
        @AutoMapping(target = "nameConstants", constant = "jack-jill-tom"),
        @AutoMapping(target = "country", constant = "THE_NETHERLANDS")
    })
    private String propertyThatShouldBeMapped;

    public String getPropertyThatShouldBeMapped() {
        return propertyThatShouldBeMapped;
    }

    public void setPropertyThatShouldBeMapped(String propertyThatShouldBeMapped) {
        this.propertyThatShouldBeMapped = propertyThatShouldBeMapped;
    }
}
