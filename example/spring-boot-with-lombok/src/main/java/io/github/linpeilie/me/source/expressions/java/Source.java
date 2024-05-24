/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.source.expressions.java;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import io.github.linpeilie.me.source.expressions.java.mapper.TimeAndFormat;
import java.util.Date;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@AutoMappers({
    @AutoMapper(target = Target.class, reverseConvertGenerate = false, imports = TimeAndFormat.class),
    @AutoMapper(target = Target1.class, reverseConvertGenerate = false, imports = TimeAndFormat.class)
})
public class Source {

    @AutoMappings({
        @AutoMapping(targetClass = Target.class, target = "timeAndFormat", expression = "java( new TimeAndFormat(\nsource.getTime(),\nsource.getFormat()\n ))"),
        @AutoMapping(target = "anotherProp", ignore = true),
        @AutoMapping(
            targetClass = Target1.class,
            target = "timeAndFormat",
            expression = "    java( new TimeAndFormat(\nsource.getTime(),\nsource.getFormat()\n ))   "
        ),
    })
    private String format;
    private Date time;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
