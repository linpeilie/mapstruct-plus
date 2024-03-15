package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import io.github.linpeilie.processor.AutoMapperProperties;
import io.github.linpeilie.utils.StrUtil;

public abstract class AbstractMapperMetadata {

    protected ClassName sourceClassName;

    public String mapperPackage() {
        return StrUtil.isNotEmpty(AutoMapperProperties.getMapperPackage())
               ? AutoMapperProperties.getMapperPackage() : sourceClassName.packageName();
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
