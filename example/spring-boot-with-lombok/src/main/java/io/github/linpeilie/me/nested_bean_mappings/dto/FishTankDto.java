/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nested_bean_mappings.dto;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import io.github.linpeilie.me.nested_bean_mappings.model.FishTank;
import lombok.Data;

@Data
@AutoMapper(target = FishTank.class)
public class FishTankDto {

    @AutoMappings({
        @AutoMapping(target = "fish.type", source = "fish.kind"),
    })
    private FishDto fish;
    private WaterPlantDto plant;
    private String name;

    @AutoMapping(target = "material", source = "material.materialType")
    private MaterialDto material;

    @AutoMapping(target = "interior.ornament", source = "ornament")
    private OrnamentDto ornament;

    @AutoMapping(target = "quality.report.organisationName", source = "quality.report.organisation.name")
    private WaterQualityDto quality;

}
