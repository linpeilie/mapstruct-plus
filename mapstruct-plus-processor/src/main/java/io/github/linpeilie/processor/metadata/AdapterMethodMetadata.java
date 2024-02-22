package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;

public class AdapterMethodMetadata extends AbstractAdapterMethodMetadata {

    private AdapterMethodMetadata(final ClassName source, final ClassName target, ClassName mapper, boolean needCycleAvoiding) {
        super(source, mapper);
        this.target = target;
        this.needCycleAvoiding = needCycleAvoiding;
    }

    private final ClassName target;
    private final boolean needCycleAvoiding;

    public static AdapterMethodMetadata newInstance(ClassName source, ClassName target, ClassName mapper, boolean needCycleAvoiding) {
        return new AdapterMethodMetadata(source, target, mapper, needCycleAvoiding);
    }

    @Override
    public String getMethodName() {
        final String sourceName = source.toString().replace(".", "_");
        return sourceName.substring(0, 1).toLowerCase() + sourceName.substring(1) + "To" +
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

    @Override
    public boolean needCycleAvoiding() {
        return needCycleAvoiding;
    }
}
