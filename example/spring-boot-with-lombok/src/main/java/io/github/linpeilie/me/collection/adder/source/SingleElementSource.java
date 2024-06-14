/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.collection.adder.source;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.me.collection.adder.PetMapper;
import io.github.linpeilie.me.collection.adder.TeethMapper;
import io.github.linpeilie.me.collection.adder._target.Target;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapping;

/**
 * @author Sjaak Derksen
 */
@AutoMapper(target = Target.class, reverseConvertGenerate = false, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {
    PetMapper.class, TeethMapper.class})
public class SingleElementSource {

    @AutoMapping(target = "pets")
    private String pet;

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }
}
