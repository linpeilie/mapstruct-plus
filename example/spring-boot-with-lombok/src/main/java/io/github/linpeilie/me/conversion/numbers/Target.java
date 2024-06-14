/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.conversion.numbers;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.math.BigDecimal;
import java.math.BigInteger;

@AutoMapper(target = Source.class)
public class Target {

    @AutoMapping( target = "i", numberFormat = "##.00" )
    private String i;

    @AutoMapping( target = "ii", numberFormat = "##.00" )
    private String ii;

    @AutoMapping( target = "d", numberFormat = "##.00" )
    private String d;

    @AutoMapping( target = "dd", numberFormat = "##.00" )
    private String dd;

    @AutoMapping( target = "f", numberFormat = "##.00" )
    private String f;

    @AutoMapping( target = "ff", numberFormat = "##.00" )
    private String ff;

    @AutoMapping( target = "l", numberFormat = "##.00" )
    private String l;

    @AutoMapping( target = "ll", numberFormat = "##.00" )
    private String ll;

    @AutoMapping( target = "b", numberFormat = "##.00" )
    private String b;

    @AutoMapping( target = "bb", numberFormat = "##.00" )
    private String bb;

    @AutoMapping( target = "complex1", numberFormat = "##0.##E0" )
    private String complex1;

    @AutoMapping( target = "complex2", numberFormat = "$#.00" )
    private String complex2;

    @AutoMapping( target = "bigDecimal1", numberFormat = "#0.#E0" )
    private String bigDecimal1;

    @AutoMapping( target = "bigInteger1", numberFormat = "0.#############E0" )
    private String bigInteger1;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getIi() {
        return ii;
    }

    public void setIi(String ii) {
        this.ii = ii;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getFf() {
        return ff;
    }

    public void setFf(String ff) {
        this.ff = ff;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getLl() {
        return ll;
    }

    public void setLl(String ll) {
        this.ll = ll;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getComplex1() {
        return complex1;
    }

    public void setComplex1(String complex1) {
        this.complex1 = complex1;
    }

    public String getComplex2() {
        return complex2;
    }

    public void setComplex2(String complex2) {
        this.complex2 = complex2;
    }

    public String getBigDecimal1() {
        return bigDecimal1;
    }

    public void setBigDecimal1(String bigDecimal1) {
        this.bigDecimal1 = bigDecimal1;
    }

    public String getBigInteger1() {
        return bigInteger1;
    }

    public void setBigInteger1(String bigInteger1) {
        this.bigInteger1 = bigInteger1;
    }
}
