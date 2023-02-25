package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapMapper;

@AutoMapMapper
public class MapModelA {

    private String str;
    private int i1;
    private Long l2;
    private MapModelB mapModelB;

    public String getStr() {
        return str;
    }

    public void setStr(final String str) {
        this.str = str;
    }

    public int getI1() {
        return i1;
    }

    public void setI1(final int i1) {
        this.i1 = i1;
    }

    public Long getL2() {
        return l2;
    }

    public void setL2(final Long l2) {
        this.l2 = l2;
    }

    public MapModelB getMapModelB() {
        return mapModelB;
    }

    @Override
    public String toString() {
        return "MapModelA{" +
               "str='" + str + '\'' +
               ", i1=" + i1 +
               ", l2=" + l2 +
               ", mapModelB=" + mapModelB +
               '}';
    }

    public void setMapModelB(final MapModelB mapModelB) {
        this.mapModelB = mapModelB;
    }
}
