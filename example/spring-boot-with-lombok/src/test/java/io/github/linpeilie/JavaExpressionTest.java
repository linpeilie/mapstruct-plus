/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.source.expressions.java.BooleanWorkAroundMapper;
import io.github.linpeilie.me.source.expressions.java.MultiLineExpressionMapper;
import io.github.linpeilie.me.source.expressions.java.QualifierProvider;
import io.github.linpeilie.me.source.expressions.java.Source;
import io.github.linpeilie.me.source.expressions.java.Source2;
import io.github.linpeilie.me.source.expressions.java.SourceBooleanWorkAround;
import io.github.linpeilie.me.source.expressions.java.SourceList;
import io.github.linpeilie.me.source.expressions.java.SourceTargetListMapper;
import io.github.linpeilie.me.source.expressions.java.Target;
import io.github.linpeilie.me.source.expressions.java.Target1;
import io.github.linpeilie.me.source.expressions.java.TargetBooleanWorkAround;
import io.github.linpeilie.me.source.expressions.java.TargetList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class JavaExpressionTest {

    @Autowired
    private Converter converter;

    private Date getTime(String format, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }

    @Test
    public void testBooleanGetterWorkAround() throws ParseException {
        SourceBooleanWorkAround source = new SourceBooleanWorkAround();
        source.setVal(Boolean.TRUE);

        TargetBooleanWorkAround target = converter.convert(source, TargetBooleanWorkAround.class);
        assertThat(target).isNotNull();
        assertThat(target.isVal()).isTrue();

        SourceBooleanWorkAround source2 = converter.convert(target, SourceBooleanWorkAround.class);
        assertThat(source2).isNotNull();
        assertThat(source2.isVal()).isTrue();
    }

    @Test
    public void testGetterOnly() throws ParseException {
        SourceList source = new SourceList();
        source.setList(Arrays.asList("test1"));

        TargetList target = converter.convert(source, TargetList.class);
        assertThat(target).isNotNull();
        assertThat(target.getList()).isEqualTo(Arrays.asList("test2"));
    }

    @Test
    public void testMultiLineJavaExpressionInsertion() throws ParseException {
        Source source = new Source();
        String format = "dd-MM-yyyy,hh:mm:ss";
        Date time = getTime(format, "09-01-2014,01:35:03");

        source.setFormat(format);
        source.setTime(time);

        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getTimeAndFormat().getTime()).isEqualTo(time);
        assertThat(target.getTimeAndFormat().getFormat()).isEqualTo(format);
        assertThat(target.getAnotherProp()).isNull();

        Target1 target1 = converter.convert(source, Target1.class);

        assertThat(target1).isNotNull();
        assertThat(target1.getTimeAndFormat().getTime()).isEqualTo(time);
        assertThat(target1.getTimeAndFormat().getFormat()).isEqualTo(format);
        assertThat(target1.getAnotherProp()).isNull();
    }

}
