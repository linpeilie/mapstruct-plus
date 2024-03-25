package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public abstract class AbstractAdapterMethodMetadata {

    public AbstractAdapterMethodMetadata(final TypeName source, ClassName mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    protected final TypeName source;

    protected final ClassName mapper;

    public abstract String getMethodName();

    public abstract TypeName getReturn();

    public abstract String getMapperMethodName();

    public TypeName getSource() {
        return source;
    }

    public ClassName getMapper() {
        return mapper;
    }

    public boolean isStatic() {
        return false;
    }

    public boolean needCycleAvoiding() {
        return false;
    }

    public String cycleAvoidingMethodName() {
        return "convertWithCycle";
    }

}
