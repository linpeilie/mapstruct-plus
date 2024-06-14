/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.iterabletononiterable;

import io.github.linpeilie.annotations.AutoMapper;

@AutoMapper(target = Source.class, uses = StringListMapper.class)
public class Target {

    //CHECKSTYLE:OFF
    public String publicNames;
    //CHECKSTYLE:ON

    private String names;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
