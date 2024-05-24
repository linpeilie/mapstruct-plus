/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.collection.adder.source;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.me.collection.adder.PetMapper;
import io.github.linpeilie.me.collection.adder.TeethMapper;
import io.github.linpeilie.me.collection.adder._target.TargetDali;
import io.github.linpeilie.me.collection.adder._target.TargetHuman;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;

/**
 * @author Sjaak Derksen
 */
@AutoMappers({
    @AutoMapper(target = TargetDali.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {PetMapper.class, TeethMapper.class}),
    @AutoMapper(target = TargetHuman.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {PetMapper.class, TeethMapper.class})
})
public class SourceTeeth {

    private List<String> teeth;

    public List<String> getTeeth() {
        return teeth;
    }

    public void setTeeth(List<String> teeth) {
        this.teeth = teeth;
    }
}
