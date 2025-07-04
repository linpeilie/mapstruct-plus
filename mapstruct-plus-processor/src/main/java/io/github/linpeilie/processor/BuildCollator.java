package io.github.linpeilie.processor;

import cn.easii.tutelary.deps.com.squareup.javapoet.ClassName;
import cn.easii.tutelary.deps.com.squareup.javapoet.TypeName;
import io.github.linpeilie.processor.utils.FileUtils;
import io.github.linpeilie.utils.CollectionUtils;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * 构建校对器
 *
 * 兼容 IDEA 部分构建的问题
 */
public class BuildCollator {

    private final ProcessingEnvironment processingEnv;

    private final File collatorFile;

    private List<TypeElement> records;

    public BuildCollator(ProcessingEnvironment processingEnv, String fileName) {
        this.processingEnv = processingEnv;
        Filer filer = processingEnv.getFiler();
        try {
            FileObject fileObject = filer.getResource(StandardLocation.CLASS_OUTPUT, "",
                 ContextConstants.MetaInf.folder + fileName);
            this.collatorFile = new File(fileObject.getName());
            if (collatorFile.exists()) {
                records = FileUtils.readUtf8Lines(collatorFile)
                    .stream()
                    .map(line -> processingEnv.getElementUtils().getTypeElement(line))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            } else {
                records = new ArrayList<>();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void writeTypeElements(Collection<TypeElement> typeElements) {
        final Set<String> names = typeElements.stream()
            .map(typeElement -> typeElement.getQualifiedName().toString())
            .collect(Collectors.toSet());
        write(names);
    }

    private void write(Collection<String> lines) {
        if (CollectionUtils.isEmpty(lines)) {
            return;
        }

        records = lines.stream()
            .map(line -> processingEnv.getElementUtils().getTypeElement(line))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        FileUtils.mkParentDirs(collatorFile);
        FileUtils.writeUtf8Lines(lines, collatorFile);
    }

    private List<String> loadRecords() {
        return records.stream()
            .map(ele -> ele.getQualifiedName().toString())
            .collect(Collectors.toList());
    }

    public List<TypeElement> getRecords() {
        return records;
    }

    public void consumeRecords(Consumer<TypeElement> consumer) {
        final List<TypeElement> typeElements = getRecords();
        if (CollectionUtils.isNotEmpty(typeElements)) {
            typeElements.forEach(consumer);
        }
    }

    public void appendNonexistent(Collection<? extends Element> newRecords) {
        final List<String> lines = loadRecords();
        newRecords.forEach(ele -> {
            final String classQualifiedName = ((TypeElement) ele).getQualifiedName().toString();
            lines.add(classQualifiedName);
        });
        write(new HashSet<>(lines));
    }

    public void appendNonexistentTypes(Collection<ClassName> typeNames) {
        List<String> lines = loadRecords();
        typeNames.forEach(ele -> {
            String classQualifiedName = ele.reflectionName();
            lines.add(classQualifiedName);
        });
        write(new HashSet<>(lines));
    }

}
