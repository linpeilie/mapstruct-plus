/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.mappingcontrol;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import org.mapstruct.control.DeepClone;
import org.mapstruct.control.MappingControl;

@AutoMappers({
    @AutoMapper(target = FridgeDTO.class, mappingControl = DeepClone.class, reverseConvertGenerate = false),
    @AutoMapper(target = Fridge.class, reverseConvertGenerate = false, uses = ComplexMapper.class)
})
public class FridgeDTO {

    private ShelveDTO shelve;

    public ShelveDTO getShelve() {
        return shelve;
    }

    public void setShelve(ShelveDTO shelve) {
        this.shelve = shelve;
    }
}
