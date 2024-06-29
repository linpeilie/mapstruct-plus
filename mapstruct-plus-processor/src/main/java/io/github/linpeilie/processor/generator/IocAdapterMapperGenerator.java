package io.github.linpeilie.processor.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.AbstractAdapterMapperGenerator;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import io.github.linpeilie.utils.CollectionUtils;
import java.util.List;
import javax.lang.model.element.Modifier;

public abstract class IocAdapterMapperGenerator extends AbstractAdapterMapperGenerator {

    protected static final String CONVERTER_FIELD_NAME = "converter";

    protected abstract AnnotationSpec componentAnnotation();

    protected abstract List<AnnotationSpec> injectAnnotations();

    @Override
    protected TypeSpec createTypeSpec(List<MethodSpec> methods, String adapterClassName, ClassName superClass) {
        TypeSpec.Builder adapterBuilder = TypeSpec.classBuilder(ClassName.get(adapterPackage(), adapterClassName))
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(componentAnnotation());

        adapterBuilder.addField(buildConverterField());

        if (CollectionUtils.isNotEmpty(methods)) {
            adapterBuilder.addMethods(methods);
        }

        if (superClass != null) {
            adapterBuilder.superclass(superClass);
        }

        return adapterBuilder.build();
    }

    private FieldSpec buildConverterField() {
        return FieldSpec.builder(
                ClassName.get("io.github.linpeilie", "Converter"),
                CONVERTER_FIELD_NAME,
                Modifier.PRIVATE
            )
            .addAnnotations(injectAnnotations())
            .build();
    }

    private String firstWordToLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * <code>
     * return converter.convert(param, Target.class);
     * </code>
     */
    @Override
    protected CodeBlock proxyMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        return CodeBlock.builder()
            .add("return $N.convert($N, $T.class);\n", CONVERTER_FIELD_NAME,
                PARAM__PARAMETER_NAME,
                adapterMethodMetadata.getReturn())
            .build();
    }

    /**
     * <code>
     * return converter.convert(param, Target.class, context);
     * </code>
     */
    @Override
    protected CodeBlock cycleAvoidingMethodTarget(AbstractAdapterMethodMetadata adapterMethodMetadata) {
        return CodeBlock.builder()
            .add("return $N.convert($N, $T.class, $N);\n",
                CONVERTER_FIELD_NAME,
                PARAM__PARAMETER_NAME,
                adapterMethodMetadata.getReturn(),
                CONTEXT__PARAMETER_NAME)
            .build();
    }
}
