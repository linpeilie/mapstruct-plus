/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.defaultimplementation;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.List;
import org.mapstruct.Mapping;

@AutoMappers({
    @AutoMapper(target = Target.class)
})
public class Source {

    @AutoMapping(target = "fooListNoSetter")
    private List<SourceFoo> fooList;

    public List<SourceFoo> getFooList() {
        return fooList;
    }

    public void setFooList(List<SourceFoo> fooList) {
        this.fooList = fooList;
    }

}
