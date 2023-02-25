package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapMapper;
import java.util.Date;

@AutoMapMapper
public class MapModelB {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MapModelB{" +
               "date=" + date +
               '}';
    }
}
