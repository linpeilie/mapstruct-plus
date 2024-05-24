/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.source.expressions.java;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;

@AutoMapper(target = TargetBooleanWorkAround.class)
public class SourceBooleanWorkAround {

    @AutoMapping( expression = "java(source.isVal())", target = "val" )
    private Boolean val;

    public Boolean isVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val = val;
    }
}
