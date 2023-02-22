package io.github.linpl.annotations;

import io.github.linpl.BaseMapper;
import io.github.linpl.ConverterFactory;
import org.apache.commons.lang3.ClassUtils;
import org.mapstruct.factory.Mappers;
import sun.misc.ClassLoaderUtil;

public class DefaultConverterFactory implements ConverterFactory {

    private final String basePackage;


    public DefaultConverterFactory() {
        this("");
    }

    public DefaultConverterFactory(final String basePackage) {
        this.basePackage = basePackage;
        // load mapper package
        if (basePackage == null || basePackage.isEmpty()) {
            loadBasePackage();
        }
    }

    private void loadBasePackage() {

    }

    @Override
    public <S, T> BaseMapper<S, T> getMapper(final Class<S> sourceType, final Class<T> targetType) {

    }

}
