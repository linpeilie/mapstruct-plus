/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.source.defaultExpressions.java;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;

@AutoMappers({
    @AutoMapper(target = Target.class, reverseConvertGenerate = false, imports = {Date.class}),
    @AutoMapper(target = Target1.class,
        reverseConvertGenerate = false,
        imports = {Date.class, LocalDate.class, ZoneOffset.class, Month.class})
})
public class Source {

    @AutoMappings({
        @AutoMapping(targetClass = Target.class, target = "sourceId", defaultExpression = "java( String.valueOf( \"test\" ) )"),
        @AutoMapping(
            targetClass = Target1.class,
            target = "sourceId",
            defaultExpression = "java( new StringBuilder()\n.append( \"test\" )\n.toString() )"
        )
    })
    private int id = -1;

    @AutoMappings({
        @AutoMapping(targetClass = Target.class, target = "sourceDate", defaultExpression = "java( new Date( 30L ))"),
        @AutoMapping(
            targetClass = Target1.class,
            target = "sourceDate",
            defaultExpression = "java(" +
                                "Date.from(\n" +
                                "LocalDate.of( 2022, Month.JUNE, 5 )\n" +
                                ".atTime( 17, 10 )\n" +
                                ".toInstant( ZoneOffset.UTC )\n)" +
                                ")"
        )
    })
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasId() {
        return id != -1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
