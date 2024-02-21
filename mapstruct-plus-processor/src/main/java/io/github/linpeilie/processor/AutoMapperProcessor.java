package io.github.linpeilie.processor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
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
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.mapstruct.MappingConstants;

import static io.github.linpeilie.processor.Constants.AUTO_ENUM_MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.AUTO_MAPPERS_ANNOTATION;
import static io.github.linpeilie.processor.Constants.AUTO_MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.AUTO_MAP_MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.COMPONENT_MODEL_CONFIG_ANNOTATION;
import static io.github.linpeilie.processor.Constants.MAPPER_ANNOTATION;
import static io.github.linpeilie.processor.Constants.MAPPER_CONFIG_ANNOTATION;
import static io.github.linpeilie.processor.ProcessorOptions.ADAPTER_CLASS_NAME;
import static io.github.linpeilie.processor.ProcessorOptions.ADAPTER_PACKAGE;
import static io.github.linpeilie.processor.ProcessorOptions.BUILDER_BUILD_METHOD;
import static io.github.linpeilie.processor.ProcessorOptions.BUILDER_DISABLE_BUILDER;
import static io.github.linpeilie.processor.ProcessorOptions.MAPPER_CONFIG_CLASS;
import static io.github.linpeilie.processor.ProcessorOptions.MAPPER_PACKAGE;
import static io.github.linpeilie.processor.ProcessorOptions.MAP_ADAPTER_CLASS_NAME;
import static io.github.linpeilie.processor.ProcessorOptions.NULL_VALUE_MAPPING_STRATEGY;
import static io.github.linpeilie.processor.ProcessorOptions.NULL_VALUE_PROPERTY_MAPPING_STRATEGY;
import static io.github.linpeilie.processor.ProcessorOptions.UNMAPPED_SOURCE_POLICY;
import static io.github.linpeilie.processor.ProcessorOptions.UNMAPPED_TARGET_POLICY;
import static javax.tools.Diagnostic.Kind.ERROR;

@SupportedAnnotationTypes({AUTO_MAPPER_ANNOTATION, AUTO_MAPPERS_ANNOTATION, AUTO_MAP_MAPPER_ANNOTATION,
                           AUTO_ENUM_MAPPER_ANNOTATION, MAPPER_CONFIG_ANNOTATION, COMPONENT_MODEL_CONFIG_ANNOTATION,
                           MAPPER_ANNOTATION})
@SupportedOptions({
    MAPPER_CONFIG_CLASS,
    MAPPER_PACKAGE,
    UNMAPPED_SOURCE_POLICY,
    UNMAPPED_TARGET_POLICY,
    NULL_VALUE_MAPPING_STRATEGY,
    NULL_VALUE_PROPERTY_MAPPING_STRATEGY,
    BUILDER_BUILD_METHOD,
    BUILDER_DISABLE_BUILDER,
    ADAPTER_PACKAGE,
    ADAPTER_CLASS_NAME,
    MAP_ADAPTER_CLASS_NAME,
})
public class AutoMapperProcessor extends AbstractProcessor {

    private static final ClassName MAPPING_DEFAULT_TARGET = ClassName.get("io.github.linpeilie", "DefaultMapping");

    private final AutoMapperGenerator mapperGenerator;

    private AbstractAdapterMapperGenerator adapterMapperGenerator;

    private final MapperConfigGenerator mapperConfigGenerator;

    private final Map<String, AbstractAdapterMethodMetadata> methodMap = new HashMap<>();

    private final Map<String, AbstractAdapterMethodMetadata> mapMethodMap = new HashMap<>();

    private final List<AutoMapperMetadata> mapperList = new ArrayList<>();

    private final List<TypeMirror> customMapperList = new ArrayList<>();

    private final Set<String> mapperSet = new HashSet<>();

    private Messager messager;

    private Filer filer;

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
    public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        try {
            doProcess(annotations, roundEnv);
        } catch (Exception e) {
            messager.printMessage(ERROR, ExceptionUtil.stacktraceToString(e));
        }

        return false;
    }

    private void doProcess(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        boolean hasAutoMapper = annotations.stream().anyMatch(this::isAutoMapperAnnotation);
        final boolean hasAutoMapMapper = annotations.stream().anyMatch(this::isAutoMapMapperAnnotation);
        final boolean hasAutoEnumMapper = annotations.stream().anyMatch(this::isAutoEnumMapperAnnotation);
        final boolean hasAutoMapMappers = annotations.stream().anyMatch(this::isAutoMappersAnnotation);
        final boolean hasMapperConfig = annotations.stream().anyMatch(this::isMapperConfigAnnotation);
        if (!hasAutoMapper && !hasAutoMapMapper && !hasAutoEnumMapper && !hasAutoMapMappers && !hasMapperConfig) {
            return;
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
        final TypeElement autoMapMapperAnnotation =
            processingEnv.getElementUtils().getTypeElement(AUTO_MAP_MAPPER_ANNOTATION);
        processAutoMapMapperAnnotation(roundEnv, autoMapMapperAnnotation);

        // AutoEnumMapper
        final TypeElement autoEnumMapperAnnotation =
            processingEnv.getElementUtils().getTypeElement(AUTO_ENUM_MAPPER_ANNOTATION);
        processAutoEnumMapperAnnotation(roundEnv, autoEnumMapperAnnotation, false);
        processAutoEnumMapperAnnotation(roundEnv, autoEnumMapperAnnotation, true);

        // AutoMapper
        final TypeElement autoMapperAnnotation = processingEnv.getElementUtils().getTypeElement(AUTO_MAPPER_ANNOTATION);
        processAutoMapperAnnotation(roundEnv, autoMapperAnnotation, false);
        processAutoMapperAnnotation(roundEnv, autoMapperAnnotation, true);

        // AutoMappers
        final TypeElement autoMappersAnnotation =
            processingEnv.getElementUtils().getTypeElement(AUTO_MAPPERS_ANNOTATION);
        processAutoMappersAnnotation(roundEnv, autoMappersAnnotation, false);
        processAutoMappersAnnotation(roundEnv, autoMappersAnnotation, true);

        // custom mapper
        final TypeElement mapperAnnotation = processingEnv.getElementUtils().getTypeElement(MAPPER_ANNOTATION);
        processMapperAnnotation(roundEnv, mapperAnnotation);

        // 生成类
        generateMapper();
    }

    private List<TypeElement> getElementAndMergeHistory(final RoundEnvironment roundEnv,
        TypeElement annotation,
        BuildCollator buildCollator) {
        buildCollator.appendNonexistent(roundEnv.getElementsAnnotatedWith(annotation));
        return buildCollator.getRecords();
    }

    private void processMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        if (annotation == null) {
            return;
        }

        final List<TypeElement> elements = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, Constants.MAPPERS_FILE_NAME));

        elements.forEach(element -> customMapperList.add(element.asType()));
    }

    private void processAutoEnumMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation, boolean cycleAvoiding) {
        if (annotation == null) {
            return;
        }
        final List<TypeElement> elements = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, Constants.ENUM_MAPPERS_FILE_NAME));
        elements.stream()
            .map(element -> buildAutoEnumMapperMetadata(element, cycleAvoiding))
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
        if (autoEnumMapperMetadata == null) {
            return;
        }
        // toValue
        final AdapterEnumMethodMetadata toValueProxyMethod =
            new AdapterEnumMethodMetadata(autoEnumMapperMetadata.getSourceClassName(),
                ClassName.get(autoEnumMapperMetadata.mapperPackage(), autoEnumMapperMetadata.mapperName()),
                autoEnumMapperMetadata.toValueMethodName(),
                autoEnumMapperMetadata.getReturnType(), autoEnumMapperMetadata.isCycleAvoiding());
        // toEnum
        final AdapterEnumMethodMetadata toEnumProxyMethod =
            new AdapterEnumMethodMetadata(autoEnumMapperMetadata.getReturnType(),
                ClassName.get(autoEnumMapperMetadata.mapperPackage(), autoEnumMapperMetadata.mapperName()),
                autoEnumMapperMetadata.toEnumMethodName(),
                autoEnumMapperMetadata.getSourceClassName(), autoEnumMapperMetadata.isCycleAvoiding());
        methodMap.putIfAbsent(
            autoEnumMapperMetadata.mapperName() + "." + toValueProxyMethod.getMapperMethodName(),
            toValueProxyMethod);
        methodMap.put(
            autoEnumMapperMetadata.mapperName() + "." + toEnumProxyMethod.getMapperMethodName(),
            toEnumProxyMethod);
    }

    private AutoEnumMapperMetadata buildAutoEnumMapperMetadata(final Element element, boolean cycleAvoiding) {
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
        autoEnumMapperMetadata.setCycleAvoiding(cycleAvoiding);

        return autoEnumMapperMetadata;
    }

    private void processAutoMapMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        if (annotation == null) {
            return;
        }

        final List<TypeElement> elements = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, Constants.AUTO_MAP_MAPPERS_FILE_NAME));

        elements.stream()
            .map(ele -> buildAutoMapMapperMetadata((TypeElement) ele))
            .filter(Objects::nonNull)
            .forEach(metadata -> {
                this.writeAutoMapperClassFile(metadata);
                addAdapterMapMethod(metadata);
            });

        adapterMapperGenerator.write(processingEnv, mapMethodMap.values(),
            AutoMapperProperties.getMapAdapterClassName());

        mapperConfigGenerator.write(processingEnv, AutoMapperProperties.getMapConfigClassName(),
            AutoMapperProperties.getMapAdapterClassName(), null);
    }

    private AutoMapperMetadata buildAutoMapMapperMetadata(TypeElement element) {
        if (element.getAnnotation(AutoMapMapper.class) == null) {
            return null;
        }
        ClassName source = ClassName.get("java.util", "Map");
        ClassName target = ClassName.get(element);
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
    }

    private void addAdapterMapMethod(AutoMapperMetadata metadata) {
        if (metadata == null) {
            return;
        }
        addAdapterMapMethod(metadata.getSourceClassName(), metadata.getTargetClassName(), metadata.mapperClass(),
            false);
        addAdapterMapMethod(ClassName.get("java.lang", "Object"), metadata.getTargetClassName(),
            metadata.mapperClass(), true);
    }

    private void loadMapperConfig(MapperConfig mapperConfig) {
        if (mapperConfig == null) {
            return;
        }
        AutoMapperProperties.setUnmappedSourcePolicy(mapperConfig.unmappedSourcePolicy());
        AutoMapperProperties.setUnmappedTargetPolicy(mapperConfig.unmappedTargetPolicy());
        AutoMapperProperties.setNullValueMappingStrategy(mapperConfig.nullValueMappingStrategy());
        AutoMapperProperties.setNullValuePropertyMappingStrategy(mapperConfig.nullValuePropertyMappingStrategy());
        AutoMapperProperties.setBuildMethod(mapperConfig.builder().buildMethod());
        AutoMapperProperties.setDisableBuilder(mapperConfig.builder().disableBuilder());
        if (StrUtil.isNotEmpty(mapperConfig.mapperPackage())) {
            AutoMapperProperties.setMapperPackage(mapperConfig.mapperPackage());
        }
        if (StrUtil.isNotEmpty(mapperConfig.adapterPackage())) {
            AutoMapperProperties.setAdapterPackage(mapperConfig.adapterPackage());
        }
        if (StrUtil.isNotEmpty(mapperConfig.adapterClassName())) {
            AutoMapperProperties.setAdapterClassName(mapperConfig.adapterClassName());
        }
        if (StrUtil.isNotEmpty(mapperConfig.cycleAvoidingAdapterClassName())) {
            AutoMapperProperties.setCycleAvoidingAdapterClassName(mapperConfig.cycleAvoidingAdapterClassName());
        }
        if (StrUtil.isNotEmpty(mapperConfig.mapAdapterClassName())) {
            AutoMapperProperties.setMapAdapterClassName(mapperConfig.mapAdapterClassName());
        }
        if (StrUtil.isNotEmpty(mapperConfig.autoConfigPackage())) {
            AutoMapperProperties.setAutoConfigPackage(mapperConfig.autoConfigPackage());
        }
        if (StrUtil.isNotEmpty(mapperConfig.autoMapperConfigClassName())) {
            AutoMapperProperties.setAutoMapperConfigClassName(mapperConfig.autoMapperConfigClassName());
        }
        if (StrUtil.isNotEmpty(mapperConfig.autoCycleAvoidingMapperConfigClassName())) {
            AutoMapperProperties.setAutoCycleAvoidingMapperConfigClassName(mapperConfig.autoCycleAvoidingMapperConfigClassName());
        }
        if (StrUtil.isNotEmpty(mapperConfig.autoMapMapperConfigClassName())) {
            AutoMapperProperties.setAutoMapMapperConfigClassName(mapperConfig.autoMapMapperConfigClassName());
        }
    }

    private void refreshProperties(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        final BuildCollator buildCollator = new BuildCollator(processingEnv, Constants.MAPPER_CONFIG_FILE_NAME);

        // load previous mapper config
        final List<TypeElement> typeElements = buildCollator.getRecords();
        if (CollectionUtil.isNotEmpty(typeElements)) {
            messager.printMessage(Diagnostic.Kind.NOTE,
                "The previous Mapper Config Class was read , class name : " + typeElements.get(0));
            loadMapperConfig(typeElements.get(0).getAnnotation(MapperConfig.class));
        }

        // annotation --> MapperConfig
        final TypeElement mapperConfigAnnotation =
            processingEnv.getElementUtils().getTypeElement(MAPPER_CONFIG_ANNOTATION);
        if (mapperConfigAnnotation != null) {
            final Optional<? extends Element> mapperConfigOptional =
                roundEnv.getElementsAnnotatedWith(mapperConfigAnnotation)
                    .stream()
                    .findFirst();
            if (mapperConfigOptional.isPresent()) {
                loadMapperConfig(mapperConfigOptional.get().getAnnotation(MapperConfig.class));
                // record
                buildCollator.writeTypeElements(CollectionUtil.newArrayList((TypeElement) mapperConfigOptional.get()));
            }
        }

        // special MapperConfig Class
        final String mapperConfigClass = processingEnv.getOptions().get(MAPPER_CONFIG_CLASS);
        if (StrUtil.isNotEmpty(mapperConfigClass)) {
            final TypeElement typeElement = processingEnv.getElementUtils().getTypeElement(mapperConfigClass);
            if (typeElement != null) {
                loadMapperConfig(typeElement.getAnnotation(MapperConfig.class));
            }
        }

        // annotation --> ComponentModelConfig
        annotations.stream()
            .filter(this::isComponentModelConfigAnnotation)
            .findFirst()
            .flatMap(annotation -> roundEnv.getElementsAnnotatedWith(annotation).stream().findFirst())
            .ifPresent(element -> {
                final ComponentModelConfig componentModelConfig = element.getAnnotation(ComponentModelConfig.class);
                String componentModelByAnnotation = componentModelConfig.componentModel();
                AutoMapperProperties.setComponentModel(componentModelByAnnotation);
            });
        // compilerArgs
        loadCompilerArgs();
    }

    private void loadCompilerArgs() {
        ProcessorOptions.optionConsumers().forEach((key, consumer) -> {
            final String value = processingEnv.getOptions().get(key);
            if (StrUtil.isNotEmpty(value)) {
                consumer.accept(value);
            }
        });
    }

    private void processAutoMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation, boolean cycleAvoiding) {
        if (annotation == null) {
            return;
        }
        final List<TypeElement> elements = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, Constants.AUTO_MAPPER_FILE_NAME));

        final List<AutoMapperMetadata> autoMapperMetadataList = elements
            .stream()
            .map(element -> buildAutoMapperMetadata(element, cycleAvoiding))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        mapperList.addAll(autoMapperMetadataList);
    }

    private void processAutoMappersAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation, boolean cycleAvoiding) {
        if (annotation == null) {
            return;
        }
        final List<AutoMapperMetadata> autoMapperMetadata = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, Constants.AUTO_MAPPERS_FILE_NAME))
            .stream()
            .map(typeElement -> buildAutoMapperMetadataByAutoMappers(typeElement, cycleAvoiding))
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
//            addAdapterMethod(metadata);
        });

        List<AbstractAdapterMethodMetadata> methodList = methodMap.values().stream()
            .filter(method -> !method.isCycleAvoiding())
            .collect(Collectors.toList());
        adapterMapperGenerator.write(processingEnv, methodList, AutoMapperProperties.getAdapterClassName());

        List<AbstractAdapterMethodMetadata> cycleAvoidingMethodList = methodMap.values().stream()
            .filter(method -> method.isCycleAvoiding())
            .collect(Collectors.toList());
        adapterMapperGenerator.write(processingEnv, cycleAvoidingMethodList, AutoMapperProperties.getCycleAvoidingAdapterClassName());

        mapperConfigGenerator.write(processingEnv, AutoMapperProperties.getConfigClassName(),
            AutoMapperProperties.getAdapterClassName(), customMapperList);
        mapperConfigGenerator.write(processingEnv, AutoMapperProperties.getCycleAvoidingConfigClassName(),
            AutoMapperProperties.getCycleAvoidingAdapterClassName(), customMapperList);
    }

    private AutoMapperMetadata reverseMapper(AutoMapperMetadata autoMapperMetadata) {
        AutoMapperMetadata reverseMapperMetadata = initAutoMapperMetadata(
            autoMapperMetadata.getTargetClassName(), autoMapperMetadata.getSourceClassName(), autoMapperMetadata.isCycleAvoiding());
        reverseMapperMetadata.setConvertGenerate(autoMapperMetadata.isReverseConvertGenerate());
        reverseMapperMetadata.setUsesClassNameList(autoMapperMetadata.getUsesClassNameList());
        reverseMapperMetadata.setImportsClassNameList(autoMapperMetadata.getImportsClassNameList());
        reverseMapperMetadata.setMapstructConfigClass(
            ClassName.get(AutoMapperProperties.getConfigPackage(), !autoMapperMetadata.isCycleAvoiding() ?
                AutoMapperProperties.getConfigClassName() : AutoMapperProperties.getCycleAvoidingConfigClassName()));
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
        reverseMapperMetadata.setCycleAvoiding(autoMapperMetadata.isCycleAvoiding());
        return reverseMapperMetadata;
    }

    private void writeAutoMapperClassFile(AutoMapperMetadata metadata) {
        String mapperPackage = metadata.mapperPackage();
        String mapperClassName = metadata.mapperName();
        try (final Writer writer = processingEnv.getFiler()
            .createSourceFile(mapperPackage + "." + mapperClassName)
            .openWriter()) {
            mapperGenerator.write(metadata, processingEnv, writer);
        } catch (IOException e) {
            processingEnv.getMessager()
                .printMessage(ERROR,
                    "Error while opening " + metadata.mapperName() + " output file: " + e.getMessage());
        }
    }

    private void addAdapterMethod(AutoMapperMetadata metadata) {
        if (metadata == null) {
            return;
        }
        AdapterMethodMetadata adapterMethodMetadata = AdapterMethodMetadata.newInstance(
            metadata.getSourceClassName(), metadata.getTargetClassName(), metadata.mapperClass(), metadata.isCycleAvoiding());
        methodMap.putIfAbsent(metadata.mapperName() + "." + adapterMethodMetadata.getMethodName(), adapterMethodMetadata);
    }

    private void addAdapterMapMethod(ClassName source, ClassName target, ClassName mapper, boolean objectConverter) {
        final AdapterMapMethodMetadata adapterMapMethodMetadata =
            new AdapterMapMethodMetadata(source, target, mapper, objectConverter);
        mapMethodMap.putIfAbsent(adapterMapMethodMetadata.getMethodName(), adapterMapMethodMetadata);
    }

    private AutoMapperMetadata initAutoMapperMetadata(ClassName source, ClassName target, boolean cycleAvoiding) {
        AutoMapperMetadata metadata = new AutoMapperMetadata();

        metadata.setSourceClassName(source);
        metadata.setTargetClassName(target);
        metadata.setSuperClass(ClassName.get("io.github.linpeilie", !cycleAvoiding ? "BaseMapper" : "BaseCycleAvoidingMapper"));
        metadata.setSuperGenerics(new ClassName[] {source, target});
        metadata.setMapstructConfigClass(
            ClassName.get(AutoMapperProperties.getConfigPackage(), !cycleAvoiding ?
                AutoMapperProperties.getConfigClassName() : AutoMapperProperties.getCycleAvoidingConfigClassName()));
        return metadata;
    }

    private List<AutoMapperMetadata> buildAutoMapperMetadataByAutoMappers(final Element ele, boolean cycleAvoiding) {
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
            .map(autoMapper -> buildAutoMapperMetadata(autoMapper, ele, cycleAvoiding))
            .collect(Collectors.toList());
    }

    private AutoMapperMetadata buildAutoMapperMetadata(final Element ele, boolean cycleAvoiding) {
        AutoMapper autoMapperAnnotation = ele.getAnnotation(AutoMapper.class);
        if (autoMapperAnnotation == null) {
            return null;
        }
        return buildAutoMapperMetadata(autoMapperAnnotation, ele, cycleAvoiding);
    }

    private boolean isTargetFieldMapping(ClassName target, AutoMappingMetadata mappingMetadata) {
        if (MAPPING_DEFAULT_TARGET.reflectionName().contentEquals(mappingMetadata.getTargetClass().reflectionName())) {
            return true;
        }
        if (target.reflectionName().contentEquals(mappingMetadata.getTargetClass().reflectionName())) {
            return true;
        }
        TypeElement targetTypeElement = classNameToTypeElement(target);
        Optional<TypeElement> superClass = getSuperClass(targetTypeElement);
        return superClass.filter(typeElement -> isTargetFieldMapping(ClassName.get(typeElement), mappingMetadata))
            .isPresent();
    }

    private Optional<TypeElement> getSuperClass(TypeElement ele) {
        TypeMirror superclass = ele.getSuperclass();
        if (superclass == null) {
            return Optional.empty();
        }
        if ("java.lang.Object".equals(superclass.toString())) {
            return Optional.empty();
        }
        if (ele.getQualifiedName().contentEquals(superclass.toString())) {
            return Optional.empty();
        }
        return Optional.of((TypeElement) processingEnv.getTypeUtils().asElement(superclass));
    }

    private AutoMapperMetadata buildAutoMapperMetadata(final AutoMapper autoMapper, final Element ele, boolean cycleAvoiding) {
        ClassName source = ClassName.get((TypeElement) ele);
        ClassName target = transToClassName(autoMapper::target);
        if (target == null) {
            return null;
        }
        List<ClassName> uses = transToClassNameList(autoMapper::uses);
        final List<ClassName> importsClassNameList = transToClassNameList(autoMapper::imports);
        List<AutoMappingMetadata> autoMappingMetadataList = buildFieldMappingMetadata((TypeElement) ele);
        autoMappingMetadataList.removeIf(mappingMetadata -> !isTargetFieldMapping(target, mappingMetadata));

        List<AutoMappingMetadata> reverseMappingMetadataList = buildFieldReverseMappingMetadata((TypeElement) ele);
        reverseMappingMetadataList.removeIf(mappingMetadata -> !isTargetFieldMapping(target, mappingMetadata));

        AutoMapperMetadata metadata = initAutoMapperMetadata(source, target, cycleAvoiding);

        metadata.setUsesClassNameList(uses);
        metadata.setImportsClassNameList(importsClassNameList);
        metadata.setFieldMappingList(autoMappingMetadataList);
        metadata.setFieldReverseMappingList(reverseMappingMetadataList);
        metadata.setConvertGenerate(autoMapper.convertGenerate());
        metadata.setReverseConvertGenerate(autoMapper.reverseConvertGenerate());
        metadata.setCycleAvoiding(cycleAvoiding);

        addMapper(metadata);

        return metadata;
    }

    private List<AutoMappingMetadata> buildFieldReverseMappingMetadata(final TypeElement ele) {
        List<AutoMappingMetadata> list = new ArrayList<>();
        if (!ele.getKind().isClass() && !ele.getKind().isInterface()) {
            return list;
        }
        for (Element field : ele.getEnclosedElements()) {
            if (field.getKind() != ElementKind.FIELD && field.getKind() != ElementKind.METHOD) {
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

        // super class
        getSuperClass(ele)
            .ifPresent(superClass -> list.addAll(buildFieldReverseMappingMetadata(superClass)));

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
        metadata.setDefaultExpression(reverseAutoMapping.defaultExpression());
        metadata.setConditionExpression(reverseAutoMapping.conditionExpression());
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

        if (!autoMapperEle.getKind().isClass() && !autoMapperEle.getKind().isInterface()) {
            return list;
        }

        for (Element ele : autoMapperEle.getEnclosedElements()) {
            if (ele.getKind() != ElementKind.FIELD && ele.getKind() != ElementKind.METHOD) {
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

        // add super class AutoMappings
        getSuperClass(autoMapperEle)
            .ifPresent(superClass -> list.addAll(buildFieldMappingMetadata(superClass)));

        list.removeIf(Objects::isNull);
        return list;
    }

    private AutoMappingMetadata buildAutoMappingMetadata(AutoMapping autoMapping, Element ele, TypeElement type) {
        ClassName targetClass = transToClassName(autoMapping::targetClass);
        if (targetClass == null) {
            return null;
        }

        AutoMappingMetadata metadata = new AutoMappingMetadata();
        String elementName = ele.getSimpleName().toString();

        if (ele.getKind() == ElementKind.METHOD) {
            elementName = ObjectUtil.defaultIfBlank(StrUtil.getGeneralField(elementName), elementName);
        }

        if (StrUtil.isNotEmpty(autoMapping.source())) {
            metadata.setSource(autoMapping.source());
        } else {
            metadata.setSource(elementName);
        }
        if (StrUtil.isNotEmpty(autoMapping.target())) {
            metadata.setTarget(autoMapping.target());
        } else {
            metadata.setTarget(elementName);
        }
        metadata.setTargetClass(targetClass);
        metadata.setDefaultValue(autoMapping.defaultValue());
        metadata.setIgnore(autoMapping.ignore());
        metadata.setExpression(autoMapping.expression());
        metadata.setDefaultExpression(autoMapping.defaultExpression());
        metadata.setConditionExpression(autoMapping.conditionExpression());
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

    private TypeElement classNameToTypeElement(ClassName className) {
        String classNameString = className.toString();
        return processingEnv.getElementUtils().getTypeElement(classNameString);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
