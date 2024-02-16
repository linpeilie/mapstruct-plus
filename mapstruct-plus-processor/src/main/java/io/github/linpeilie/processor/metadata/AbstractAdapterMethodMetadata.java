package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public abstract class AbstractAdapterMethodMetadata {

    public AbstractAdapterMethodMetadata(final TypeName source, ClassName mapper) {
        this.source = source;
        this.mapper = mapper;
        this.cycleAvoiding = false;
    }

    protected final TypeName source;

    protected final ClassName mapper;

    protected boolean cycleAvoiding;

    public abstract String getMethodName();

    public abstract TypeName getReturn();

    public abstract String getMapperMethodName();

    public TypeName getSource() {
        return source;
    }

    public ClassName getMapper() {
        return mapper;
    }

    public boolean isCycleAvoiding() {
        return cycleAvoiding;
    }

    public boolean isStatic() {
        return false;
    }

}
