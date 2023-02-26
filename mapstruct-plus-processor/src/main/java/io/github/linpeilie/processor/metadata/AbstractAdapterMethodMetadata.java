package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;

public abstract class AbstractAdapterMethodMetadata {

    public AbstractAdapterMethodMetadata(final ClassName source, ClassName mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    protected final ClassName source;

    protected final ClassName mapper;

    public abstract String getMethodName();

    public abstract ClassName getReturn();

    public abstract String getMapperMethodName();

    public ClassName getSource() {
        return source;
    }

    public ClassName getMapper() {
        return mapper;
    }

}
