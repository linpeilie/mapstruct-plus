package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import lombok.Data;

@Data
@AutoMapper(target = GoodsVo.class)
public class Goods {

    private String price;

    private String takeDownTime;

    @AutoMapping(target = "price", source = "sku.price")
    private Sku sku;

    private GoodsStateEnum state;

    private GoodsTypeEnum type;

}
