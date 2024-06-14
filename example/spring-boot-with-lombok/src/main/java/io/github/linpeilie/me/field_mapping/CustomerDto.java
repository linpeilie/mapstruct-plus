/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.field_mapping;

import java.util.List;
import lombok.Data;

@Data
public class CustomerDto {

    public Long id;
    public String customerName;
    public List<OrderItemDto> orders;
}
