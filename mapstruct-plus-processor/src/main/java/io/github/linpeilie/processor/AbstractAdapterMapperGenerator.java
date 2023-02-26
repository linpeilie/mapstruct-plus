package io.github.linpeilie.processor;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import javax.annotation.processing.ProcessingEnvironment;

import static javax.tools.Diagnostic.Kind.ERROR;

public abstract class AbstractAdapterMapperGenerator {

    public void write(ProcessingEnvironment processingEnv, Collection<AbstractAdapterMethodMetadata> adapterMethods, String adapterClassName) {
        // write Adapter
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(adapterPackage() + "." + adapterClassName)
            .openWriter()) {
            JavaFile.builder(adapterPackage(), createTypeSpec(adapterMethods, adapterClassName)).build().writeTo(writer);
        } catch (IOException e) {
            processingEnv.getMessager()
                .printMessage(ERROR, "Error while opening " + adapterClassName + " output file: " + e.getMessage());
        }
    }

    protected abstract TypeSpec createTypeSpec(Collection<AbstractAdapterMethodMetadata> adapterMethods, String adapterClassName);

    protected String adapterPackage() {
        return AutoMapperProperties.getAdapterPackage();
    }

}
