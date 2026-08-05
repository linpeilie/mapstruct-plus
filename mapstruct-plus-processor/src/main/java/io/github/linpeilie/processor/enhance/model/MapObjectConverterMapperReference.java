package io.github.linpeilie.processor.enhance.model;

import java.util.HashSet;
import java.util.Set;
import org.mapstruct.ap.internal.model.MapperReference;
import org.mapstruct.ap.internal.model.common.Type;

/**
 * MapObjectConverter 实现类的 Mapper 引用。
 * <p>
 * 生成 {@code MapObjectConverter.getInstance(XxxConverter.class)} 初始化代码，
 * 通过接口的静态单例工厂获取实例，而非 Spring Bean 注入。
 *
 * @since 1.5.2
 */
public class MapObjectConverterMapperReference extends MapperReference {

    private final Type mapObjectConverterType;

    public MapObjectConverterMapperReference(Type type, String variableName, boolean isUsed,
        Type mapObjectConverterType) {
        super(type, variableName, isUsed);
        this.mapObjectConverterType = mapObjectConverterType;
    }

    @Override
    public Set<Type> getImportTypes() {
        Set<Type> importTypes = new HashSet<>();
        importTypes.add(getType());
        importTypes.add(mapObjectConverterType);
        return importTypes;
    }
}
