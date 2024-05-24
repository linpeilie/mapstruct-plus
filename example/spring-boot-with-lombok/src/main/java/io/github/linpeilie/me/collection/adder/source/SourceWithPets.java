/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.collection.adder.source;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.me.collection.adder._target.TargetWithAnimals;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapping;

/**
 * @author Filip Hrisafov
 */
@AutoMapper(target = TargetWithAnimals.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public class SourceWithPets {

    @AutoMapping(target = "animals")
    private List<String> pets;

    public List<String> getPets() {
        return pets;
    }

    public void setPets(List<String> pets) {
        this.pets = pets;
    }
}
