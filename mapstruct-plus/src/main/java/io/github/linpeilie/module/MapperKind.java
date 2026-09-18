package io.github.linpeilie.module;

/**
 * module-mappers 清单条目的产物类型
 *
 * @author shanhongyu
 * @see ModuleMappers
 */
public enum MapperKind {

    /**
     * 常规对象转换 mapper 实现类
     */
    BEAN("bean"),

    /**
     * Map 与对象互转 mapper 实现类
     */
    MAP("map"),

    /**
     * 循环引用规避 mapper 实现类
     */
    CYCLE("cycle"),

    /**
     * 转换适配器
     */
    ADAPTER("adapter");

    private final String manifestValue;

    MapperKind(String manifestValue) {
        this.manifestValue = manifestValue;
    }

    /**
     * 清单行中的类型标识
     */
    public String manifestValue() {
        return manifestValue;
    }

    /**
     * 按清单类型标识解析，未知标识返回 null
     */
    public static MapperKind fromManifestValue(String manifestValue) {
        if (manifestValue == null) {
            return null;
        }
        for (MapperKind kind : values()) {
            if (kind.manifestValue.equals(manifestValue)) {
                return kind;
            }
        }
        return null;
    }
}
