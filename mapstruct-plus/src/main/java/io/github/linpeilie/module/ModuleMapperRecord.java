package io.github.linpeilie.module;

import java.util.Objects;

/**
 * module-mappers 清单中的一条记录：{@code <kind>=<实现类完整类名>}
 *
 * @author shanhongyu
 * @see ModuleMappers
 */
public final class ModuleMapperRecord {

    private final MapperKind kind;

    private final String mapperClassName;

    public ModuleMapperRecord(MapperKind kind, String mapperClassName) {
        this.kind = Objects.requireNonNull(kind, "kind must not be null");
        this.mapperClassName = Objects.requireNonNull(mapperClassName, "mapperClassName must not be null");
    }

    /**
     * 产物类型
     */
    public MapperKind getKind() {
        return kind;
    }

    /**
     * 产物实现类完整类名
     */
    public String getMapperClassName() {
        return mapperClassName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModuleMapperRecord)) {
            return false;
        }
        ModuleMapperRecord that = (ModuleMapperRecord) o;
        return kind == that.kind && mapperClassName.equals(that.mapperClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, mapperClassName);
    }

    @Override
    public String toString() {
        return kind.manifestValue() + "=" + mapperClassName;
    }
}
