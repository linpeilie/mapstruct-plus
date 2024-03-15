package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;

public class AutoMappingMetadata {

    private ClassName targetClass;

    private String target = "";

    private String source = "";

    private String dateFormat = "";

    private String numberFormat = "";

    private String expression = "";

    private String defaultExpression = "";

    private String conditionExpression = "";

    private boolean ignore = false;

    private String defaultValue = "";

    String[] qualifiedByName = {};

    String[] conditionQualifiedByName = {};

    String[] dependsOn = {};

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

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(final boolean ignore) {
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

    public String[] getQualifiedByName() {
        return qualifiedByName;
    }

    public void setQualifiedByName(String[] qualifiedByName) {
        this.qualifiedByName = qualifiedByName;
    }

    public String[] getConditionQualifiedByName() {
        return conditionQualifiedByName;
    }

    public void setConditionQualifiedByName(String[] conditionQualifiedByName) {
        this.conditionQualifiedByName = conditionQualifiedByName;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }
}
