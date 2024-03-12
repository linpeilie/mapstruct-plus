package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.AbstractAdapterMapperGenerator;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import java.util.Collection;
import javax.lang.model.element.Modifier;

public class DefaultAdapterMapperGenerator extends AbstractAdapterMapperGenerator {

    @Override
    protected CodeBlock proxyMethodTarget(final AbstractAdapterMethodMetadata adapterMethodMetadata) {
        return CodeBlock.of("return ($T.getMapper($T.class)).$N($N);",
            ClassName.get("org.mapstruct.factory", "Mappers"), adapterMethodMetadata.getMapper(),
            adapterMethodMetadata.getMapperMethodName(), PARAM__PARAMETER_NAME);
    }

    @Override
    protected CodeBlock cycleAvoidingMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        return CodeBlock.of("return ($T.getMapper($T.class)).$N($N, $N);",
            ClassName.get("org.mapstruct.factory", "Mappers"), adapterMethodMetadata.getMapper(),
            adapterMethodMetadata.cycleAvoidingMethodName(), PARAM__PARAMETER_NAME, CONTEXT__PARAMETER_NAME);
    }
}
