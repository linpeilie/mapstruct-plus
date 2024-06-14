/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullvaluepropertymapping;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;

@MapperConfig( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface NvpmsConfig {
}
