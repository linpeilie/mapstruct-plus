package io.github.linpeilie.processor.metadata;

import cn.easii.tutelary.deps.com.squareup.javapoet.ClassName;
import java.lang.annotation.Target;

public class AdapterMapMethodMetadata extends AbstractAdapterMethodMetadata {

    private final ClassName target;

    private final String methodName;

    private final String mapperMethodName;

    public AdapterMapMethodMetadata(final ClassName source,
                                    final ClassName target,
                                    final ClassName mapper,
                                    boolean objectConverter) {
        super(source, mapper);
        this.target = target;
        if (objectConverter) {
            methodName = "objectTo" + target.simpleName();
            mapperMethodName = "convertByObj";
        } else {
            methodName = "mapTo" + target.simpleName();
            mapperMethodName = "convert";
        }
    }

    public static AdapterMapMethodMetadata newInstance(final ClassName source,
                                                       final ClassName target,
                                                       final ClassName mapper,
                                                       boolean objectConverter) {
        return new AdapterMapMethodMetadata(source, target, mapper, objectConverter);
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public ClassName getReturn() {
        return target;
    }

    @Override
    public String getMapperMethodName() {
        return mapperMethodName;
    }
}
