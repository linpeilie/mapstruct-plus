/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.nullcheck;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;

@AutoMapper(target = Target.class,
    uses = {NullObjectMapper.class, CustomMapper.class},
    reverseConvertGenerate = false)
public class Source {

    private NullObject someObject;
    private String number;
    private List<String> someList;
    private Integer someInteger;
    private Long someLong;

    public NullObject getSomeObject() {
        return someObject;
    }

    public void setSomeObject(NullObject someObject) {
        this.someObject = someObject;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<String> getSomeList() {
        return someList;
    }

    public void setSomeList(List<String> someList) {
        this.someList = someList;
    }

    public Integer getSomeInteger() {
        return someInteger;
    }

    public void setSomeInteger(Integer someInteger) {
        this.someInteger = someInteger;
    }

    public Long getSomeLong() {
        return someLong;
    }

    public void setSomeLong(Long someLong) {
        this.someLong = someLong;
    }

}
