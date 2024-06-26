/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.defaultimplementation;

import io.github.linpeilie.annotations.AutoMapper;

@AutoMapper(target = TargetFoo.class)
public class SourceFoo {

    private String name;

    public SourceFoo() {
    }

    public SourceFoo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
