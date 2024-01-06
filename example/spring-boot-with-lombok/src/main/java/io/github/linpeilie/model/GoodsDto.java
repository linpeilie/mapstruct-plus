package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.Date;
import lombok.Data;

@Data
@AutoMappers({
    @AutoMapper(target = Goods.class),
    @AutoMapper(target = GoodsVo.class)
})
public class GoodsDto extends BaseDTO {

    @AutoMapping(targetClass = Goods.class, target = "takeDownTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date takeDownTime;

    @AutoMapping(targetClass = Goods.class, target = "price", numberFormat = "$#.00")
    private int price;

    private Integer state;

    private int type;

    private String f1;

    private String f2;

    private String f3;

    private String f4;

    private String f5;

}
