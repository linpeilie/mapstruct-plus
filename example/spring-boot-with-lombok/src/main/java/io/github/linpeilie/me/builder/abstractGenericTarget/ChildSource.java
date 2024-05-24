/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.builder.abstractGenericTarget;

import io.github.linpeilie.annotations.AutoMapper;

@AutoMapper(target = ImmutableChild.class)
public class ChildSource {
    private String name;

    public ChildSource() {
    }

    public ChildSource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
