package io.github.linpeilie.processor.metadata;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public class AutoEnumMapperMetadata extends AbstractMapperMetadata {

    private TypeName returnType;

    private String getter;

    public TypeName getReturnType() {
        return returnType;
    }

    public void setReturnType(final TypeName returnType) {
        this.returnType = returnType;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(final String getter) {
        this.getter = getter;
    }

    @Override
    public String mapperName() {
        return sourceClassName.simpleName() + "Mapper";
    }

    public String toEnumMethodName() {
        return "_toEnum";
    }

    public String toValueMethodName() {
        return "_toValue";
    }

}
