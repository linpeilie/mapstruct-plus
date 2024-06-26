/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.iterabletononiterable;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;

@AutoMapper(target = Target.class, uses = StringListMapper.class)
public class Source {

    //CHECKSTYLE:OFF
    public List<String> publicNames;
    //CHECKSTYLE:ON

    private List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
