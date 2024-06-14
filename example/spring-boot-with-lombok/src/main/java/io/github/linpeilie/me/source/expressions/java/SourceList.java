/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.source.expressions.java;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.Arrays;
import java.util.List;
import org.mapstruct.Mapping;

@AutoMappers({
    @AutoMapper(target = TargetList.class, imports = { Arrays.class })
})
public class SourceList {

    @AutoMapping(target = "list", expression = "java(Arrays.asList( \"test2\" ))")
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList( List<String> list ) {
        this.list = list;
    }

}
