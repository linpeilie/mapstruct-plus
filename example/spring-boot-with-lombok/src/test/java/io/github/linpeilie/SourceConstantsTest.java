/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.source.constants.CountryEnum;
import io.github.linpeilie.me.source.constants.Source;
import io.github.linpeilie.me.source.constants.Target;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class SourceConstantsTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldMapSameSourcePropertyToSeveralTargetProperties() throws ParseException {
        Source source = new Source();
        source.setPropertyThatShouldBeMapped("SomeProperty");

        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getPropertyThatShouldBeMapped()).isEqualTo("SomeProperty");
        assertThat(target.getStringConstant()).isEqualTo("stringConstant");
        assertThat(target.getIntegerConstant()).isEqualTo(14);
        assertThat(target.getLongWrapperConstant()).isEqualTo(new Long(3001L));
        assertThat(target.getDateConstant()).isEqualTo(getDate("dd-MM-yyyy", "09-01-2014"));
        assertThat(target.getNameConstants()).isEqualTo(Arrays.asList("jack", "jill", "tom"));
        assertThat(target.getCountry()).isEqualTo(CountryEnum.THE_NETHERLANDS);
    }

    @Test
    public void shouldMapTargetToSourceWithoutWhining() throws ParseException {
        Target target = new Target();
        target.setPropertyThatShouldBeMapped("SomeProperty");

        Source source = converter.convert(target, Source.class);

        assertThat(source).isNotNull();
        assertThat(target.getPropertyThatShouldBeMapped()).isEqualTo("SomeProperty");
    }

    private Date getDate(String format, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }

}
