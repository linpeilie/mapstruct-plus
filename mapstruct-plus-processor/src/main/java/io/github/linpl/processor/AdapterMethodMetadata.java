package io.github.linpl.processor;

import com.squareup.javapoet.ClassName;

public class AdapterMethodMetadata {

    private AdapterMethodMetadata(final ClassName source, final ClassName target, ClassName mapper) {
        this.source = source;
        this.target = target;
        this.mapper = mapper;
    }

    private final ClassName source;

    private final ClassName target;

    private final ClassName mapper;

    public static AdapterMethodMetadata newInstance(ClassName source, ClassName target, ClassName mapper) {
        return new AdapterMethodMetadata(source, target, mapper);
    }

    public String getMethodName() {
        return source.simpleName().substring(0, 1).toLowerCase() + source.simpleName().substring(1) + "To" +
               target.simpleName();
    }

    public ClassName getSource() {
        return source;
    }

    public ClassName getTarget() {
        return target;
    }

    public ClassName getMapper() {
        return mapper;
    }
}
