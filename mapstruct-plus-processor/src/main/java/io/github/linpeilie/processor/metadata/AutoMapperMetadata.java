package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import io.github.linpeilie.processor.utils.MapperUtils;
import io.github.linpeilie.utils.StrUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.SubclassExhaustiveStrategy;

public class AutoMapperMetadata extends AbstractMapperMetadata {

    public AutoMapperMetadata(ClassName sourceClassName, ClassName targetClassName) {
        this.sourceClassName = sourceClassName;
        this.targetClassName = targetClassName;
        this.mapperName = MapperUtils.getMapperClassName(sourceClassName.reflectionName(), targetClassName.reflectionName());
    }

    private String mapperName;

    private String mapperNameSuffix;

    private ClassName targetClassName;

    private List<ClassName> usesClassNameList;

    private List<ClassName> useEnumClassNameList;

    private List<ClassName> importsClassNameList;

    private List<AutoMappingMetadata> fieldMappingList;

    private List<AutoMappingMetadata> fieldReverseMappingList;

    private ClassName superClass;

    private ClassName[] superGenerics;

    private ClassName mapstructConfigClass;

    private boolean convertGenerate;

    private boolean reverseConvertGenerate;

    private boolean cycleAvoiding;

    private String unmappedSourcePolicy;

    private String unmappedTargetPolicy;

    private String typeConversionPolicy;

    private String collectionMappingStrategy;

    private String nullValueMappingStrategy;

    private String nullValueIterableMappingStrategy;

    private NullValueMappingStrategy nullValueMapMappingStrategy;

    private String nullValuePropertyMappingStrategy;

    private String nullValueCheckStrategy;

    private SubclassExhaustiveStrategy subclassExhaustiveStrategy;

    private ClassName mappingControl;

    private Set<TypeName> dependencies;

    public String qualifiedMapperName() {
        return mapperPackage() + "." + mapperName();
    }

    public boolean addUseList(List<ClassName> uses) {
        if (this.usesClassNameList == null) {
            this.usesClassNameList = new ArrayList<>();
        }
        return usesClassNameList.addAll(uses);
    }

    /*************** getter/setter ***************/

    public String mapperName() {
        return StrUtil.isNotEmpty(this.mapperNameSuffix) ? this.mapperName + this.mapperNameSuffix : this.mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getMapperNameSuffix() {
        return mapperNameSuffix;
    }

    public void setMapperNameSuffix(String mapperNameSuffix) {
        this.mapperNameSuffix = mapperNameSuffix;
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

    public List<ClassName> getUseEnumClassNameList() {
        return useEnumClassNameList;
    }

    public void setUseEnumClassNameList(List<ClassName> useEnumClassNameList) {
        this.useEnumClassNameList = useEnumClassNameList;
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

    public boolean isCycleAvoiding() {
        return cycleAvoiding;
    }

    public void setCycleAvoiding(boolean cycleAvoiding) {
        this.cycleAvoiding = cycleAvoiding;
    }

    public String getUnmappedSourcePolicy() {
        return unmappedSourcePolicy;
    }

    public void setUnmappedSourcePolicy(String unmappedSourcePolicy) {
        this.unmappedSourcePolicy = unmappedSourcePolicy;
    }

    public String getUnmappedTargetPolicy() {
        return unmappedTargetPolicy;
    }

    public void setUnmappedTargetPolicy(String unmappedTargetPolicy) {
        this.unmappedTargetPolicy = unmappedTargetPolicy;
    }

    public String getTypeConversionPolicy() {
        return typeConversionPolicy;
    }

    public void setTypeConversionPolicy(String typeConversionPolicy) {
        this.typeConversionPolicy = typeConversionPolicy;
    }

    public String getCollectionMappingStrategy() {
        return collectionMappingStrategy;
    }

    public void setCollectionMappingStrategy(String collectionMappingStrategy) {
        this.collectionMappingStrategy = collectionMappingStrategy;
    }

    public String getNullValueMappingStrategy() {
        return nullValueMappingStrategy;
    }

    public void setNullValueMappingStrategy(String nullValueMappingStrategy) {
        this.nullValueMappingStrategy = nullValueMappingStrategy;
    }

    public String getNullValueIterableMappingStrategy() {
        return nullValueIterableMappingStrategy;
    }

    public void setNullValueIterableMappingStrategy(String nullValueIterableMappingStrategy) {
        this.nullValueIterableMappingStrategy = nullValueIterableMappingStrategy;
    }

    public NullValueMappingStrategy getNullValueMapMappingStrategy() {
        return nullValueMapMappingStrategy;
    }

    public void setNullValueMapMappingStrategy(NullValueMappingStrategy nullValueMapMappingStrategy) {
        this.nullValueMapMappingStrategy = nullValueMapMappingStrategy;
    }

    public String getNullValuePropertyMappingStrategy() {
        return nullValuePropertyMappingStrategy;
    }

    public void setNullValuePropertyMappingStrategy(String nullValuePropertyMappingStrategy) {
        this.nullValuePropertyMappingStrategy = nullValuePropertyMappingStrategy;
    }

    public String getNullValueCheckStrategy() {
        return nullValueCheckStrategy;
    }

    public void setNullValueCheckStrategy(String nullValueCheckStrategy) {
        this.nullValueCheckStrategy = nullValueCheckStrategy;
    }

    public SubclassExhaustiveStrategy getSubclassExhaustiveStrategy() {
        return subclassExhaustiveStrategy;
    }

    public void setSubclassExhaustiveStrategy(SubclassExhaustiveStrategy subclassExhaustiveStrategy) {
        this.subclassExhaustiveStrategy = subclassExhaustiveStrategy;
    }

    public ClassName getMappingControl() {
        return mappingControl;
    }

    public void setMappingControl(ClassName mappingControl) {
        this.mappingControl = mappingControl;
    }

    public Set<TypeName> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<TypeName> dependencies) {
        this.dependencies = dependencies;
    }
}
