package io.github.linpeilie.me.conditional.expression;

public class Util {

    static boolean mapCustomerFromOrder(OrderDTO orderDTO) {
        return orderDTO != null && (orderDTO.getCustomerName() != null || mapAddressFromOrder(orderDTO));
    }

    static boolean mapAddressFromOrder(OrderDTO orderDTO) {
        return orderDTO != null && (orderDTO.getLine1() != null || orderDTO.getLine2() != null);
    }

}
