/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.source.expressions.java;

public class QualifierProvider {

    public @interface ToUpper {
    }

    @ToUpper
    public String toUpper( String string ) {
        return string.toUpperCase();
    }
}
