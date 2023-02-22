package io.github.linpl.processor;

public class Constants {

    private static final String DEFAULT_BASE_PACKAGE = AutoMapperProcessor.class.getPackage().getName();

    public static final String DEFAULT_MAPPER_PACKAGE = DEFAULT_BASE_PACKAGE + ".mappers";

    public static final String DEFAULT_ADAPTER_CLASS_NAME = "ConvertMapperAdapter";


    public static final String AUTO_MAPPER_CONFIG_CLASS_NAME = "AutoMapperConfig";

    public static final String AUTO_MAPPER_ANNOTATION = "io.github.linpl.annotations.AutoMapper";

    public static final String MAPPER_CONFIG_ANNOTATION = "io.github.linpl.annotations.MapperConfig";

    public static final String BASE_MAPPER_PACKAGE = "io.github.linpl";

    public static final String BASE_MAPPER_CLASS_NAME = "BaseMapper";

    public static final String MAPSTRUCT_MAPPER_PACKAGE = "org.mapstruct";

    public static final String MAPSTRUCT_MAPPER_CLASS_NAME = "Mapper";


}
