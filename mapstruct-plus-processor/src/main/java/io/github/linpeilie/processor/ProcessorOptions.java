package io.github.linpeilie.processor;

import io.github.linpeilie.utils.StrUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ProcessorOptions {

    public static final String MAPPER_CONFIG_CLASS = "mapstruct.plus.mapperConfigClass";

    public static final String DEFAULT_COMPONENT_MODEL = "mapstruct.defaultComponentModel";

    public static final String MAPPER_PACKAGE = "mapstruct.plus.mapperPackage";

    public static final String UNMAPPED_SOURCE_POLICY = "mapstruct.plus.unmappedSourcePolicy";

    public static final String UNMAPPED_TARGET_POLICY = "mapstruct.plus.unmappedTargetPolicy";

    public static final String TYPE_CONVERSION_POLICY = "mapstruct.plus.typeConversionPolicy";

    public static final String COLLECTION_MAPPING_STRATEGY = "mapstruct.plus.collectionMappingStrategy";

    public static final String NULL_VALUE_MAPPING_STRATEGY = "mapstruct.plus.nullValueMappingStrategy";

    public static final String NULL_VALUE_ITERABLE_MAPPING_STRATEGY = "mapstruct.plus.nullValueIterableMappingStrategy";

    public static final String NULL_VALUE_MAP_MAPPING_STRATEGY = "mapstruct.plus.nullValueMapMappingStrategy";

    public static final String NULL_VALUE_PROPERTY_MAPPING_STRATEGY = "mapstruct.plus.nullValuePropertyMappingStrategy";

    public static final String NULL_VALUE_CHECK_STRATEGY = "mapstruct.plus.nullValueCheckStrategy";

    public static final String SUPPRESS_TIMESTAMP_IN_GENERATED = "mapstruct.plus.suppressTimestampInGenerated";

    public static final String BUILDER_BUILD_METHOD = "mapstruct.plus.builder.buildMethod";

    public static final String BUILDER_DISABLE_BUILDER = "mapstruct.plus.builder.disableBuilder";

    public static final String ADAPTER_PACKAGE = "mapstruct.plus.adapterPackage";

    public static final String ADAPTER_CLASS_NAME = "mapstruct.plus.adapterClassName";

    public static final String MAP_ADAPTER_CLASS_NAME = "mapstruct.plus.mapAdapterClassName";

    public static final String AUTO_CONFIG_PACKAGE = "mapstruct.plus.autoConfigPackage";

    public static final String AUTO_MAPPER_CONFIG_CLASS_NAME = "mapstruct.plus.autoMapperConfigClassName";

    public static final String AUTO_MAP_MAPPER_CONFIG_CLASS_NAME = "mapstruct.plus.autoMapMapperConfigClassName";

    public static Map<String, Consumer<String>> optionConsumers() {
        final Map<String, Consumer<String>> consumerMap = new HashMap<>();

        consumerMap.put(DEFAULT_COMPONENT_MODEL, AutoMapperProperties::setComponentModel);

        consumerMap.put(MAPPER_PACKAGE, AutoMapperProperties::setMapperPackage);
        consumerMap.put(UNMAPPED_SOURCE_POLICY, AutoMapperProperties::setUnmappedSourcePolicy);
        consumerMap.put(UNMAPPED_TARGET_POLICY, AutoMapperProperties::setUnmappedTargetPolicy);
        consumerMap.put(TYPE_CONVERSION_POLICY, AutoMapperProperties::setTypeConversionPolicy);
        consumerMap.put(COLLECTION_MAPPING_STRATEGY, AutoMapperProperties::setCollectionMappingStrategy);
        consumerMap.put(NULL_VALUE_MAPPING_STRATEGY, AutoMapperProperties::setNullValueMappingStrategy);
        consumerMap.put(NULL_VALUE_ITERABLE_MAPPING_STRATEGY, AutoMapperProperties::setNullValueIterableMappingStrategy);
        consumerMap.put(NULL_VALUE_MAP_MAPPING_STRATEGY, AutoMapperProperties::setNullValueMapMappingStrategy);
        consumerMap.put(NULL_VALUE_PROPERTY_MAPPING_STRATEGY, AutoMapperProperties::setNullValuePropertyMappingStrategy);
        consumerMap.put(NULL_VALUE_CHECK_STRATEGY, AutoMapperProperties::setNullValueCheckStrategy);
        consumerMap.put(SUPPRESS_TIMESTAMP_IN_GENERATED, value -> {
            if (StrUtil.isNotEmpty(value)) {
                AutoMapperProperties.setSuppressTimestampInGenerated(Boolean.parseBoolean(value));
            }
        });
        consumerMap.put(BUILDER_BUILD_METHOD, AutoMapperProperties::setBuildMethod);
        consumerMap.put(BUILDER_DISABLE_BUILDER,
            value -> AutoMapperProperties.setDisableBuilder(Boolean.parseBoolean(value)));
        consumerMap.put(ADAPTER_PACKAGE, AutoMapperProperties::setAdapterPackage);
        consumerMap.put(ADAPTER_CLASS_NAME, AutoMapperProperties::setAdapterClassName);
        consumerMap.put(MAP_ADAPTER_CLASS_NAME, AutoMapperProperties::setMapAdapterClassName);
        consumerMap.put(AUTO_CONFIG_PACKAGE, AutoMapperProperties::setAutoConfigPackage);
        consumerMap.put(AUTO_MAPPER_CONFIG_CLASS_NAME, AutoMapperProperties::setAutoMapperConfigClassName);
        consumerMap.put(AUTO_MAP_MAPPER_CONFIG_CLASS_NAME, AutoMapperProperties::setAutoMapMapperConfigClassName);
        return consumerMap;
    }

}
