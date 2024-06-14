/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection;

import org.springframework.stereotype.Component;

@Component
public class SourceTargetMapper {

    protected StringHolder toStringHolder(String string) {
        return new StringHolder( string );
    }

    protected String toString(StringHolder string) {
        return string.getString();
    }
}
