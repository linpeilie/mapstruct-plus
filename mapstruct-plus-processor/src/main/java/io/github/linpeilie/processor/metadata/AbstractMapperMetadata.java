package io.github.linpeilie.processor.metadata;

import cn.easii.tutelary.deps.com.squareup.javapoet.ClassName;
import io.github.linpeilie.processor.AutoMapperProperties;
import io.github.linpeilie.processor.utils.MapperUtils;
import io.github.linpeilie.utils.StrUtil;

public abstract class AbstractMapperMetadata {

    protected ClassName sourceClassName;

    public String mapperPackage() {
        return MapperUtils.getMapperPackage(sourceClassName.packageName());
    }

    public abstract String mapperName();

    public ClassName mapperClass() {
        return ClassName.get(mapperPackage(), mapperName());
    }

    public ClassName getSourceClassName() {
        return sourceClassName;
    }

    public void setSourceClassName(final ClassName sourceClassName) {
        this.sourceClassName = sourceClassName;
    }

}
