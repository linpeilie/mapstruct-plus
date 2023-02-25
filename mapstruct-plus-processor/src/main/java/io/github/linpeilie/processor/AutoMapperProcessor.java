package io.github.linpeilie.processor;

import com.squareup.javapoet.ClassName;
import io.github.linpeilie.annotations.AutoMapMapper;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.ComponentModelConfig;
import io.github.linpeilie.annotations.MapperConfig;
import io.github.linpeilie.processor.generator.AutoMapperGenerator;
import io.github.linpeilie.processor.generator.DefaultAdapterMapperGenerator;
import io.github.linpeilie.processor.generator.MapperConfigGenerator;
import io.github.linpeilie.processor.generator.SpringAdapterMapperGenerator;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import io.github.linpeilie.processor.metadata.AdapterMapMethodMetadata;
import io.github.linpeilie.processor.metadata.AdapterMethodMetadata;
import io.github.linpeilie.processor.metadata.AutoMapMapperMetadata;
import io.github.linpeilie.processor.metadata.AutoMapperMetadata;
import io.github.linpeilie.processor.metadata.AutoMappingMetadata;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.mapstruct.MappingConstants;

import static io.github.linpeilie.processor.Constants.AUTO_MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.AUTO_MAP_MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.COMPONENT_MODEL_CONFIG_ANNOTATION;
import static io.github.linpeilie.processor.Constants.MAPPER_CONFIG_ANNOTATION;
import static javax.tools.Diagnostic.Kind.ERROR;

@SupportedAnnotationTypes({AUTO_MAPPER_ANNOTATION, AUTO_MAP_MAPPER_ANNOTATION, MAPPER_CONFIG_ANNOTATION,
                           COMPONENT_MODEL_CONFIG_ANNOTATION})
public class AutoMapperProcessor extends AbstractProcessor {

    private final AutoMapperGenerator mapperGenerator;

    private AbstractAdapterMapperGenerator adapterMapperGenerator;

    private final MapperConfigGenerator mapperConfigGenerator;

    private final Map<String, AbstractAdapterMethodMetadata> methodMap = new HashMap<>();

    private final Map<String, AbstractAdapterMethodMetadata> mapMethodMap = new HashMap<>();

    private final Set<String> mapperSet = new HashSet<>();

    public AutoMapperProcessor() {
        this.mapperGenerator = new AutoMapperGenerator();
        this.mapperConfigGenerator = new MapperConfigGenerator();
    }

    private boolean isAutoMapperAnnotation(TypeElement annotation) {
        return AUTO_MAPPER_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isAutoMapMapperAnnotation(TypeElement annotation) {
        return AUTO_MAP_MAPPER_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isMapperConfigAnnotation(TypeElement annotation) {
        return MAPPER_CONFIG_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isComponentModelConfigAnnotation(TypeElement annotation) {
        return COMPONENT_MODEL_CONFIG_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        boolean hasAutoMapper = annotations.stream().anyMatch(this::isAutoMapperAnnotation);
        if (!hasAutoMapper) {
            return false;
        }
        // 刷新配置
        refreshProperties(annotations, roundEnv);

        // 根据配置生成注解类
        this.adapterMapperGenerator = AutoMapperProperties.getComponentModel()
                                          .contentEquals(
                                              MappingConstants.ComponentModel.SPRING) ? new SpringAdapterMapperGenerator() : new DefaultAdapterMapperGenerator();

        // 获取 MapMapper
        annotations.stream()
            .filter(this::isAutoMapMapperAnnotation)
            .findFirst()
            .ifPresent(annotation -> processAutoMapMapperAnnotation(roundEnv, annotation));

        // 生成类
        annotations.stream()
            .filter(this::isAutoMapperAnnotation)
            .findFirst()
            .ifPresent(annotation -> processAutoMapperAnnotation(roundEnv, annotation));
        return false;
    }

    private void processAutoMapMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        final List<AutoMapperMetadata> autoMapMapperMetadataList =
            roundEnv.getElementsAnnotatedWith(annotation).stream().map(ele -> {
                if (ele.getAnnotation(AutoMapMapper.class) == null) {
                    return null;
                }
                ClassName source = ClassName.get("java.util", "Map");
                ClassName target = ClassName.get((TypeElement) ele);
                List<ClassName> uses = Arrays.asList(ClassName.get("io.github.linpeilie.map", "MapObjectConvert"));

                final AutoMapperMetadata autoMapperMetadata = new AutoMapMapperMetadata();
                autoMapperMetadata.setTargetClassName(target);
                autoMapperMetadata.setSourceClassName(source);
                autoMapperMetadata.setUsesClassNameList(uses);
                autoMapperMetadata.setSuperClass(ClassName.get("io.github.linpeilie", "BaseMapMapper"));
                autoMapperMetadata.setSuperGenerics(new ClassName[] {target});
                autoMapperMetadata.setMapstructConfigClass(ClassName.get(AutoMapperProperties.getConfigPackage(),
                    AutoMapperProperties.getMapConfigClassName()));
                return autoMapperMetadata;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        autoMapMapperMetadataList.forEach(metadata -> {
            this.writeAutoMapperClassFile(metadata);
            addAdapterMapMethod(metadata.getSourceClassName(), metadata.getTargetClassName(), metadata.mapperClass(),
                false);
            addAdapterMapMethod(ClassName.get("java.lang", "Object"), metadata.getTargetClassName(),
                metadata.mapperClass(), true);
        });
        adapterMapperGenerator.write(processingEnv, mapMethodMap.values(),
            AutoMapperProperties.getMapAdapterClassName());

        mapperConfigGenerator.write(processingEnv, AutoMapperProperties.getMapConfigClassName(),
            AutoMapperProperties.getMapAdapterClassName());
    }

    private void refreshProperties(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        annotations.stream()
            .filter(this::isMapperConfigAnnotation)
            .findFirst()
            .flatMap(annotation -> roundEnv.getElementsAnnotatedWith(annotation).stream().findFirst())
            .ifPresent(element -> {
                final MapperConfig mapperConfig = element.getAnnotation(MapperConfig.class);
                String mapperPackage = StringUtils.isEmpty(mapperConfig.mapperPackage()) ? getPackageName(
                    element) : mapperConfig.mapperPackage();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "mapper package " + mapperPackage);
                AutoMapperProperties.setMapperPackage(mapperPackage);
            });
        annotations.stream()
            .filter(this::isComponentModelConfigAnnotation)
            .findFirst()
            .flatMap(annotation -> roundEnv.getElementsAnnotatedWith(annotation).stream().findFirst())
            .ifPresent(element -> {
                final ComponentModelConfig componentModelConfig = element.getAnnotation(ComponentModelConfig.class);
                String componentModel = StringUtils.isEmpty(
                    componentModelConfig.componentModel()) ? "default" : componentModelConfig.componentModel();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "component model " + componentModel);
                AutoMapperProperties.setComponentModel(componentModel);
            });
    }

    private String getPackageName(Element element) {
        return String.valueOf(processingEnv.getElementUtils().getPackageOf(element).getQualifiedName());
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

        autoMapperMetadataList.forEach(metadata -> {
            this.writeAutoMapperClassFile(metadata);
            addAdapterMethod(metadata.getSourceClassName(), metadata.getTargetClassName(), metadata.mapperClass());
        });

        adapterMapperGenerator.write(processingEnv, methodMap.values(), AutoMapperProperties.getAdapterClassName());

        mapperConfigGenerator.write(processingEnv, AutoMapperProperties.getConfigClassName(),
            AutoMapperProperties.getAdapterClassName());
    }

    private AutoMapperMetadata reverseMapper(AutoMapperMetadata autoMapperMetadata) {
        AutoMapperMetadata reverseMapperMetadata = new AutoMapperMetadata();
        reverseMapperMetadata.setSourceClassName(autoMapperMetadata.getTargetClassName());
        reverseMapperMetadata.setTargetClassName(autoMapperMetadata.getSourceClassName());
        reverseMapperMetadata.setSuperClass(autoMapperMetadata.getSuperClass());
        reverseMapperMetadata.setSuperGenerics(
            new ClassName[] {reverseMapperMetadata.getSourceClassName(), reverseMapperMetadata.getTargetClassName()});
        reverseMapperMetadata.setMapstructConfigClass(
            ClassName.get(AutoMapperProperties.getConfigPackage(), AutoMapperProperties.getConfigClassName()));
        // 需要继承的属性
        final List<AutoMappingMetadata> fieldMetadataList =
            autoMapperMetadata.getFieldMappingList().stream().map(fieldMapping -> {
                final AutoMappingMetadata autoMappingMetadata = new AutoMappingMetadata();
                autoMappingMetadata.setSource(fieldMapping.getTarget());
                autoMappingMetadata.setTarget(fieldMapping.getSource());
                return autoMappingMetadata;
            }).collect(Collectors.toList());
        reverseMapperMetadata.setFieldMappingList(fieldMetadataList);
        return reverseMapperMetadata;
    }

    private void writeAutoMapperClassFile(AutoMapperMetadata metadata) {
        String mapperPackage = metadata.mapperPackage();
        String mapperClassName = metadata.mapperName();
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(mapperPackage + "." + mapperClassName)
            .openWriter()) {
            mapperGenerator.write(metadata, writer);
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

    private void addAdapterMapMethod(ClassName source, ClassName target, ClassName mapper, boolean objectConverter) {
        final AdapterMapMethodMetadata adapterMapMethodMetadata =
            new AdapterMapMethodMetadata(source, target, mapper, objectConverter);
        mapMethodMap.put(adapterMapMethodMetadata.getMethodName(), adapterMapMethodMetadata);
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
        metadata.setSuperClass(ClassName.get("io.github.linpeilie", "BaseMapper"));
        metadata.setSuperGenerics(new ClassName[] {source, target});
        metadata.setMapstructConfigClass(
            ClassName.get(AutoMapperProperties.getConfigPackage(), AutoMapperProperties.getConfigClassName()));

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
            metadata.setExpression(autoMapping.expression());
            metadata.setDateFormat(autoMapping.dateFormat());
            metadata.setNumberFormat(autoMapping.numberFormat());
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
