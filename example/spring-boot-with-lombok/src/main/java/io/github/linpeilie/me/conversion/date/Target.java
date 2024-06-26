/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.conversion.date;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@AutoMapper(target = Source.class, uses = DataStringMapper.class)
public class Target {

    @AutoMapping(target = "date", dateFormat = "dd.MM.yyyy")
    private String date;
    private String anotherDate;
    private Time time;
    private Date sqlDate;
    private Timestamp timestamp;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getAnotherDate() {
        return anotherDate;
    }

    public void setAnotherDate(String anotherDate) {
        this.anotherDate = anotherDate;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
