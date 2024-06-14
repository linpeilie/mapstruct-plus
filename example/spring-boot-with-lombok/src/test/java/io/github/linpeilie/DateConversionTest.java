/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.conversion.date.Source;
import io.github.linpeilie.me.conversion.date.Target;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests application of format strings for conversions between strings and dates.
 *
 * @author Gunnar Morling
 */
@SpringBootTest(classes = Application.class)
public class DateConversionTest {

    @Autowired
    private Converter converter;

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    public void shouldApplyDateFormatForConversions() {
        Source source = new Source();
        source.setDate(new GregorianCalendar(2013, Calendar.JULY, 6).getTime());

        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getDate()).isEqualTo("06.07.2013");
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_11)
    // See https://bugs.openjdk.java.net/browse/JDK-8211262, there is a difference in the default formats on Java 9+
    public void shouldApplyDateFormatForConversionsJdk11() {
        Source source = new Source();
        source.setDate(new GregorianCalendar(2013, Calendar.JULY, 6).getTime());
        source.setAnotherDate(new GregorianCalendar(2013, Calendar.FEBRUARY, 14).getTime());

        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getDate()).isEqualTo("06.07.2013");
        assertThat(target.getAnotherDate()).isEqualTo("14.02.13, 00:00");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    // See https://bugs.openjdk.java.net/browse/JDK-8211262, there is a difference in the default formats on Java 9+
    public void shouldApplyDateFormatForConversionInReverseMapping() {
        Target target = new Target();
        target.setDate("06.07.2013");

        Source source = converter.convert(target, Source.class);

        assertThat(source).isNotNull();
        assertThat(source.getDate()).isEqualTo(new GregorianCalendar(2013, Calendar.JULY, 6).getTime());
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_11)
    // See https://bugs.openjdk.java.net/browse/JDK-8211262, there is a difference in the default formats on Java 9+
    public void shouldApplyDateFormatForConversionInReverseMappingJdk11() {
        Target target = new Target();
        target.setDate("06.07.2013");
        target.setAnotherDate("14.02.13, 8:30");

        Source source = converter.convert(target, Source.class);

        assertThat(source).isNotNull();
        assertThat(source.getDate()).isEqualTo(new GregorianCalendar(2013, Calendar.JULY, 6).getTime());
        assertThat(source.getAnotherDate()).isEqualTo(
            new GregorianCalendar(2013, Calendar.FEBRUARY, 14, 8, 30).getTime());
    }

    @Test
    public void shouldApplyDateToSqlConversion() {
        GregorianCalendar time = new GregorianCalendar(2016, Calendar.AUGUST, 24, 20, 30, 30);
        GregorianCalendar sqlDate = new GregorianCalendar(2016, Calendar.AUGUST, 23, 21, 35, 35);
        GregorianCalendar timestamp = new GregorianCalendar(2016, Calendar.AUGUST, 22, 21, 35, 35);
        Source source = new Source();
        source.setTime(time.getTime());
        source.setSqlDate(sqlDate.getTime());
        source.setTimestamp(timestamp.getTime());

        Target target = converter.convert(source, Target.class);
        Time expectedTime = new Time(time.getTime().getTime());
        java.sql.Date expectedSqlDate = new java.sql.Date(sqlDate.getTime().getTime());
        Timestamp expectedTimestamp = new Timestamp(timestamp.getTime().getTime());

        assertThat(target.getTime()).isEqualTo(expectedTime);
        assertThat(target.getSqlDate()).isEqualTo(expectedSqlDate);
        assertThat(target.getTimestamp()).isEqualTo(expectedTimestamp);
    }

    @Test
    public void shouldApplySqlToDateConversion() {
        Target target = new Target();
        GregorianCalendar time = new GregorianCalendar(2016, Calendar.AUGUST, 24, 20, 30, 30);
        GregorianCalendar sqlDate = new GregorianCalendar(2016, Calendar.AUGUST, 23, 21, 35, 35);
        GregorianCalendar timestamp = new GregorianCalendar(2016, Calendar.AUGUST, 22, 21, 35, 35);
        target.setTime(new Time(time.getTime().getTime()));
        target.setSqlDate(new java.sql.Date(sqlDate.getTime().getTime()));
        target.setTimestamp(new Timestamp(timestamp.getTime().getTime()));

        Source source = converter.convert(target, Source.class);

        assertThat(source).isNotNull();
        assertThat(source.getTime()).isEqualTo(target.getTime());
        assertThat(source.getSqlDate()).isEqualTo(target.getSqlDate());
        assertThat(source.getTimestamp()).isEqualTo(target.getTimestamp());
    }
}