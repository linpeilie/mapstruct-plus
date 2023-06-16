package io.github.linpeilie.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

public class ProcessorOptions {

    public static final String MAPPER_CONFIG_CLASS = "mapstruct.plus.mapperConfigClass";

    public static final String DEFAULT_COMPONENT_MODEL = "mapstruct.defaultComponentModel";

    public static final String MAPPER_PACKAGE = "mapstruct.plus.mapperPackage";

    public static final String UNMAPPED_SOURCE_POLICY = "mapstruct.plus.unmappedSourcePolicy";

    public static final String UNMAPPED_TARGET_POLICY = "mapstruct.plus.unmappedTargetPolicy";

    public static final String NULL_VALUE_MAPPING_STRATEGY = "mapstruct.plus.nullValueMappingStrategy";

    public static final String NULL_VALUE_PROPERTY_MAPPING_STRATEGY = "mapstruct.plus.nullValuePropertyMappingStrategy";

    public static final String BUILDER_BUILD_METHOD = "mapstruct.plus.builder.buildMethod";

    public static final String BUILDER_DISABLE_BUILDER = "mapstruct.plus.builder.disableBuilder";

    public static final String ADAPTER_PACKAGE = "mapstruct.plus.adapterPackage";

    public static final String ADAPTER_CLASS_NAME = "mapstruct.plus.adapterClassName";

    public static final String MAP_ADAPTER_CLASS_NAME = "mapstruct.plus.mapAdapterClassName";

    public static Map<String, Consumer<String>> optionConsumers() {
        final Map<String, Consumer<String>> consumerMap = new HashMap<>();

        consumerMap.put(DEFAULT_COMPONENT_MODEL, AutoMapperProperties::setComponentModel);

        consumerMap.put(MAPPER_PACKAGE, AutoMapperProperties::setMapperPackage);
        consumerMap.put(UNMAPPED_SOURCE_POLICY, value -> AutoMapperProperties.setUnmappedSourcePolicy(ReportingPolicy.valueOf(value)));
        consumerMap.put(UNMAPPED_TARGET_POLICY, value -> AutoMapperProperties.setUnmappedTargetPolicy(ReportingPolicy.valueOf(value)));
        consumerMap.put(NULL_VALUE_MAPPING_STRATEGY,
            value -> AutoMapperProperties.setNullValueMappingStrategy(NullValueMappingStrategy.valueOf(value)));
        consumerMap.put(NULL_VALUE_PROPERTY_MAPPING_STRATEGY,
            value -> AutoMapperProperties.setNullValuePropertyMappingStrategy(
                NullValuePropertyMappingStrategy.valueOf(value)
            ));
        consumerMap.put(BUILDER_BUILD_METHOD, AutoMapperProperties::setBuildMethod);
        consumerMap.put(BUILDER_DISABLE_BUILDER,
            value -> AutoMapperProperties.setDisableBuilder(Boolean.parseBoolean(value)));
        consumerMap.put(ADAPTER_PACKAGE, AutoMapperProperties::setAdapterPackage);
        consumerMap.put(ADAPTER_CLASS_NAME, AutoMapperProperties::setAdapterClassName);
        consumerMap.put(MAP_ADAPTER_CLASS_NAME, AutoMapperProperties::setMapAdapterClassName);
        return consumerMap;
    }

}
