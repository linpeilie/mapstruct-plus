package io.github.linpeilie.model;

import io.github.linpeilie.annotations.ReverseAutoMapping;
import java.time.LocalDate;
import lombok.Data;
import org.json.JSONObject;

@Data
public class OrderEntity {

    private String orderId;

    private String goods;

    private String orderPrice;

    private String goodsNum;

    private String orderTime;

    private String createTime;

    private LocalDate orderDate;

    private String user;

    private String payStatus;

}
