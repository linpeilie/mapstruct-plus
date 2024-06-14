/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me;

import io.github.linpeilie.annotations.AutoMapper;

/**
 * @author Filip Hrisafov
 */
@AutoMapper(target = NoProperties.class)
public class WithProperties {

    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
