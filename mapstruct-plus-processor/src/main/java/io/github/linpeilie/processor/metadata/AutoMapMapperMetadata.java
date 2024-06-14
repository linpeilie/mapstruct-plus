package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import io.github.linpeilie.processor.AutoMapperProperties;
import io.github.linpeilie.utils.StrUtil;

public class AutoMapMapperMetadata extends AutoMapperMetadata {

    public AutoMapMapperMetadata(ClassName sourceClassName,
        ClassName targetClassName) {
        super(sourceClassName, targetClassName);
    }

    @Override
    public String mapperPackage() {
        return StrUtil.isNotEmpty(
            AutoMapperProperties.getMapperPackage()) ? AutoMapperProperties.getMapperPackage() : getTargetClassName().packageName();
    }
}
