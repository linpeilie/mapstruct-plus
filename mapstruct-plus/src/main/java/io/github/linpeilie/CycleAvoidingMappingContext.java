package io.github.linpeilie;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

public class CycleAvoidingMappingContext {

    private Map<Object, Map<Class<?>, Object>> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        Map<Class<?>, Object> map = knownInstances.get(source);
        if (map == null || map.isEmpty()) {
            return null;
        }
        return (T) map.get(targetType);
    }

    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        if (target == null) {
            return;
        }
        Map<Class<?>, Object> map = knownInstances.get(source);
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(target.getClass(), target);
        knownInstances.put( source, map );
    }

}
