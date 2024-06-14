/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.conversion.numbers;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@AutoMapper(target = Target.class)
public class Source {

    @AutoMapping( target = "i", numberFormat = "##.00" )
    private int i;

    @AutoMapping( target = "ii", numberFormat = "##.00" )
    private Integer ii;

    @AutoMapping( target = "d", numberFormat = "##.00" )
    private double d;

    @AutoMapping( target = "dd", numberFormat = "##.00" )
    private Double dd;

    @AutoMapping( target = "f", numberFormat = "##.00" )
    private float f;

    @AutoMapping( target = "ff", numberFormat = "##.00" )
    private Float ff;

    @AutoMapping( target = "l", numberFormat = "##.00" )
    private long l;

    @AutoMapping( target = "ll", numberFormat = "##.00" )
    private Long ll;

    @AutoMapping( target = "b", numberFormat = "##.00" )
    private byte b;

    @AutoMapping( target = "bb", numberFormat = "##.00" )
    private Byte bb;

    @AutoMapping( target = "complex1", numberFormat = "##0.##E0" )
    private double complex1;

    @AutoMapping( target = "complex2", numberFormat = "$#.00" )
    private double complex2;

    @AutoMapping( target = "bigDecimal1", numberFormat = "#0.#E0" )
    private BigDecimal bigDecimal1;

    @AutoMapping( target = "bigInteger1", numberFormat = "0.#############E0" )
    private BigInteger bigInteger1;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Integer getIi() {
        return ii;
    }

    public void setIi(Integer ii) {
        this.ii = ii;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public Double getDd() {
        return dd;
    }

    public void setDd(Double dd) {
        this.dd = dd;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public Float getFf() {
        return ff;
    }

    public void setFf(Float ff) {
        this.ff = ff;
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }

    public Long getLl() {
        return ll;
    }

    public void setLl(Long ll) {
        this.ll = ll;
    }

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public Byte getBb() {
        return bb;
    }

    public void setBb(Byte bb) {
        this.bb = bb;
    }

    public double getComplex1() {
        return complex1;
    }

    public void setComplex1(double complex1) {
        this.complex1 = complex1;
    }

    public double getComplex2() {
        return complex2;
    }

    public void setComplex2(double complex2) {
        this.complex2 = complex2;
    }

    public BigDecimal getBigDecimal1() {
        return bigDecimal1;
    }

    public void setBigDecimal1(BigDecimal bigDecimal1) {
        this.bigDecimal1 = bigDecimal1;
    }

    public BigInteger getBigInteger1() {
        return bigInteger1;
    }

    public void setBigInteger1(BigInteger bigInteger1) {
        this.bigInteger1 = bigInteger1;
    }
}
