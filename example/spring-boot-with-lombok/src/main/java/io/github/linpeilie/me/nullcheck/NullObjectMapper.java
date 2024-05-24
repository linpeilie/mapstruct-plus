/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullcheck;

import org.springframework.stereotype.Component;

@Component
public class NullObjectMapper {

    public String toNullString(NullObject in) {
        return in.toString();
    }
}
