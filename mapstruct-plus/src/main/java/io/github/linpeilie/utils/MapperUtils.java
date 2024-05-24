package io.github.linpeilie.utils;

public class MapperUtils {

    public static String getMapperClassName(String sourceQualifiedClassName, String targetQualifiedClassName) {
        String[] sourceQualifiedClassNames = sourceQualifiedClassName.split("\\.");
        String[] targetQualifiedClassNames = targetQualifiedClassName.split("\\.");
        String mapperClassName = sourceQualifiedClassNames[sourceQualifiedClassNames.length - 1]
                                 + "To"
                                 + targetQualifiedClassNames[targetQualifiedClassNames.length - 1];
        return mapperClassName.substring(0, 1).toUpperCase() + mapperClassName.substring(1) + "Mapper";
    }

}
