/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.defaultvalue;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.me.defaultvalue.other.Continent;

@AutoMapper(target = CountryDts.class)
public class CountryEntity {

    @AutoMapping(target = "code", defaultValue = "DE")
    private String code;

    @AutoMapping(target = "id", defaultValue = "42")
    private Integer id;

    @AutoMapping(target = "zipcode", defaultValue = "1337")
    private long zipcode;

    @AutoMapping(target = "region", defaultValue = "someRegion")
    private Region region;

    @AutoMapping(target = "continent", defaultValue = "EUROPE")
    private Continent continent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getZipcode() {
        return zipcode;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
