package io.github.linpeilie.processor;

import static io.github.linpeilie.processor.Constants.*;

public class AutoMapperProperties {

    private static String mapperPackage;

    private static String componentModel = DEFAULT_COMPONENT_MODEL;

    public static String getMapperPackage() {
        return mapperPackage;
    }

    public static String getAdapterPackage() {
        return DEFAULT_BASE_PACKAGE;
    }

    public static String getAdapterClassName() {
        return DEFAULT_ADAPTER_CLASS_NAME;
    }

    public static String getMapAdapterClassName() {
        return DEFAULT_MAP_ADAPTER_CLASS_NAME;
    }

    public static String getConfigPackage() {
        return DEFAULT_BASE_PACKAGE;
    }

    public static String getConfigClassName() {
        return AUTO_MAPPER_CONFIG_CLASS_NAME;
    }

    public static String getMapConfigClassName() {
        return AUTO_MAP_MAPPER_CONFIG_CLASS_NAME;
    }

    public static String getComponentModel() {
        return componentModel;
    }

    public static void setMapperPackage(final String mapperPackage) {
        AutoMapperProperties.mapperPackage = mapperPackage;
    }

    public static void setComponentModel(final String componentModel) {
        AutoMapperProperties.componentModel = componentModel;
    }
}
