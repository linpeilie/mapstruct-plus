package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@AutoMapper(target = GoodsDto.class)
public class GoodsVo extends BaseVO {

    private Integer price;

    private Integer state;

}
