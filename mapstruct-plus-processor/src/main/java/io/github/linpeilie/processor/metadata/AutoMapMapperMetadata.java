package io.github.linpeilie.processor.metadata;

import io.github.linpeilie.processor.AutoMapperProperties;
import org.apache.commons.lang3.StringUtils;

public class AutoMapMapperMetadata extends AutoMapperMetadata {

    @Override
    public String mapperPackage() {
        return StringUtils.isNotEmpty(AutoMapperProperties.getMapperPackage())
               ? AutoMapperProperties.getMapperPackage() : getTargetClassName().packageName();
    }
}
