package io.github.linpeilie.processor.gem;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import io.github.linpeilie.annotations.MapperConfig;
import io.github.linpeilie.annotations.ReverseAutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMappings;
import org.mapstruct.Builder;
import org.mapstruct.tools.gem.GemDefinition;

@GemDefinition(AutoMapper.class)
@GemDefinition(AutoMappers.class)
@GemDefinition(AutoMapping.class)
@GemDefinition(AutoMappings.class)
@GemDefinition(MapperConfig.class)
@GemDefinition(ReverseAutoMapping.class)
@GemDefinition(ReverseAutoMappings.class)
@GemDefinition(Builder.class)
public class GemGenerator {
}
