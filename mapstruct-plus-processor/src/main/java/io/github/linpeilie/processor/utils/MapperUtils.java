package io.github.linpeilie.processor.utils;

import io.github.linpeilie.processor.AutoMapperProperties;
import io.github.linpeilie.utils.StrUtil;

public class MapperUtils {

    public static String getMapperClassName(String sourceQualifiedClassName, String targetQualifiedClassName) {
        String[] sourceQualifiedClassNames = sourceQualifiedClassName.split("\\.");
        String[] targetQualifiedClassNames = targetQualifiedClassName.split("\\.");
        String mapperClassName = sourceQualifiedClassNames[sourceQualifiedClassNames.length - 1]
                                 + "To"
                                 + targetQualifiedClassNames[targetQualifiedClassNames.length - 1];
        return mapperClassName.substring(0, 1).toUpperCase() + mapperClassName.substring(1) + "Mapper";
    }

    public static String getEnumMapperClassName(String sourceSimpleName) {
        return sourceSimpleName + "Mapper";
    }

    public static String getMapperPackage(String sourcePackage) {
        return StrUtil.isNotEmpty(AutoMapperProperties.getMapperPackage())
               ? AutoMapperProperties.getMapperPackage() : sourcePackage;

    }

}
