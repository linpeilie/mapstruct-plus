package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import java.util.List;

public class AutoMapperMetadata extends AbstractMapperMetadata {



    private ClassName targetClassName;

    private List<ClassName> usesClassNameList;

    private List<AutoMappingMetadata> fieldMappingList;

    private ClassName superClass;

    private ClassName[] superGenerics;

    private ClassName mapstructConfigClass;

    public String mapperName() {
        return sourceClassName.simpleName() + "To" + targetClassName.simpleName() + "Mapper";
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

    public ClassName getSuperClass() {
        return superClass;
    }

    public ClassName[] getSuperGenerics() {
        return superGenerics;
    }

    public void setSuperGenerics(final ClassName[] superGenerics) {
        this.superGenerics = superGenerics;
    }

    public void setSuperClass(final ClassName superClass) {
        this.superClass = superClass;
    }

    public ClassName getMapstructConfigClass() {
        return mapstructConfigClass;
    }

    public void setMapstructConfigClass(final ClassName mapstructConfigClass) {
        this.mapstructConfigClass = mapstructConfigClass;
    }
}
