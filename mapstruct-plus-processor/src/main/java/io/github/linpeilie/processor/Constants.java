package io.github.linpeilie.processor;

import java.io.File;
import org.mapstruct.MappingConstants;

public class Constants {

    public static final String DEFAULT_BASE_PACKAGE = "io.github.linpeilie";

    public static final String DEFAULT_COMPONENT_MODEL = MappingConstants.ComponentModel.SPRING;

    public static final String DEFAULT_ADAPTER_CLASS_NAME = "ConvertMapperAdapter";

    public static final String DEFAULT_MAP_ADAPTER_CLASS_NAME = "MapConvertMapperAdapter";

    public static final String AUTO_MAPPER_CONFIG_CLASS_NAME = "AutoMapperConfig";

    public static final String AUTO_MAP_MAPPER_CONFIG_CLASS_NAME = "AutoMapMapperConfig";

    public static final String AUTO_MAPPER_ANNOTATION = "io.github.linpeilie.annotations.AutoMapper";

    public static final String AUTO_MAPPERS_ANNOTATION = "io.github.linpeilie.annotations.AutoMappers";

    public static final String AUTO_MAP_MAPPER_ANNOTATION = "io.github.linpeilie.annotations.AutoMapMapper";

    public static final String AUTO_ENUM_MAPPER_ANNOTATION = "io.github.linpeilie.annotations.AutoEnumMapper";

    public static final String MAPPER_CONFIG_ANNOTATION = "io.github.linpeilie.annotations.MapperConfig";

    public static final String MAPPER_ANNOTATION = "org.mapstruct.Mapper";

    public static final String COMPONENT_MODEL_CONFIG_ANNOTATION = "io.github.linpeilie.annotations.ComponentModelConfig";

    public static final String MAPSTRUCT_MAPPER_PACKAGE = "org.mapstruct";

    public static final String MAPSTRUCT_MAPPER_CLASS_NAME = "Mapper";

    public static final String MAPSTRUCT_PLUS_META_INF = "META-INF" + File.separator + "mps";

    public static final String MAPPER_CONFIG_FILE_NAME = "config";

    public static final String MAPPERS_FILE_NAME = "mappers";

    public static final String AUTO_MAPPER_FILE_NAME = "autoMapper";

    public static final String AUTO_MAPPERS_FILE_NAME = "autoMappers";

    public static final String AUTO_MAP_MAPPERS_FILE_NAME = "autoMapMappers";

    public static final String ENUM_MAPPERS_FILE_NAME = "enumMappers";

}
