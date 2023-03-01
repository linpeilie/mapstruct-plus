package io.github.linpeilie.model;

import io.github.linpeilie.StringToListStringConverter;
import io.github.linpeilie.annotations.AutoMapMapper;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMapping;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@AutoMappers({
    @AutoMapper(target = OrderEntity.class, reverseConvertGenerate = false),
//    @AutoMapper(target = OrderVO.class)
})
public class Order {

    private String orderId;

    @AutoMapping(expression = "java(java.lang.String.join(\",\", source.getGoods()))")
    private List<String> goods;

    @AutoMapping(numberFormat = "$0.00")
    private BigDecimal orderPrice;

    @AutoMapping(numberFormat = "$0.00")
    private Integer goodsNum;

    @AutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    @AutoMapping(dateFormat = "yyyy_MM_dd HH:mm:ss")
    private Date createTime;

    @ReverseAutoMapping(target = "orderDate", dateFormat = "yyyy-MM-dd")
    private String date;

    @AutoMapping(source = "user.username")
    private User user;

    private Boolean payStatus;

}
