/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.collection.adder.source;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.me.collection.adder._target.Target2;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;

/**
 * @author Sjaak Derksen
 */
@AutoMapper(target = Target2.class,
    reverseConvertGenerate = false,
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public class Source2 {

    private List<Foo> attributes;

    public List<Foo> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Foo> attributes) {
        this.attributes = attributes;
    }

}
