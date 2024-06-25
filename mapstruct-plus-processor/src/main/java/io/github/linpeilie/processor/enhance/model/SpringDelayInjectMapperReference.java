package io.github.linpeilie.processor.enhance.model;

import io.github.linpeilie.processor.enhance.processor.SpringComponentProcessor;
import java.util.HashSet;
import java.util.Set;
import org.mapstruct.ap.internal.model.MapperReference;
import org.mapstruct.ap.internal.model.common.Type;

public class SpringDelayInjectMapperReference extends MapperReference {

    private final Type springContextUtil;

    public SpringDelayInjectMapperReference(Type type, String variableName, boolean isUsed, Type springContextUtil) {
        super(type, variableName, isUsed);
        this.springContextUtil = springContextUtil;
    }

    @Override
    public Set<Type> getImportTypes() {
        Set<Type> importTypes = new HashSet<>();
        importTypes.add(getType());
        importTypes.add(springContextUtil);
        return importTypes;
    }
}
