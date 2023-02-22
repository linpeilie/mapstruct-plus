package io.github.linpl.processor;

import static io.github.linpl.processor.Constants.*;

public class AutoMapperProperties {


    private static String mapperPackage = DEFAULT_MAPPER_PACKAGE;

    private static String componentModel = DEFAULT_COMPONENT_MODEL;

    private static final String adapterClassName = DEFAULT_ADAPTER_CLASS_NAME;

    private static final String configClassName = AUTO_MAPPER_CONFIG_CLASS_NAME;

    public static String getMapperPackage() {
        return mapperPackage;
    }

    public static String getAdapterPackage() {
        return getMapperPackage();
    }

    public static String getAdapterClassName() {
        return adapterClassName;
    }

    public static String getConfigClassName() {
        return configClassName;
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
