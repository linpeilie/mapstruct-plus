package io.github.linpeilie.processor;

import com.squareup.javapoet.ClassName;
import io.github.linpeilie.processor.utils.FileUtils;
import io.github.linpeilie.processor.utils.IncrementMarkUtils;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

public class AutoMapperProperties {

    private static String mapperPackage;

    private static String componentModel = ContextConstants.ComponentModelConfig.defaultComponentModel;

    private static String unmappedSourcePolicy;

    private static String unmappedTargetPolicy = "IGNORE";

    private static String typeConversionPolicy;

    private static String collectionMappingStrategy;

    private static String nullValueMappingStrategy;

    private static String nullValueIterableMappingStrategy;

    private static String nullValueMapMappingStrategy;

    private static String nullValuePropertyMappingStrategy;

    private static String nullValueCheckStrategy;

    private static ClassName mappingControl;

    private static ClassName unexpectedValueMappingException;

    private static Boolean suppressTimestampInGenerated;

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
        adapterClassName = adapterClassName + "__" + mark;
        mapAdapterClassName = mapAdapterClassName + "__" + mark;
        autoMapperConfigClassName = autoMapperConfigClassName + "__" + mark;
        autoMapMapperConfigClassName = autoMapMapperConfigClassName + "__" + mark;
    }

    public static String getConfigClassName() {
        return autoMapperConfigClassName;
    }

    public static String getMapConfigClassName() {
        return autoMapMapperConfigClassName;
    }

    public static String getConfigPackage() {
        return autoConfigPackage;
    }

    public static String getCycleAvoidingAdapterClassName() {
        return getAdapterClassName() + "ForCycleAvoiding";
    }

    public static String getCycleAvoidingConfigClassName() {
        return getConfigClassName() + "ForCycleAvoiding";
    }

    /* ******************** getter/setter ******************** */

    public static String getMapperPackage() {
        return mapperPackage;
    }

    public static void setMapperPackage(String mapperPackage) {
        AutoMapperProperties.mapperPackage = mapperPackage;
    }

    public static String getComponentModel() {
        return componentModel;
    }

    public static void setComponentModel(String componentModel) {
        AutoMapperProperties.componentModel = componentModel;
    }

    public static String getUnmappedSourcePolicy() {
        return unmappedSourcePolicy;
    }

    public static void setUnmappedSourcePolicy(String unmappedSourcePolicy) {
        AutoMapperProperties.unmappedSourcePolicy = unmappedSourcePolicy;
    }

    public static String getUnmappedTargetPolicy() {
        return unmappedTargetPolicy;
    }

    public static void setUnmappedTargetPolicy(String unmappedTargetPolicy) {
        AutoMapperProperties.unmappedTargetPolicy = unmappedTargetPolicy;
    }

    public static String getTypeConversionPolicy() {
        return typeConversionPolicy;
    }

    public static void setTypeConversionPolicy(String typeConversionPolicy) {
        AutoMapperProperties.typeConversionPolicy = typeConversionPolicy;
    }

    public static String getCollectionMappingStrategy() {
        return collectionMappingStrategy;
    }

    public static void setCollectionMappingStrategy(String collectionMappingStrategy) {
        AutoMapperProperties.collectionMappingStrategy = collectionMappingStrategy;
    }

    public static String getNullValueMappingStrategy() {
        return nullValueMappingStrategy;
    }

    public static void setNullValueMappingStrategy(String nullValueMappingStrategy) {
        AutoMapperProperties.nullValueMappingStrategy = nullValueMappingStrategy;
    }

    public static String getNullValueIterableMappingStrategy() {
        return nullValueIterableMappingStrategy;
    }

    public static void setNullValueIterableMappingStrategy(String nullValueIterableMappingStrategy) {
        AutoMapperProperties.nullValueIterableMappingStrategy = nullValueIterableMappingStrategy;
    }

    public static String getNullValueMapMappingStrategy() {
        return nullValueMapMappingStrategy;
    }

    public static void setNullValueMapMappingStrategy(String nullValueMapMappingStrategy) {
        AutoMapperProperties.nullValueMapMappingStrategy = nullValueMapMappingStrategy;
    }

    public static String getNullValuePropertyMappingStrategy() {
        return nullValuePropertyMappingStrategy;
    }

    public static void setNullValuePropertyMappingStrategy(String nullValuePropertyMappingStrategy) {
        AutoMapperProperties.nullValuePropertyMappingStrategy = nullValuePropertyMappingStrategy;
    }

    public static String getNullValueCheckStrategy() {
        return nullValueCheckStrategy;
    }

    public static void setNullValueCheckStrategy(String nullValueCheckStrategy) {
        AutoMapperProperties.nullValueCheckStrategy = nullValueCheckStrategy;
    }

    public static ClassName getMappingControl() {
        return mappingControl;
    }

    public static void setMappingControl(ClassName mappingControl) {
        AutoMapperProperties.mappingControl = mappingControl;
    }

    public static ClassName getUnexpectedValueMappingException() {
        return unexpectedValueMappingException;
    }

    public static void setUnexpectedValueMappingException(ClassName unexpectedValueMappingException) {
        AutoMapperProperties.unexpectedValueMappingException = unexpectedValueMappingException;
    }

    public static Boolean getSuppressTimestampInGenerated() {
        return suppressTimestampInGenerated;
    }

    public static void setSuppressTimestampInGenerated(Boolean suppressTimestampInGenerated) {
        AutoMapperProperties.suppressTimestampInGenerated = suppressTimestampInGenerated;
    }

    public static String getBuildMethod() {
        return buildMethod;
    }

    public static void setBuildMethod(String buildMethod) {
        AutoMapperProperties.buildMethod = buildMethod;
    }

    public static boolean isDisableBuilder() {
        return disableBuilder;
    }

    public static void setDisableBuilder(boolean disableBuilder) {
        AutoMapperProperties.disableBuilder = disableBuilder;
    }

    public static String getAdapterPackage() {
        return adapterPackage;
    }

    public static void setAdapterPackage(String adapterPackage) {
        AutoMapperProperties.adapterPackage = adapterPackage;
    }

    public static String getAdapterClassName() {
        return adapterClassName;
    }

    public static void setAdapterClassName(String adapterClassName) {
        AutoMapperProperties.adapterClassName = adapterClassName;
    }

    public static String getMapAdapterClassName() {
        return mapAdapterClassName;
    }

    public static void setMapAdapterClassName(String mapAdapterClassName) {
        AutoMapperProperties.mapAdapterClassName = mapAdapterClassName;
    }

    public static String getAutoConfigPackage() {
        return autoConfigPackage;
    }

    public static void setAutoConfigPackage(String autoConfigPackage) {
        AutoMapperProperties.autoConfigPackage = autoConfigPackage;
    }

    public static String getAutoMapperConfigClassName() {
        return autoMapperConfigClassName;
    }

    public static void setAutoMapperConfigClassName(String autoMapperConfigClassName) {
        AutoMapperProperties.autoMapperConfigClassName = autoMapperConfigClassName;
    }

    public static String getAutoMapMapperConfigClassName() {
        return autoMapMapperConfigClassName;
    }

    public static void setAutoMapMapperConfigClassName(String autoMapMapperConfigClassName) {
        AutoMapperProperties.autoMapMapperConfigClassName = autoMapMapperConfigClassName;
    }
}
