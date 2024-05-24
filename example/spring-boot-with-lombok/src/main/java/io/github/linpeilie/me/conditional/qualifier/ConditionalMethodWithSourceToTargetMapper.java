/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.conditional.qualifier;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * @author Filip Hrisafov
 */
@Component
public class ConditionalMethodWithSourceToTargetMapper {

    @Condition
    @Named("mapCustomerFromOrder")
    public boolean mapCustomerFromOrder(OrderDTO orderDTO) {
        return orderDTO != null && (orderDTO.getCustomerName() != null || mapAddressFromOrder(orderDTO));
    }

    @Condition
    @Named("mapAddressFromOrder")
    public boolean mapAddressFromOrder(OrderDTO orderDTO) {
        return orderDTO != null && (orderDTO.getLine1() != null || orderDTO.getLine2() != null);
    }

}
