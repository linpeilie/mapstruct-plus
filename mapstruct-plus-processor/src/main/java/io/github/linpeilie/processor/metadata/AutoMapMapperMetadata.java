package io.github.linpeilie.processor.metadata;

import io.github.linpeilie.processor.AutoMapperProperties;
import io.github.linpeilie.utils.StrUtil;

public class AutoMapMapperMetadata extends AutoMapperMetadata {

    @Override
    public String mapperPackage() {
        return StrUtil.isNotEmpty(
            AutoMapperProperties.getMapperPackage()) ? AutoMapperProperties.getMapperPackage() : getTargetClassName().packageName();
    }
}
