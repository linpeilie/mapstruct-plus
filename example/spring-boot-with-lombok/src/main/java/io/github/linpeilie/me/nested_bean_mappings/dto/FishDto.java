/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nested_bean_mappings.dto;

import lombok.Data;

@Data
public class FishDto {

    private String kind;

    // make sure that mapping on name does not happen based on name mapping
    private String name;

}
