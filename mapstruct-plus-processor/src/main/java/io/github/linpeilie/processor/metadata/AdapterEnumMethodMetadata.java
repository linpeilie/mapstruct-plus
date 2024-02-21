package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public class AdapterEnumMethodMetadata extends AbstractAdapterMethodMetadata {

    private final String proxyTargetMethodName;

    private final TypeName returnType;

    public AdapterEnumMethodMetadata(final TypeName source,
        final ClassName mapper,
        final String proxyTargetMethodName,
        final TypeName returnType,
        boolean cycleAvoiding) {
        super(source, mapper);
        this.proxyTargetMethodName = proxyTargetMethodName;
        this.returnType = returnType;
        this.cycleAvoiding = cycleAvoiding;
    }

    @Override
    public String getMethodName() {
        return "proxy" + mapper.simpleName() + proxyTargetMethodName;
    }

    @Override
    public TypeName getReturn() {
        return returnType;
    }

    @Override
    public String getMapperMethodName() {
        return proxyTargetMethodName;
    }

    @Override
    public boolean isStatic() {
        return true;
    }
}
