/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.conversion.bignumbers;

import io.github.linpeilie.annotations.AutoMapper;
import java.math.BigInteger;

@AutoMapper(target = BigIntegerTarget.class)
public class BigIntegerSource {

    private BigInteger b;
    private BigInteger bb;
    private BigInteger s;
    private BigInteger ss;
    private BigInteger i;
    private BigInteger ii;
    private BigInteger l;
    private BigInteger ll;
    private BigInteger f;
    private BigInteger ff;
    private BigInteger d;
    private BigInteger dd;
    private BigInteger string;

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public BigInteger getBb() {
        return bb;
    }

    public void setBb(BigInteger bb) {
        this.bb = bb;
    }

    public BigInteger getS() {
        return s;
    }

    public void setS(BigInteger s) {
        this.s = s;
    }

    public BigInteger getSs() {
        return ss;
    }

    public void setSs(BigInteger ss) {
        this.ss = ss;
    }

    public BigInteger getI() {
        return i;
    }

    public void setI(BigInteger i) {
        this.i = i;
    }

    public BigInteger getIi() {
        return ii;
    }

    public void setIi(BigInteger ii) {
        this.ii = ii;
    }

    public BigInteger getL() {
        return l;
    }

    public void setL(BigInteger l) {
        this.l = l;
    }

    public BigInteger getLl() {
        return ll;
    }

    public void setLl(BigInteger ll) {
        this.ll = ll;
    }

    public BigInteger getF() {
        return f;
    }

    public void setF(BigInteger f) {
        this.f = f;
    }

    public BigInteger getFf() {
        return ff;
    }

    public void setFf(BigInteger ff) {
        this.ff = ff;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getDd() {
        return dd;
    }

    public void setDd(BigInteger dd) {
        this.dd = dd;
    }

    public BigInteger getString() {
        return string;
    }

    public void setString(BigInteger string) {
        this.string = string;
    }
}
