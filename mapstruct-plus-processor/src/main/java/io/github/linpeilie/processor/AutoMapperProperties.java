package io.github.linpeilie.processor;

import org.mapstruct.Builder;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import static io.github.linpeilie.processor.Constants.*;

public class AutoMapperProperties {

    private static String mapperPackage;

    private static String componentModel = DEFAULT_COMPONENT_MODEL;

    private static ReportingPolicy unmappedSourcePolicy = ReportingPolicy.IGNORE;

    private static ReportingPolicy unmappedTargetPolicy = ReportingPolicy.IGNORE;

    private static NullValueMappingStrategy nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL;

    private static NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL;

    private static String buildMethod = "build";

    private static boolean disableBuilder = true;

    private static String adapterPackage = DEFAULT_BASE_PACKAGE;

    private static String adapterClassName = DEFAULT_ADAPTER_CLASS_NAME;

    private static String cycleAvoidingAdapterClassName = DEFAULT_CYCLE_AVOIDING_ADAPTER_CLASS_NAME;

    private static String mapAdapterClassName = DEFAULT_MAP_ADAPTER_CLASS_NAME;

    private static String autoConfigPackage = DEFAULT_BASE_PACKAGE;

    private static String autoMapperConfigClassName = AUTO_MAPPER_CONFIG_CLASS_NAME;

    private static String autoCycleAvoidingMapperConfigClassName = AUTO_CYCLE_AVOIDING_MAPPER_CONFIG_CLASS_NAME;

    private static String autoMapMapperConfigClassName = AUTO_MAP_MAPPER_CONFIG_CLASS_NAME;

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

    public static String getCycleAvoidingAdapterClassName() {
        return cycleAvoidingAdapterClassName;
    }

    public static void setCycleAvoidingAdapterClassName(final String cycleAvoidingAdapterClassName) {
        AutoMapperProperties.cycleAvoidingAdapterClassName = cycleAvoidingAdapterClassName;
    }

    public static String getMapAdapterClassName() {
        return mapAdapterClassName;
    }

    public static void setMapAdapterClassName(final String mapAdapterClassName) {
        AutoMapperProperties.mapAdapterClassName = mapAdapterClassName;
    }

    public static String getConfigPackage() {
        return autoConfigPackage;
    }

    public static void setAutoConfigPackage(String autoConfigPackage) {
        AutoMapperProperties.autoConfigPackage = autoConfigPackage;
    }

    public static String getConfigClassName() {
        return autoMapperConfigClassName;
    }

    public static void setAutoMapperConfigClassName(String autoMapperConfigClassName) {
        AutoMapperProperties.autoMapperConfigClassName = autoMapperConfigClassName;
    }

    public static String getCycleAvoidingConfigClassName() {
        return autoCycleAvoidingMapperConfigClassName;
    }

    public static void setAutoCycleAvoidingMapperConfigClassName(String autoCycleAvoidingMapperConfigClassName) {
        AutoMapperProperties.autoCycleAvoidingMapperConfigClassName = autoCycleAvoidingMapperConfigClassName;
    }

    public static String getMapConfigClassName() {
        return autoMapMapperConfigClassName;
    }

    public static void setAutoMapMapperConfigClassName(String autoMapMapperConfigClassName) {
        AutoMapperProperties.autoMapMapperConfigClassName = autoMapMapperConfigClassName;
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

    public static NullValueMappingStrategy getNullValueMappingStrategy() {
        return nullValueMappingStrategy;
    }

    public static void setNullValueMappingStrategy(NullValueMappingStrategy nullValueMappingStrategy) {
        AutoMapperProperties.nullValueMappingStrategy = nullValueMappingStrategy;
    }

    public static NullValuePropertyMappingStrategy getNullValuePropertyMappingStrategy() {
        return nullValuePropertyMappingStrategy;
    }

    public static void setNullValuePropertyMappingStrategy(NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy) {
        AutoMapperProperties.nullValuePropertyMappingStrategy = nullValuePropertyMappingStrategy;
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
