package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;

public class AdapterMethodMetadata extends AbstractAdapterMethodMetadata {

    private AdapterMethodMetadata(final ClassName source, final ClassName target, ClassName mapper) {
        super(source, mapper);
        this.target = target;
    }

    private final ClassName target;

    public static AdapterMethodMetadata newInstance(ClassName source, ClassName target, ClassName mapper) {
        return new AdapterMethodMetadata(source, target, mapper);
    }

    @Override
    public String getMethodName() {
        return source.simpleName().substring(0, 1).toLowerCase() + source.simpleName().substring(1) + "To" +
               target.simpleName();
    }

    public ClassName getTarget() {
        return target;
    }

    @Override
    public ClassName getReturn() {
        return target;
    }

    @Override
    public String getMapperMethodName() {
        return "convert";
    }
}
