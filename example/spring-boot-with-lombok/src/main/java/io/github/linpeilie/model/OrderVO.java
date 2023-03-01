package io.github.linpeilie.model;

import io.github.linpeilie.StringToListStringConverter;
import io.github.linpeilie.annotations.AutoMapMapper;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import io.github.linpeilie.annotations.ReverseAutoMapping;
import java.time.LocalDate;
import lombok.Data;

@Data
@AutoMapper(target = Order.class, uses = StringToListStringConverter.class, convertGenerate = false)
public class OrderVO {

    private String orderId;

    private String goods;

    @ReverseAutoMapping(numberFormat = "$0.00")
    private String orderPrice;

    @ReverseAutoMapping(ignore = true)
    private String goodsNum;

    @ReverseAutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String orderTime;

    @ReverseAutoMapping(dateFormat = "yyyy_MM_dd HHmmss")
    private String createTime;

    @ReverseAutoMapping(source = "date", dateFormat = "yyyy-MM-dd")
    private LocalDate orderDate;

    @ReverseAutoMapping(source = "user.username")
    private String user;

    @ReverseAutoMapping(defaultValue = "True")
    private String payStatus;

}
