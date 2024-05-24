package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import io.github.linpeilie.processor.ContextConstants;
import java.util.ArrayList;
import java.util.List;

public class AutoMappingMetadata {

    private ClassName targetClass;

    private String target;

    private String source;

    private String dateFormat;

    private String numberFormat;

    private String expression;

    private String defaultExpression;

    private String conditionExpression;

    private Boolean ignore;

    private String defaultValue;

    private List<String> qualifiedByName;

    private List<String> conditionQualifiedByName;

    private List<String> dependsOn;

    private String constant;

    private List<ClassName> qualifiedBy;

    private String nullValueCheckStrategy;

    private String nullValuePropertyMappingStrategy;

    private ClassName mappingControl;
    /*********** getter/setter ************/

    public ClassName getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(final ClassName targetClass) {
        this.targetClass = targetClass;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(final String target) {
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(final String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(final String numberFormat) {
        this.numberFormat = numberFormat;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(final String expression) {
        this.expression = expression;
    }

    public void setIgnore(final Boolean ignore) {
        this.ignore = ignore;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultExpression() {
        return defaultExpression;
    }

    public void setDefaultExpression(final String defaultExpression) {
        this.defaultExpression = defaultExpression;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public void setConditionExpression(final String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public List<ClassName> getQualifiedBy() {
        return qualifiedBy;
    }

    public void setQualifiedBy(List<ClassName> qualifiedBy) {
        this.qualifiedBy = qualifiedBy;
    }

    public Boolean getIgnore() {
        return ignore;
    }

    public List<String> getQualifiedByName() {
        return qualifiedByName;
    }

    public void setQualifiedByName(List<String> qualifiedByName) {
        this.qualifiedByName = qualifiedByName;
    }

    public List<String> getConditionQualifiedByName() {
        return conditionQualifiedByName;
    }

    public void setConditionQualifiedByName(List<String> conditionQualifiedByName) {
        this.conditionQualifiedByName = conditionQualifiedByName;
    }

    public List<String> getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(List<String> dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String getNullValueCheckStrategy() {
        return nullValueCheckStrategy;
    }

    public void setNullValueCheckStrategy(String nullValueCheckStrategy) {
        this.nullValueCheckStrategy = nullValueCheckStrategy;
    }

    public String getNullValuePropertyMappingStrategy() {
        return nullValuePropertyMappingStrategy;
    }

    public void setNullValuePropertyMappingStrategy(String nullValuePropertyMappingStrategy) {
        this.nullValuePropertyMappingStrategy = nullValuePropertyMappingStrategy;
    }

    public ClassName getMappingControl() {
        return mappingControl;
    }

    public void setMappingControl(ClassName mappingControl) {
        this.mappingControl = mappingControl;
    }
}
