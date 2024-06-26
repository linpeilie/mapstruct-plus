/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.conversion.numbers.Source;
import io.github.linpeilie.me.conversion.numbers.Target;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class NumberFormatConversionTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldApplyStringConversions() {
        Source source = new Source();
        source.setI(1);
        source.setIi(2);
        source.setD(3.0);
        source.setDd(4.0);
        source.setF(3.0f);
        source.setFf(4.0f);
        source.setL(5L);
        source.setLl(6L);
        source.setB((byte) 7);
        source.setBb((byte) 8);

        source.setComplex1(345346.456756);
        source.setComplex2(5007034.3);

        source.setBigDecimal1(new BigDecimal("987E-20"));
        source.setBigInteger1(new BigInteger("1234567890000"));

        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getI()).isEqualTo("1.00");
        assertThat(target.getIi()).isEqualTo("2.00");
        assertThat(target.getD()).isEqualTo("3.00");
        assertThat(target.getDd()).isEqualTo("4.00");
        assertThat(target.getF()).isEqualTo("3.00");
        assertThat(target.getFf()).isEqualTo("4.00");
        assertThat(target.getL()).isEqualTo("5.00");
        assertThat(target.getLl()).isEqualTo("6.00");
        assertThat(target.getB()).isEqualTo("7.00");
        assertThat(target.getBb()).isEqualTo("8.00");

        assertThat(target.getComplex1()).isEqualTo("345.35E3");
        assertThat(target.getComplex2()).isEqualTo("$5007034.30");

        assertThat(target.getBigDecimal1()).isEqualTo("9.87E-18");
        assertThat(target.getBigInteger1()).isEqualTo("1.23456789E12");
    }

    @Test
    public void shouldApplyReverseStringConversions() {
        Target target = new Target();
        target.setI("1.00");
        target.setIi("2.00");
        target.setD("3.00");
        target.setDd("4.00");
        target.setF("3.00");
        target.setFf("4.00");
        target.setL("5.00");
        target.setLl("6.00");
        target.setB("7.00");
        target.setBb("8.00");

        target.setComplex1("345.35E3");
        target.setComplex2("$5007034.30");

        target.setBigDecimal1("9.87E-18");
        target.setBigInteger1("1.23456789E12");

        Source source = converter.convert(target, Source.class);

        assertThat(source).isNotNull();
        assertThat(source.getI()).isEqualTo(1);
        assertThat(source.getIi()).isEqualTo(Integer.valueOf(2));
        assertThat(source.getD()).isEqualTo(3.0);
        assertThat(source.getDd()).isEqualTo(Double.valueOf(4.0));
        assertThat(source.getF()).isEqualTo(3.0f);
        assertThat(source.getFf()).isEqualTo(Float.valueOf(4.0f));
        assertThat(source.getL()).isEqualTo(5L);
        assertThat(source.getLl()).isEqualTo(Long.valueOf(6L));
        assertThat(source.getB()).isEqualTo((byte) 7);
        assertThat(source.getBb()).isEqualTo((byte) 8);

        assertThat(source.getComplex1()).isEqualTo(345350.0);
        assertThat(source.getComplex2()).isEqualTo(5007034.3);

        assertThat(source.getBigDecimal1()).isEqualTo(new BigDecimal("987E-20"));
        assertThat(source.getBigInteger1()).isEqualTo(new BigInteger("1234567890000"));
    }
}
