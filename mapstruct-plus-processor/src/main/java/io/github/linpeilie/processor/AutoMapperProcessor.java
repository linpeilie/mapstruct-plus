package io.github.linpeilie.processor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import io.github.linpeilie.ComponentModelConstant;
import io.github.linpeilie.annotations.AutoEnumMapper;
import io.github.linpeilie.annotations.AutoMapMapper;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import io.github.linpeilie.annotations.ComponentModelConfig;
import io.github.linpeilie.annotations.MapperConfig;
import io.github.linpeilie.annotations.ReverseAutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMappings;
import io.github.linpeilie.processor.generator.AutoEnumMapperGenerator;
import io.github.linpeilie.processor.generator.AutoMapperGenerator;
import io.github.linpeilie.processor.generator.DefaultAdapterMapperGenerator;
import io.github.linpeilie.processor.generator.MapperConfigGenerator;
import io.github.linpeilie.processor.generator.SolonAdapterMapperGenerator;
import io.github.linpeilie.processor.generator.SpringAdapterMapperGenerator;
import io.github.linpeilie.processor.metadata.AbstractAdapterMethodMetadata;
import io.github.linpeilie.processor.metadata.AdapterEnumMethodMetadata;
import io.github.linpeilie.processor.metadata.AdapterMapMethodMetadata;
import io.github.linpeilie.processor.metadata.AdapterMethodMetadata;
import io.github.linpeilie.processor.metadata.AutoEnumMapperMetadata;
import io.github.linpeilie.processor.metadata.AutoMapMapperMetadata;
import io.github.linpeilie.processor.metadata.AutoMapperMetadata;
import io.github.linpeilie.processor.metadata.AutoMappingMetadata;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.MappingConstants;

import static io.github.linpeilie.processor.Constants.AUTO_ENUM_MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.AUTO_MAPPERS_ANNOTATION;
import static io.github.linpeilie.processor.Constants.AUTO_MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.AUTO_MAP_MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.COMPONENT_MODEL_CONFIG_ANNOTATION;
import static io.github.linpeilie.processor.Constants.MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.MAPPER_CONFIG_ANNOTATION;
import static javax.tools.Diagnostic.Kind.ERROR;

@SupportedAnnotationTypes({AUTO_MAPPER_ANNOTATION, AUTO_MAPPERS_ANNOTATION, AUTO_MAP_MAPPER_ANNOTATION,
                           AUTO_ENUM_MAPPER_ANNOTATION, MAPPER_CONFIG_ANNOTATION, COMPONENT_MODEL_CONFIG_ANNOTATION,
                           MAPPER_ANNOTATION})
public class AutoMapperProcessor extends AbstractProcessor {

    private static final ClassName MAPPING_DEFAULT_TARGET = ClassName.get("io.github.linpeilie", "DefaultMapping");

    protected static final String DEFAULT_COMPONENT_MODEL = "mapstruct.defaultComponentModel";

    private final AutoMapperGenerator mapperGenerator;

    private AbstractAdapterMapperGenerator adapterMapperGenerator;

    private final MapperConfigGenerator mapperConfigGenerator;

    private final Map<String, AbstractAdapterMethodMetadata> methodMap = new HashMap<>();

    private final Map<String, AbstractAdapterMethodMetadata> mapMethodMap = new HashMap<>();

    private final List<AutoMapperMetadata> mapperList = new ArrayList<>();

    private final List<TypeMirror> customMapperList = new ArrayList<>();

    private final Set<String> mapperSet = new HashSet<>();

    public AutoMapperProcessor() {
        this.mapperGenerator = new AutoMapperGenerator();
        this.mapperConfigGenerator = new MapperConfigGenerator();
    }

    private boolean isAutoMapperAnnotation(TypeElement annotation) {
        return AUTO_MAPPER_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isAutoMappersAnnotation(TypeElement annotation) {
        return AUTO_MAPPERS_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isAutoMapMapperAnnotation(TypeElement annotation) {
        return AUTO_MAP_MAPPER_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isAutoEnumMapperAnnotation(TypeElement annotation) {
        return AUTO_ENUM_MAPPER_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isMapperConfigAnnotation(TypeElement annotation) {
        return MAPPER_CONFIG_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isMapperAnnotation(TypeElement annotation) {
        return MAPPER_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    private boolean isComponentModelConfigAnnotation(TypeElement annotation) {
        return COMPONENT_MODEL_CONFIG_ANNOTATION.contentEquals(annotation.getQualifiedName());
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        boolean hasAutoMapper = annotations.stream().anyMatch(this::isAutoMapperAnnotation);
        final boolean hasAutoMapMapper = annotations.stream().anyMatch(this::isAutoMapMapperAnnotation);
        final boolean hasAutoEnumMapper = annotations.stream().anyMatch(this::isAutoEnumMapperAnnotation);
        final boolean hasAutoMapMappers = annotations.stream().anyMatch(this::isAutoMappersAnnotation);
        if (!hasAutoMapper && !hasAutoMapMapper && !hasAutoEnumMapper && !hasAutoMapMappers) {
            return false;
        }
        // 刷新配置
        refreshProperties(annotations, roundEnv);

        // 根据配置生成适配类生成器
        switch (AutoMapperProperties.getComponentModel()) {
            case MappingConstants.ComponentModel.SPRING:
                this.adapterMapperGenerator = new SpringAdapterMapperGenerator();
                break;
            case ComponentModelConstant.SOLON:
                this.adapterMapperGenerator = new SolonAdapterMapperGenerator();
                break;
            default:
                this.adapterMapperGenerator = new DefaultAdapterMapperGenerator();
        }

        // AutoMapMapper
        annotations.stream()
            .filter(this::isAutoMapMapperAnnotation)
            .findFirst()
            .ifPresent(annotation -> processAutoMapMapperAnnotation(roundEnv, annotation));

        // AutoEnumMapper
        annotations.stream()
            .filter(this::isAutoEnumMapperAnnotation)
            .findFirst()
            .ifPresent(annotation -> processAutoEnumMapperAnnotation(roundEnv, annotation));

        // AutoMapper
        annotations.stream()
            .filter(this::isAutoMapperAnnotation)
            .findFirst()
            .ifPresent(annotation -> processAutoMapperAnnotation(roundEnv, annotation));

        // AutoMappers
        annotations.stream()
            .filter(this::isAutoMappersAnnotation)
            .findFirst()
            .ifPresent(annotation -> processAutoMappersAnnotation(roundEnv, annotation));

        // custom mapper
        annotations.stream()
            .filter(this::isMapperAnnotation)
            .findFirst()
            .ifPresent(annotation -> processMapperAnnotation(roundEnv, annotation));

        // 生成类
        generateMapper();

        return false;
    }

    private void processMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        roundEnv.getElementsAnnotatedWith(annotation)
            .forEach(element -> customMapperList.add(element.asType()));
    }

    private void processAutoEnumMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        roundEnv.getElementsAnnotatedWith(annotation)
            .stream()
            .map(this::buildAutoEnumMapperMetadata)
            .filter(Objects::nonNull)
            .forEach(this::writeAutoEnumMapperFile);
    }

    private void writeAutoEnumMapperFile(final AutoEnumMapperMetadata autoEnumMapperMetadata) {
        final AutoEnumMapperGenerator autoEnumMapperGenerator = new AutoEnumMapperGenerator();
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(autoEnumMapperMetadata.mapperPackage() + "." + autoEnumMapperMetadata.mapperName())
            .openWriter()) {
            autoEnumMapperGenerator.write(autoEnumMapperMetadata, writer);
        } catch (IOException e) {
            processingEnv.getMessager()
                .printMessage(ERROR,
                    "Error while opening " + autoEnumMapperMetadata.mapperName() + " output file : " + e.getMessage());
        }
        addAdapterMethodMetadata(autoEnumMapperMetadata);
    }

    private void addAdapterMethodMetadata(final AutoEnumMapperMetadata autoEnumMapperMetadata) {
        // toValue
        final AdapterEnumMethodMetadata toValueProxyMethod =
            new AdapterEnumMethodMetadata(autoEnumMapperMetadata.getSourceClassName(),
                ClassName.get(autoEnumMapperMetadata.mapperPackage(), autoEnumMapperMetadata.mapperName()),
                autoEnumMapperMetadata.toValueMethodName(),
                autoEnumMapperMetadata.getReturnType());
        // toEnum
        final AdapterEnumMethodMetadata toEnumProxyMethod =
            new AdapterEnumMethodMetadata(autoEnumMapperMetadata.getReturnType(),
                ClassName.get(autoEnumMapperMetadata.mapperPackage(), autoEnumMapperMetadata.mapperName()),
                autoEnumMapperMetadata.toEnumMethodName(),
                autoEnumMapperMetadata.getSourceClassName());
        methodMap.put(
            autoEnumMapperMetadata.getSourceClassName().simpleName() + toValueProxyMethod.getMapperMethodName(),
            toValueProxyMethod);
        methodMap.put(
            autoEnumMapperMetadata.getSourceClassName().simpleName() + toEnumProxyMethod.getMapperMethodName(),
            toEnumProxyMethod);
    }

    private AutoEnumMapperMetadata buildAutoEnumMapperMetadata(final Element element) {
        final AutoEnumMapper autoEnumMapper = element.getAnnotation(AutoEnumMapper.class);
        final ClassName enumClassName = ClassName.get((TypeElement) element);
        final String enumCodeFieldName = autoEnumMapper.value();
        Element enumCodeGetterElement = null;
        // 获取getter
        final List<? extends Element> enclosedElements = element.getEnclosedElements();
        for (Element ele : enclosedElements) {
            if (!ElementKind.METHOD.equals(ele.getKind())) {
                continue;
            }
            boolean isGetter = StrUtil.equalsIgnoreCase(ele.getSimpleName(), "get" + enumCodeFieldName)
                               || StrUtil.equalsIgnoreCase(ele.getSimpleName(), "is" + enumCodeFieldName);
            if (isGetter) {
                enumCodeGetterElement = ele;
                break;
            }
        }
        if (enumCodeGetterElement == null) {
            return null;
        }
        final String getter = enumCodeGetterElement.getSimpleName().toString();
        final TypeName returnType = TypeName.get(((ExecutableElement) enumCodeGetterElement).getReturnType());

        AutoEnumMapperMetadata autoEnumMapperMetadata = new AutoEnumMapperMetadata();
        autoEnumMapperMetadata.setSourceClassName(enumClassName);
        autoEnumMapperMetadata.setGetter(getter);
        autoEnumMapperMetadata.setReturnType(returnType);

        return autoEnumMapperMetadata;
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
            AutoMapperProperties.getMapAdapterClassName(), null);
    }

    private void refreshProperties(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        // annotation --> MapperConfig
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
                AutoMapperProperties.setUnmappedSourcePolicy(mapperConfig.unmappedSourcePolicy());
                AutoMapperProperties.setUnmappedTargetPolicy(mapperConfig.unmappedTargetPolicy());
                AutoMapperProperties.setNullValueMappingStrategy(mapperConfig.nullValueMappingStrategy());
                AutoMapperProperties.setNullValuePropertyMappingStrategy(mapperConfig.nullValuePropertyMappingStrategy());
                AutoMapperProperties.setBuildMethod(mapperConfig.builder().buildMethod());
                AutoMapperProperties.setDisableBuilder(mapperConfig.builder().disableBuilder());
                if (StrUtil.isNotEmpty(mapperConfig.adapterPackage())) {
                    AutoMapperProperties.setAdapterPackage(mapperConfig.adapterPackage());
                }
                if (StrUtil.isNotEmpty(mapperConfig.adapterClassName())) {
                    AutoMapperProperties.setAdapterClassName(mapperConfig.adapterClassName());
                }
                if (StrUtil.isNotEmpty(mapperConfig.mapAdapterClassName())) {
                    AutoMapperProperties.setMapAdapterClassName(mapperConfig.mapAdapterClassName());
                }
            });

        // compilerArgs
        String componentModel = processingEnv.getOptions().get(DEFAULT_COMPONENT_MODEL);
        if (StrUtil.isNotEmpty(componentModel)) {
            AutoMapperProperties.setComponentModel(componentModel);
        }

        // annotation --> ComponentModelConfig
        annotations.stream()
            .filter(this::isComponentModelConfigAnnotation)
            .findFirst()
            .flatMap(annotation -> roundEnv.getElementsAnnotatedWith(annotation).stream().findFirst())
            .ifPresent(element -> {
                if (StrUtil.isEmpty(componentModel)) {
                    final ComponentModelConfig componentModelConfig = element.getAnnotation(ComponentModelConfig.class);
                    String componentModelByAnnotation = componentModelConfig.componentModel();
                    AutoMapperProperties.setComponentModel(componentModelByAnnotation);
                }
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

        mapperList.addAll(autoMapperMetadataList);
    }

    private void processAutoMappersAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        final List<AutoMapperMetadata> autoMapperMetadata = roundEnv.getElementsAnnotatedWith(annotation)
            .stream()
            .map(this::buildAutoMapperMetadataByAutoMappers)
            .filter(Objects::nonNull)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        mapperList.addAll(autoMapperMetadata);
    }

    private void generateMapper() {
        List<AutoMapperMetadata> reverseMapperMetadataList = new ArrayList<>();

        mapperList.forEach(autoMapperMetadata -> {
            if (!autoMapperMetadata.isReverseConvertGenerate()) {
                return;
            }
            boolean defineReverseMapping = CollectionUtil.isNotEmpty(autoMapperMetadata.getFieldReverseMappingList());
            final AutoMapperMetadata reverseMapperMetadata = reverseMapper(autoMapperMetadata);
            if (defineReverseMapping) {
                addMapper(reverseMapperMetadata);
            } else if (!mapperSet.add(reverseMapperMetadata.mapperName())) {
                return;
            }
            reverseMapperMetadataList.add(reverseMapperMetadata);
        });

        mapperList.addAll(reverseMapperMetadataList);

        mapperList.forEach(metadata -> {
            if (!metadata.isConvertGenerate()) {
                return;
            }
            this.writeAutoMapperClassFile(metadata);
            addAdapterMethod(metadata.getSourceClassName(), metadata.getTargetClassName(), metadata.mapperClass());
        });

        adapterMapperGenerator.write(processingEnv, methodMap.values(), AutoMapperProperties.getAdapterClassName());

        mapperConfigGenerator.write(processingEnv, AutoMapperProperties.getConfigClassName(),
            AutoMapperProperties.getAdapterClassName(), customMapperList);
    }

    private AutoMapperMetadata reverseMapper(AutoMapperMetadata autoMapperMetadata) {
        AutoMapperMetadata reverseMapperMetadata = initAutoMapperMetadata(
            autoMapperMetadata.getTargetClassName(), autoMapperMetadata.getSourceClassName());
        reverseMapperMetadata.setConvertGenerate(autoMapperMetadata.isReverseConvertGenerate());
        reverseMapperMetadata.setUsesClassNameList(autoMapperMetadata.getUsesClassNameList());
        reverseMapperMetadata.setMapstructConfigClass(
            ClassName.get(AutoMapperProperties.getConfigPackage(), AutoMapperProperties.getConfigClassName()));
        if (CollectionUtil.isNotEmpty(autoMapperMetadata.getFieldReverseMappingList())) {
            reverseMapperMetadata.setFieldMappingList(autoMapperMetadata.getFieldReverseMappingList());
        } else {
            // 需要继承的属性
            final List<AutoMappingMetadata> fieldMetadataList =
                autoMapperMetadata.getFieldMappingList().stream().map(fieldMapping -> {
                    final AutoMappingMetadata autoMappingMetadata = new AutoMappingMetadata();
                    autoMappingMetadata.setSource(fieldMapping.getTarget());
                    autoMappingMetadata.setTarget(fieldMapping.getSource());
                    return autoMappingMetadata;
                }).collect(Collectors.toList());
            reverseMapperMetadata.setFieldMappingList(fieldMetadataList);
        }
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

    private AutoMapperMetadata initAutoMapperMetadata(ClassName source, ClassName target) {
        AutoMapperMetadata metadata = new AutoMapperMetadata();

        metadata.setSourceClassName(source);
        metadata.setTargetClassName(target);
        metadata.setSuperClass(ClassName.get("io.github.linpeilie", "BaseMapper"));
        metadata.setSuperGenerics(new ClassName[] {source, target});
        metadata.setMapstructConfigClass(
            ClassName.get(AutoMapperProperties.getConfigPackage(), AutoMapperProperties.getConfigClassName()));
        return metadata;
    }

    private List<AutoMapperMetadata> buildAutoMapperMetadataByAutoMappers(final Element ele) {
        final AutoMappers autoMappers = ele.getAnnotation(AutoMappers.class);
        if (autoMappers == null) {
            return null;
        }
        Set<String> targetClassNames = new HashSet<>();
        return Arrays.stream(autoMappers.value())
            .filter(autoMapper -> {
                ClassName className = transToClassName(autoMapper::target);
                if (className == null) {
                    return false;
                }
                return targetClassNames.add(className.reflectionName());
            })
            .map(autoMapper -> buildAutoMapperMetadata(autoMapper, ele))
            .collect(Collectors.toList());
    }

    private AutoMapperMetadata buildAutoMapperMetadata(final Element ele) {
        AutoMapper autoMapperAnnotation = ele.getAnnotation(AutoMapper.class);
        if (autoMapperAnnotation == null) {
            return null;
        }
        return buildAutoMapperMetadata(autoMapperAnnotation, ele);
    }

    private boolean hasReverseAutoMapping(Element ele) {
        TypeElement typeElement = (TypeElement) ele;
        if (!typeElement.getKind().isClass()) {
            return false;
        }
        return typeElement.getEnclosedElements()
            .stream().anyMatch(e -> {
                if (e.getKind() != ElementKind.FIELD) {
                    return false;
                }
                return e.getAnnotation(ReverseAutoMapping.class) != null
                       || e.getAnnotation(ReverseAutoMappings.class) != null;
            });
    }

    private boolean isTargetFieldMapping(ClassName target, AutoMappingMetadata mappingMetadata) {
        if (MAPPING_DEFAULT_TARGET.reflectionName().contentEquals(mappingMetadata.getTargetClass().reflectionName())) {
            return true;
        }
        if (target.reflectionName().contentEquals(mappingMetadata.getTargetClass().reflectionName())) {
            return true;
        }
        return false;
    }

    private AutoMapperMetadata buildAutoMapperMetadata(final AutoMapper autoMapper, final Element ele) {
        ClassName source = ClassName.get((TypeElement) ele);
        ClassName target = transToClassName(autoMapper::target);
        if (target == null) {
            return null;
        }
        List<ClassName> uses = transToClassNameList(autoMapper::uses);
        List<AutoMappingMetadata> autoMappingMetadataList = buildFieldMappingMetadata((TypeElement) ele);
        autoMappingMetadataList.removeIf(mappingMetadata -> !isTargetFieldMapping(target, mappingMetadata));

        List<AutoMappingMetadata> reverseMappingMetadataList = buildFieldReverseMappingMetadata((TypeElement) ele);
        reverseMappingMetadataList.removeIf(mappingMetadata -> !isTargetFieldMapping(target, mappingMetadata));

        AutoMapperMetadata metadata = initAutoMapperMetadata(source, target);

        metadata.setUsesClassNameList(uses);
        metadata.setFieldMappingList(autoMappingMetadataList);
        metadata.setFieldReverseMappingList(reverseMappingMetadataList);
        metadata.setConvertGenerate(autoMapper.convertGenerate());
        metadata.setReverseConvertGenerate(autoMapper.reverseConvertGenerate());

        addMapper(metadata);

        return metadata;
    }

    private List<AutoMappingMetadata> buildFieldReverseMappingMetadata(final TypeElement ele) {
        List<AutoMappingMetadata> list = new ArrayList<>();
        if (!ele.getKind().isClass()) {
            return list;
        }
        for (Element field : ele.getEnclosedElements()) {
            if (field.getKind() != ElementKind.FIELD) {
                continue;
            }
            ReverseAutoMapping reverseAutoMapping = field.getAnnotation(ReverseAutoMapping.class);
            if (reverseAutoMapping != null) {
                list.add(buildAutoMappingMetadata(reverseAutoMapping, field, ele));
            }
            ReverseAutoMappings reverseAutoMappings = field.getAnnotation(ReverseAutoMappings.class);
            if (reverseAutoMappings != null) {
                for (ReverseAutoMapping mapping : reverseAutoMappings.value()) {
                    list.add(buildAutoMappingMetadata(mapping, field, ele));
                }
            }
        }
        list.removeIf(Objects::isNull);
        return list;
    }

    private AutoMappingMetadata buildAutoMappingMetadata(ReverseAutoMapping reverseAutoMapping,
        Element ele,
        TypeElement type) {
        ClassName targetClass = transToClassName(reverseAutoMapping::targetClass);
        if (targetClass == null) {
            return null;
        }

        AutoMappingMetadata metadata = new AutoMappingMetadata();
        if (StrUtil.isNotEmpty(reverseAutoMapping.source())) {
            metadata.setSource(reverseAutoMapping.source());
        } else {
            metadata.setSource(ele.getSimpleName().toString());
        }
        if (StrUtil.isNotEmpty(reverseAutoMapping.target())) {
            metadata.setTarget(reverseAutoMapping.target());
        } else {
            metadata.setTarget(ele.getSimpleName().toString());
        }
        metadata.setTargetClass(targetClass);
        metadata.setDefaultValue(reverseAutoMapping.defaultValue());
        metadata.setIgnore(reverseAutoMapping.ignore());
        metadata.setExpression(reverseAutoMapping.expression());
        metadata.setDateFormat(reverseAutoMapping.dateFormat());
        metadata.setNumberFormat(reverseAutoMapping.numberFormat());
        return metadata;
    }

    private void addMapper(AutoMapperMetadata metadata) {
        if (!mapperSet.add(metadata.mapperName())) {
            throw new DuplicateMapperException("An exception occurred to generate " + metadata.mapperName()
                                               + ", check the mapping configuration for "
                                               + metadata.getSourceClassName().reflectionName()
                                               + " or " + metadata.getTargetClassName().reflectionName());
        }
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
            if (autoMapping != null) {
                list.add(buildAutoMappingMetadata(autoMapping, ele, autoMapperEle));
            }
            final AutoMappings autoMappings = ele.getAnnotation(AutoMappings.class);
            if (autoMappings != null) {
                for (AutoMapping autoMappingEle : autoMappings.value()) {
                    list.add(buildAutoMappingMetadata(autoMappingEle, ele, autoMapperEle));
                }
            }
        }

        list.removeIf(Objects::isNull);
        return list;
    }

    private AutoMappingMetadata buildAutoMappingMetadata(AutoMapping autoMapping, Element ele, TypeElement type) {
        ClassName targetClass = transToClassName(autoMapping::targetClass);
        if (targetClass == null) {
            return null;
        }

        AutoMappingMetadata metadata = new AutoMappingMetadata();
        if (StrUtil.isNotEmpty(autoMapping.source())) {
            metadata.setSource(autoMapping.source());
        } else {
            metadata.setSource(ele.getSimpleName().toString());
        }
        if (StrUtil.isNotEmpty(autoMapping.target())) {
            metadata.setTarget(autoMapping.target());
        } else {
            metadata.setTarget(ele.getSimpleName().toString());
        }
        metadata.setTargetClass(targetClass);
        metadata.setDefaultValue(autoMapping.defaultValue());
        metadata.setIgnore(autoMapping.ignore());
        metadata.setExpression(autoMapping.expression());
        metadata.setDateFormat(autoMapping.dateFormat());
        metadata.setNumberFormat(autoMapping.numberFormat());
        return metadata;
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

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
