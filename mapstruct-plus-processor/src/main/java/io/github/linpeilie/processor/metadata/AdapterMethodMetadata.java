package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import io.github.linpeilie.utils.ClassUtil;

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
        String source = ClassUtil.simplifyQualifiedName(this.source.toString());
        source = source.substring(0, 1).toLowerCase() + source.substring(1);
        return source + "To" + ClassUtil.simplifyQualifiedName(this.target.toString());
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
