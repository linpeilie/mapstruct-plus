package io.github.linpl.processor;

import com.squareup.javapoet.ClassName;
import io.github.linpl.annotations.AutoMapper;
import io.github.linpl.annotations.AutoMapping;
import io.github.linpl.annotations.MapperConfig;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.apache.commons.lang3.StringUtils;

import static io.github.linpl.processor.Constants.AUTO_MAPPER_ANNOTATION;
import static io.github.linpl.processor.Constants.MAPPER_CONFIG_ANNOTATION;
import static javax.tools.Diagnostic.Kind.ERROR;

@SupportedAnnotationTypes({AUTO_MAPPER_ANNOTATION, MAPPER_CONFIG_ANNOTATION})
public class AutoMapperProcessor extends AbstractProcessor {

    private final AutoMapperGenerator mapperGenerator;

    private final AdapterMapperGenerator adapterMapperGenerator;

    private final MapperConfigGenerator mapperConfigGenerator;

    private final Map<String, AdapterMethodMetadata> methodMap = new HashMap<>();

    private final Set<String> mapperSet = new HashSet<>();

    public AutoMapperProcessor() {
        this.mapperGenerator = new AutoMapperGenerator();
        this.adapterMapperGenerator = new AdapterMapperGenerator();
        this.mapperConfigGenerator = new MapperConfigGenerator();
    }

    private boolean isAutoMapperAnnotation(TypeElement annotation) {
        return AUTO_MAPPER_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isMapperConfigAnnotation(TypeElement annotation) {
        return MAPPER_CONFIG_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        boolean hasAutoMapper = annotations.stream().anyMatch(this::isAutoMapperAnnotation);
        if (!hasAutoMapper) {
            return false;
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "start refresh properties");
        refreshProperties(annotations, roundEnv);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "start write config class");
        writeConfigClass();
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "start generate mapper class");
        annotations.stream()
            .filter(this::isAutoMapperAnnotation)
            .forEach(annotation -> processAutoMapperAnnotation(roundEnv, annotation));
        return false;
    }

    private void refreshProperties(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        annotations.stream()
            .filter(this::isMapperConfigAnnotation)
            .findFirst()
            .flatMap(annotation -> roundEnv.getElementsAnnotatedWith(annotation).stream().findFirst())
            .ifPresent(element -> {
                final MapperConfig mapperConfig = element.getAnnotation(MapperConfig.class);
                String mapperPackage = StringUtils.isEmpty(mapperConfig.mapperPackage()) ? getPackageName(element)
                                                                                         : mapperConfig.mapperPackage();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "mapper package " + mapperPackage);
                AutoMapperProperties.setMapperPackage(mapperPackage);
            });
    }

    private String getPackageName(Element element) {
        return String.valueOf(processingEnv.getElementUtils().getPackageOf(element).getQualifiedName());
    }

    private void writeConfigClass() {
        mapperConfigGenerator.write(processingEnv);
    }

    private void processAutoMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        final List<AutoMapperMetadata> autoMapperMetadataList = roundEnv.getElementsAnnotatedWith(annotation)
            .stream()
            .map(this::buildAutoMapperMetadata)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        autoMapperMetadataList.forEach(autoMapperMetadata -> mapperSet.add(autoMapperMetadata.mapperName()));

        List<AutoMapperMetadata> reverseMapperMetadataList = new ArrayList<>();

        autoMapperMetadataList.forEach(autoMapperMetadata -> {
            final AutoMapperMetadata reverseMapperMetadata = reverseMapper(autoMapperMetadata);
            if (!mapperSet.add(reverseMapperMetadata.mapperName())) {
                return;
            }
            reverseMapperMetadataList.add(reverseMapperMetadata);
        });

        autoMapperMetadataList.addAll(reverseMapperMetadataList);

        autoMapperMetadataList.forEach(this::writeAutoMapperClassFile);

        adapterMapperGenerator.write(processingEnv, methodMap.values());
    }

    private AutoMapperMetadata reverseMapper(AutoMapperMetadata autoMapperMetadata) {
        AutoMapperMetadata reverseMapperMetadata = new AutoMapperMetadata();
        reverseMapperMetadata.setSourceClassName(autoMapperMetadata.getTargetClassName());
        reverseMapperMetadata.setTargetClassName(autoMapperMetadata.getSourceClassName());
        // 需要继承的属性
        final List<AutoMappingMetadata> fieldMetadataList = autoMapperMetadata.getFieldMappingList().stream()
            .map(fieldMapping -> {
                final AutoMappingMetadata autoMappingMetadata = new AutoMappingMetadata();
                autoMappingMetadata.setSource(fieldMapping.getTarget());
                autoMappingMetadata.setTarget(fieldMapping.getSource());
                return autoMappingMetadata;
            }).collect(Collectors.toList());
        reverseMapperMetadata.setFieldMappingList(fieldMetadataList);
        return reverseMapperMetadata;
    }

    private void writeAutoMapperClassFile(AutoMapperMetadata metadata) {
        String mapperPackage = AutoMapperProperties.getMapperPackage();
        String mapperClassName = metadata.mapperName();
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(mapperPackage + "." + mapperClassName)
            .openWriter()) {
            mapperGenerator.write(metadata, writer);
            addAdapterMethod(metadata.getSourceClassName(), metadata.getTargetClassName(),
                ClassName.get(mapperPackage, mapperClassName));
        } catch (IOException e) {
            processingEnv.getMessager()
                .printMessage(ERROR,
                    "Error while opening " + metadata.mapperName() + " output file: " + e.getMessage());
        }
    }

    private void addAdapterMethod(ClassName source, ClassName target, ClassName mapper) {
        AdapterMethodMetadata adapterMethodMetadata = AdapterMethodMetadata.newInstance(source, target, mapper);
        methodMap.put(adapterMethodMetadata.getMethodName(), adapterMethodMetadata);
    }

    private AutoMapperMetadata buildAutoMapperMetadata(final Element ele) {
        AutoMapper autoMapperAnnotation = ele.getAnnotation(AutoMapper.class);
        if (autoMapperAnnotation == null) {
            return null;
        }

        ClassName source = ClassName.get((TypeElement) ele);
        ClassName target = transToClassName(autoMapperAnnotation::target);
        if (target == null) {
            return null;
        }
        List<ClassName> uses = transToClassNameList(autoMapperAnnotation::uses);
        List<AutoMappingMetadata> autoMappingMetadataList = buildFieldMappingMetadata((TypeElement) ele);

        AutoMapperMetadata metadata = new AutoMapperMetadata();

        metadata.setSourceClassName(source);
        metadata.setTargetClassName(target);
        metadata.setUsesClassNameList(uses);
        metadata.setFieldMappingList(autoMappingMetadataList);

        return metadata;
    }

    private List<AutoMappingMetadata> buildFieldMappingMetadata(final TypeElement autoMapperEle) {
        List<AutoMappingMetadata> list = new ArrayList<>();

        if (!autoMapperEle.getKind().isClass()) {
            return list;
        }

        for (Element ele : autoMapperEle.getEnclosedElements()) {
            if (ele.getKind() != ElementKind.FIELD) {
                continue;
            }
            AutoMapping autoMapping = ele.getAnnotation(AutoMapping.class);
            if (autoMapping == null) {
                continue;
            }

            AutoMappingMetadata metadata = new AutoMappingMetadata();
            metadata.setTarget(autoMapping.target());
            metadata.setSource(ele.getSimpleName().toString());
            metadata.setIgnore(autoMapping.ignore());
            list.add(metadata);
        }

        return list;
    }

    private ClassName transToClassName(Supplier<Class<?>> classSupplier) {
        TypeMirror typeMirror = null;
        try {
            Class<?> targetClass = classSupplier.get();
        } catch (MirroredTypeException e) {
            typeMirror = e.getTypeMirror();
        }
        if (typeMirror == null) {
            return null;
        }
        return (ClassName) ClassName.get(typeMirror);
    }

    private List<ClassName> transToClassNameList(Supplier<Class<?>[]> classSuppler) {
        List<? extends TypeMirror> typeMirrors = null;
        try {
            Class<?>[] classes = classSuppler.get();
        } catch (MirroredTypesException e) {
            typeMirrors = e.getTypeMirrors();
        }
        return typeMirrors.stream()
            .map(typeMirror -> (ClassName) ClassName.get(typeMirror))
            .collect(Collectors.toList());
    }

}
