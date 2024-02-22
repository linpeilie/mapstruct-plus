package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import io.github.linpeilie.processor.AutoMapperProperties;
import java.util.List;
import org.mapstruct.ReportingPolicy;

public class AutoMapperMetadata extends AbstractMapperMetadata {

    private ClassName targetClassName;

    private List<ClassName> usesClassNameList;

    private List<ClassName> importsClassNameList;

    private List<AutoMappingMetadata> fieldMappingList;

    private List<AutoMappingMetadata> fieldReverseMappingList;

    private ClassName superClass;

    private ClassName[] superGenerics;

    private ClassName mapstructConfigClass;

    private boolean convertGenerate;

    private boolean reverseConvertGenerate;

    private boolean cycles;

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

    public List<ClassName> getImportsClassNameList() {
        return importsClassNameList;
    }

    public void setImportsClassNameList(final List<ClassName> importsClassNameList) {
        this.importsClassNameList = importsClassNameList;
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

    public boolean isReverseConvertGenerate() {
        return reverseConvertGenerate;
    }

    public void setReverseConvertGenerate(final boolean reverseConvertGenerate) {
        this.reverseConvertGenerate = reverseConvertGenerate;
    }

    public List<AutoMappingMetadata> getFieldReverseMappingList() {
        return fieldReverseMappingList;
    }

    public void setFieldReverseMappingList(final List<AutoMappingMetadata> fieldReverseMappingList) {
        this.fieldReverseMappingList = fieldReverseMappingList;
    }

    public boolean isConvertGenerate() {
        return convertGenerate;
    }

    public void setConvertGenerate(final boolean convertGenerate) {
        this.convertGenerate = convertGenerate;
    }

    public boolean isCycles() {
        return cycles;
    }

    public void setCycles(boolean cycles) {
        this.cycles = cycles;
    }
}
