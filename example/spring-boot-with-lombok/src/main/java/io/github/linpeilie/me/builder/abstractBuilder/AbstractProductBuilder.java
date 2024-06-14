/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.builder.abstractBuilder;

public abstract class AbstractProductBuilder<T extends AbstractImmutableProduct> {

    protected String name;

    public AbstractProductBuilder<T> name(String name) {
        this.name = name;
        return this;
    }

    public abstract T build();
}
