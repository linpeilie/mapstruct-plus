package io.github.linpeilie.me.nested_bean_mappings.dto;

import lombok.Data;

@Data
public class FishTankDto2 {

    private FishDto fish;
    private WaterPlantDto plant;
    private String name;
    private MaterialDto material;
    private OrnamentDto ornament;
    private WaterQualityDto quality;

}
