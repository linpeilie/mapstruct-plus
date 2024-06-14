/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.collection.immutabletarget;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;

/**
 * @author Sjaak Derksen
 */
@AutoMappers({
    @AutoMapper(target = CupboardEntity.class, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE),
})
public class CupboardDto {

    private List<String> content;

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
