/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.source.manytargetproperties;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;

@AutoMapper(target = Target.class, uses = TimeAndFormatMapper.class, reverseConvertGenerate = false,
    mapperName = "SourceToTargetForManyTargetPropertiesMapper")
public class Source {

    @AutoMappings({
        @AutoMapping(target = "time", source = "timeAndFormat"),
        @AutoMapping(target = "format", source = "timeAndFormat")
    })
    private TimeAndFormat timeAndFormat;

    @AutoMappings({
        @AutoMapping(target = "name1", source = "name"),
        @AutoMapping(target = "name2", source = "name")
    })
    private String name;

    public TimeAndFormat getTimeAndFormat() {
        return timeAndFormat;
    }

    public void setTimeAndFormat(TimeAndFormat timeAndFormat) {
        this.timeAndFormat = timeAndFormat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
