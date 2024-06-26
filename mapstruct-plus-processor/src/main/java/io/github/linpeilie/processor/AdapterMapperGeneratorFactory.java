package io.github.linpeilie.processor;

import io.github.linpeilie.ComponentModelConstant;
import io.github.linpeilie.processor.generator.DefaultAdapterMapperGenerator;
import io.github.linpeilie.processor.generator.SolonAdapterMapperGenerator;
import io.github.linpeilie.processor.generator.SpringAdapterMapperGenerator;
import org.mapstruct.MappingConstants;

public class AdapterMapperGeneratorFactory {

    public static AbstractAdapterMapperGenerator instance(String componentModel) {
        switch (AutoMapperProperties.getComponentModel()) {
            case MappingConstants.ComponentModel.SPRING:
            case ContextConstants.ComponentModelConfig.springLazy:
                return new SpringAdapterMapperGenerator();
            case ComponentModelConstant.SOLON:
                return new SolonAdapterMapperGenerator();
            default:
                return new DefaultAdapterMapperGenerator();
        }
    }

}
