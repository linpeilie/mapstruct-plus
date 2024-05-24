/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.source.manytargetproperties.Source;
import io.github.linpeilie.me.source.manytargetproperties.Target;
import io.github.linpeilie.me.source.manytargetproperties.TimeAndFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class SourceToManyTargetPropertiesTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldMapSameSourcePropertyToSeveralTargetProperties() {
        Source source = new Source();
        source.setName("Bob");

        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getName1()).isEqualTo("Bob");
        assertThat(target.getName2()).isEqualTo("Bob");
    }

    @Test
    public void shouldMapSameSourcePropertyToSeveralTargetPropertiesInvokingOtherMapper() throws ParseException {
        Source source = new Source();
        String sourceFormat = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(sourceFormat);
        Date sourceTime = dateFormat.parse("09-01-2014");
        TimeAndFormat sourceTimeAndFormat = new TimeAndFormat();
        sourceTimeAndFormat.setTfFormat(sourceFormat);
        sourceTimeAndFormat.setTfTime(sourceTime);
        source.setTimeAndFormat(sourceTimeAndFormat);

        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getFormat()).isEqualTo(sourceFormat);
        assertThat(target.getTime()).isEqualTo(sourceTime);
    }
}
