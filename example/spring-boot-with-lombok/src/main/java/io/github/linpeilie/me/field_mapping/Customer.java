/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.field_mapping;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.Collection;
import lombok.Data;

@Data
@AutoMapper(target = CustomerDto.class)
public class Customer {

    private Long id;

    @AutoMapping(target = "customerName")
    private String name;

    @AutoMapping(target = "orders")
    private Collection<OrderItem> orderItems;

}
