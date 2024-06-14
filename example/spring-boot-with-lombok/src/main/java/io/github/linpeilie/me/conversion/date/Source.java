/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.conversion.date;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.Date;
import org.mapstruct.Mapping;

@AutoMapper(target = Target.class, uses = DataStringMapper.class)
public class Source {

    @AutoMapping(target = "date", dateFormat = "dd.MM.yyyy")
    private Date date;
    private Date anotherDate;
    private Date time;
    private Date sqlDate;
    private Date timestamp;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getAnotherDate() {
        return anotherDate;
    }

    public void setAnotherDate(Date anotherDate) {
        this.anotherDate = anotherDate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
