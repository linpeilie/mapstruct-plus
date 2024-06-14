/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.nested_bean_mappings.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import io.github.linpeilie.me.nested_bean_mappings.dto.FishTankDto;
import io.github.linpeilie.me.nested_bean_mappings.dto.FishTankDto1;
import io.github.linpeilie.me.nested_bean_mappings.dto.FishTankDto2;
import io.github.linpeilie.me.nested_bean_mappings.dto.FishTankWithNestedDocumentDto;
import lombok.Data;

@Data
@AutoMappers({
    @AutoMapper(target = FishTankDto.class),
    @AutoMapper(target = FishTankDto1.class, reverseConvertGenerate = false),
    @AutoMapper(target = FishTankDto2.class, reverseConvertGenerate = false),
    @AutoMapper(target = FishTankWithNestedDocumentDto.class, reverseConvertGenerate = false),
})
public class FishTank {

    @AutoMappings({
        @AutoMapping(targetClass = FishTankDto.class, target = "fish.kind", source = "fish.type"),
        @AutoMapping(targetClass = FishTankDto.class, target = "fish.name", ignore = true),
        @AutoMapping(targetClass = FishTankDto1.class, target = "fish.kind", source = "fish.type"),
        @AutoMapping(targetClass = FishTankDto1.class, target = "fish.name", constant = "Nemo"),
        @AutoMapping(targetClass = FishTankDto2.class, target = "fish.kind", source = "fish.type"),
        @AutoMapping(targetClass = FishTankDto2.class, target = "fish.name", expression = "java(\"Jaws\")"),
        @AutoMapping(targetClass = FishTankWithNestedDocumentDto.class, target = "fish.kind", source = "fish.type"),
        @AutoMapping(targetClass = FishTankWithNestedDocumentDto.class, target = "fish.name", expression = "java(\"Jaws\")"),
    })
    private Fish fish;

    @AutoMappings({
        @AutoMapping(targetClass = FishTankDto2.class, target = "plant", ignore = true),
        @AutoMapping(targetClass = FishTankWithNestedDocumentDto.class, target = "plant", ignore = true)
    })
    private WaterPlant plant;
    private String name;

    @AutoMappings({
        @AutoMapping(targetClass = FishTankDto.class, target = "material.materialType"),
        @AutoMapping(targetClass = FishTankDto1.class, target = "material.materialType"),
        @AutoMapping(targetClass = FishTankDto1.class, target = "material.manufacturer", constant = "MMM"),
        @AutoMapping(targetClass = FishTankDto2.class, target = "material", ignore = true),
        @AutoMapping(targetClass = FishTankWithNestedDocumentDto.class, target = "material", ignore = true),
    })
    private MaterialType material;

    @AutoMappings({
        @AutoMapping(targetClass = FishTankDto.class, target = "ornament", source = "interior.ornament"),
        @AutoMapping(targetClass = FishTankDto1.class, target = "ornament", ignore = true),
        @AutoMapping(targetClass = FishTankDto2.class, target = "ornament", ignore = true),
        @AutoMapping(targetClass = FishTankWithNestedDocumentDto.class, target = "ornament", ignore = true),
    })
    private Interior interior;

    @AutoMappings({
        @AutoMapping(targetClass = FishTankDto.class, target = "quality.report.organisation.name", source = "quality.report.organisationName"),
        @AutoMapping(targetClass = FishTankDto1.class, target = "quality", ignore = true),
        @AutoMapping(targetClass = FishTankDto2.class, target = "quality.report.organisation.name", expression = "java(\"Dunno\")"),
        @AutoMapping(targetClass = FishTankWithNestedDocumentDto.class, target = "quality.document", source = "quality.report"),
        @AutoMapping(targetClass = FishTankWithNestedDocumentDto.class, target = "quality.document.organisation.name", constant = "NoIdeaInc"),
    })
    private WaterQuality quality;

}
