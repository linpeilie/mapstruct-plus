package io.github.linpeilie.processor;

import io.github.linpeilie.processor.utils.FileUtils;
import io.github.linpeilie.processor.utils.IncrementMarkUtils;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

public class AutoMapperProperties {

    private static String mapperPackage;

    private static String componentModel = ContextConstants.ComponentModelConfig.defaultComponentModel;

    private static ReportingPolicy unmappedSourcePolicy = ReportingPolicy.IGNORE;

    private static ReportingPolicy unmappedTargetPolicy = ReportingPolicy.IGNORE;

    private static NullValueMappingStrategy nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL;

    private static NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy =
        NullValuePropertyMappingStrategy.SET_TO_NULL;

    private static String buildMethod = "build";

    private static boolean disableBuilder = true;

    private static String adapterPackage = ContextConstants.ConvertAdapter.packageName;

    private static String adapterClassName = ContextConstants.ConvertAdapter.convertMapperAdapterClassName;

    private static String mapAdapterClassName = ContextConstants.ConvertAdapter.mapConvertMapperAdapterClassName;

    private static String autoConfigPackage = ContextConstants.AutoConfig.packageName;

    private static String autoMapperConfigClassName = ContextConstants.AutoConfig.autoMapperConfigClassName;

    private static String autoMapMapperConfigClassName = ContextConstants.AutoConfig.autoMapMapperConfigClassName;

    static {
        // load increment mark
        Integer mark = IncrementMarkUtils.incrementAndGet();
        adapterClassName = adapterClassName + "$" + mark;
        mapAdapterClassName = mapAdapterClassName + "$" + mark;
        autoMapperConfigClassName = autoMapperConfigClassName + "$" + mark;
        autoMapMapperConfigClassName = autoMapMapperConfigClassName + "$" + mark;
    }

    /* ******************** getter/setter ******************** */

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

    public static String getCycleAvoidingAdapterClassName() {
        return getAdapterClassName() + "ForCycleAvoiding";
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
        return autoConfigPackage;
    }

    public static void setAutoConfigPackage(String autoConfigPackage) {
        AutoMapperProperties.autoConfigPackage = autoConfigPackage;
    }

    public static String getConfigClassName() {
        return autoMapperConfigClassName;
    }

    public static String getCycleAvoidingConfigClassName() {
        return getConfigClassName() + "ForCycleAvoiding";
    }

    public static void setAutoMapperConfigClassName(String autoMapperConfigClassName) {
        AutoMapperProperties.autoMapperConfigClassName = autoMapperConfigClassName;
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
