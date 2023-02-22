package io.github.linpeilie.processor;

import com.squareup.javapoet.ClassName;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class AutoMapperMetadata {

    private ClassName sourceClassName;

    private ClassName targetClassName;

    private List<ClassName> usesClassNameList;

    private List<AutoMappingMetadata> fieldMappingList;

    public String mapperPackage() {
        return StringUtils.isNotEmpty(AutoMapperProperties.getMapperPackage())
               ? AutoMapperProperties.getMapperPackage() : sourceClassName.packageName();
    }

    public String mapperName() {
        return sourceClassName.simpleName() + "To" + targetClassName.simpleName() + "Mapper";
    }

    public ClassName getSourceClassName() {
        return sourceClassName;
    }

    public AutoMapperMetadata setSourceClassName(final ClassName sourceClassName) {
        this.sourceClassName = sourceClassName;
        return this;
    }

    public ClassName getTargetClassName() {
        return targetClassName;
    }

    public AutoMapperMetadata setTargetClassName(final ClassName targetClassName) {
        this.targetClassName = targetClassName;
        return this;
    }

    public List<ClassName> getUsesClassNameList() {
        return usesClassNameList;
    }

    public AutoMapperMetadata setUsesClassNameList(final List<ClassName> usesClassNameList) {
        this.usesClassNameList = usesClassNameList;
        return this;
    }

    public List<AutoMappingMetadata> getFieldMappingList() {
        return fieldMappingList;
    }

    public AutoMapperMetadata setFieldMappingList(final List<AutoMappingMetadata> fieldMappingList) {
        this.fieldMappingList = fieldMappingList;
        return this;
    }
}
