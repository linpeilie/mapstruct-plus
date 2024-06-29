package io.github.linpeilie.processor;

import io.github.linpeilie.DefaultMapping;
import java.io.File;
import org.mapstruct.MappingConstants;

/**
 * 上下文常量
 */
public interface ContextConstants {

    /**
     * BaseMapper 接口
     */
    interface BaseMapper {
        String packageName = "io.github.linpeilie";
        String className = "BaseMapper";
    }

    /**
     * BaseMapMapper 接口
     */
    interface BaseMapMapper {
        String packageName = "io.github.linpeilie";
        String className = "BaseMapMapper";
    }

    /**
     * BaseCycleAvoidingMapper 接口
     */
    interface BaseCycleAvoidingMapper {
        String packageName = "io.github.linpeilie";
        String className = "BaseCycleAvoidingMapper";
    }

    interface Mapper {
        String qualifiedClassName = "org.mapstruct.Mapper";
        String packageName = "org.mapstruct";
        String className = "Mapper";
    }

    interface AutoMapping {
        String qualifiedClassName = "io.github.linpeilie.annotations.AutoMapping";
    }

    interface AutoMapper {
        String qualifiedClassName = "io.github.linpeilie.annotations.AutoMapper";
    }

    interface AutoMappers {
        String qualifiedClassName = "io.github.linpeilie.annotations.AutoMappers";
    }

    interface AutoMapMapper {
        String qualifiedClassName = "io.github.linpeilie.annotations.AutoMapMapper";
    }

    interface AutoEnumMapper {
        String qualifiedClassName = "io.github.linpeilie.annotations.AutoEnumMapper";
    }

    interface MapperConfig {
        String qualifiedClassName = "io.github.linpeilie.annotations.MapperConfig";
    }

    interface ComponentModelConfig {
        String qualifiedClassName = "io.github.linpeilie.annotations.ComponentModelConfig";
        String springLazy = "spring-lazy";
        String defaultComponentModel = springLazy;
    }

    interface ConvertAdapter {
        String packageName = "io.github.linpeilie";
        String convertMapperAdapterClassName = "ConverterMapperAdapter";
        String mapConvertMapperAdapterClassName = "MapConvertMapperAdapter";
    }

    interface AutoConfig {
        String packageName = "io.github.linpeilie";
        String autoMapperConfigClassName = "AutoMapperConfig";
        String autoMapMapperConfigClassName = "AutoMapMapperConfig";
    }

    interface Annotations {
        String mapper = Mapper.qualifiedClassName;
        String autoMapper = AutoMapper.qualifiedClassName;
        String autoMappers = AutoMappers.qualifiedClassName;
        String autoMapMapper = AutoMapMapper.qualifiedClassName;
        String autoEnumMapper = AutoEnumMapper.qualifiedClassName;
        String mapperConfig = MapperConfig.qualifiedClassName;
        String componentModel = ComponentModelConfig.qualifiedClassName;
    }

    interface MetaInf {
        String folder = "META-INF/mps/";
        String mapperConfig = "config";
        String mappers = "mappers";
        String autoMapper = "autoMapper";
        String autoMappers = "autoMappers";
        String autoMapMappers = "autoMapMappers";
        String enumMappers = "enumMappers";
        String uses = "uses";
    }

    interface AutoIncrementFile {
        String file = System.getProperty("user.home") + File.separator + ".msp" + File.separator + "incrementMark";
    }

    interface Map {
        String packageName = "java.util";
        String className = "Map";
    }

    interface MapObjectConvert {
        String packageName = "io.github.linpeilie.map";
        String className = "MapObjectConvert";
    }

    interface DoIgnore {
        String packageName = "io.github.linpeilie.annotations";
        String className = "DoIgnore";
    }

    interface MappingControl {
        String packageName = "org.mapstruct.control";
        String className = "MappingControl";
        String qualifiedName = packageName + "." + className;
    }

    interface NullValueCheckStrategy {
        String packageName = "org.mapstruct";
        String className = "NullValueCheckStrategy";
    }

    interface NullValuePropertyMappingStrategy {
        String packageName = "org.mapstruct";
        String className = "NullValuePropertyMappingStrategy";
    }

    interface ReportingPolicy {
        String packageName = "org.mapstruct";
        String className = "ReportingPolicy";
    }

    interface CollectionMappingStrategy {
        String packageName = "org.mapstruct";
        String className = "CollectionMappingStrategy";
    }

    interface NullValueMappingStrategy {
        String packageName = "org.mapstruct";
        String className = "NullValueMappingStrategy";
    }

    interface MappingInheritanceStrategy {
        String packageName = "org.mapstruct";
        String className = "MappingInheritanceStrategy";
    }

    interface InjectionStrategy {
        String packageName = "org.mapstruct";
        String className = "InjectionStrategy";
    }

}
