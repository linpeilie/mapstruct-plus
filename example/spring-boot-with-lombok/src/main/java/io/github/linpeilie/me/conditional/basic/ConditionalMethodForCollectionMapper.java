/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.conditional.basic;

import java.util.Collection;
import java.util.List;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class ConditionalMethodForCollectionMapper {

    @Condition
    public <T> boolean isNotEmpty(Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

}
