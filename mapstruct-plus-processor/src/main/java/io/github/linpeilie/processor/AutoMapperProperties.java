package io.github.linpeilie.processor;

import org.mapstruct.Builder;
import org.mapstruct.ReportingPolicy;

import static io.github.linpeilie.processor.Constants.*;

public class AutoMapperProperties {

    private static String mapperPackage;

    private static String componentModel = DEFAULT_COMPONENT_MODEL;

    private static ReportingPolicy unmappedSourcePolicy = ReportingPolicy.IGNORE;

    private static ReportingPolicy unmappedTargetPolicy = ReportingPolicy.IGNORE;

    private static String buildMethod = "build";

    private static boolean disableBuilder = true;

    private static String adapterPackage = DEFAULT_BASE_PACKAGE;

    private static String adapterClassName = DEFAULT_ADAPTER_CLASS_NAME;

    private static String mapAdapterClassName = DEFAULT_MAP_ADAPTER_CLASS_NAME;

    public static String getMapperPackage() {
        return mapperPackage;
    }

    public static String getAdapterPackage() {
        return adapterPackage;
    }

    public static void setAdapterPackage(final String adapterPackage) {
        AutoMapperProperties.adapterPackage = adapterPackage;
    }

    public static String getAdapterClassName() {
        return adapterClassName;
    }

    public static void setAdapterClassName(final String adapterClassName) {
        AutoMapperProperties.adapterClassName = adapterClassName;
    }

    public static String getMapAdapterClassName() {
        return mapAdapterClassName;
    }

    public static void setMapAdapterClassName(final String mapAdapterClassName) {
        AutoMapperProperties.mapAdapterClassName = mapAdapterClassName;
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

    public static ReportingPolicy getUnmappedSourcePolicy() {
        return unmappedSourcePolicy;
    }

    public static void setUnmappedSourcePolicy(final ReportingPolicy unmappedSourcePolicy) {
        AutoMapperProperties.unmappedSourcePolicy = unmappedSourcePolicy;
    }

    public static ReportingPolicy getUnmappedTargetPolicy() {
        return unmappedTargetPolicy;
    }

    public static void setUnmappedTargetPolicy(final ReportingPolicy unmappedTargetPolicy) {
        AutoMapperProperties.unmappedTargetPolicy = unmappedTargetPolicy;
    }

    public static String getBuildMethod() {
        return buildMethod;
    }

    public static void setBuildMethod(final String buildMethod) {
        AutoMapperProperties.buildMethod = buildMethod;
    }

    public static boolean isDisableBuilder() {
        return disableBuilder;
    }

    public static void setDisableBuilder(final boolean disableBuilder) {
        AutoMapperProperties.disableBuilder = disableBuilder;
    }
}
