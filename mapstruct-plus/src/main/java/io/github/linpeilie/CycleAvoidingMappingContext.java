package io.github.linpeilie;

import cn.hutool.core.map.multi.RowKeyTable;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

public class CycleAvoidingMappingContext {

    private RowKeyTable<Object, Class, Object> knownInstances = new RowKeyTable<>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        return (T) knownInstances.get(source, targetType);
    }

    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put(source, target.getClass(), target);
    }
}