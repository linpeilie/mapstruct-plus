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
import io.github.linpeilie.me.collection.adder._target.Target;
import io.github.linpeilie.me.collection.adder._target.TargetOnlyGetter;
import io.github.linpeilie.me.collection.adder._target.TargetViaTargetType;
import io.github.linpeilie.me.collection.adder._target.TargetWithoutSetter;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;

/**
 * @author Sjaak Derksen
 */
@AutoMappers({
    @AutoMapper(target = Target.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {
        PetMapper.class, TeethMapper.class}),
    @AutoMapper(target = TargetOnlyGetter.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {
        PetMapper.class, TeethMapper.class}),
    @AutoMapper(target = TargetViaTargetType.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {
        PetMapper.class, TeethMapper.class}, reverseConvertGenerate = false),
    @AutoMapper(target = TargetWithoutSetter.class,
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        uses = {PetMapper.class}
    )
})
public class Source {

    private List<String> pets;

    public List<String> getPets() {
        return pets;
    }

    public void setPets(List<String> pets) {
        this.pets = pets;
    }
}
