package io.github.linpeilie.processor.metadata;

import cn.easii.tutelary.deps.com.squareup.javapoet.ClassName;
import cn.easii.tutelary.deps.com.squareup.javapoet.TypeName;

public class AdapterEnumMethodMetadata extends AbstractAdapterMethodMetadata {

    private final String proxyTargetMethodName;

    private final TypeName returnType;

    public AdapterEnumMethodMetadata(final TypeName source,
        final ClassName mapper,
        final String proxyTargetMethodName,
        final TypeName returnType) {
        super(source, mapper);
        this.proxyTargetMethodName = proxyTargetMethodName;
        this.returnType = returnType;
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
