package io.github.linpeilie.processor;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import io.github.linpeilie.ComponentModelConstant;
import io.github.linpeilie.annotations.AutoEnumMapper;
import io.github.linpeilie.annotations.AutoMapMapper;
import io.github.linpeilie.annotations.ComponentModelConfig;
import io.github.linpeilie.processor.gem.AutoMapperGem;
import io.github.linpeilie.processor.gem.AutoMappersGem;
import io.github.linpeilie.processor.gem.AutoMappingGem;
import io.github.linpeilie.processor.gem.AutoMappingsGem;
import io.github.linpeilie.processor.gem.BuilderGem;
import io.github.linpeilie.processor.gem.MapperConfigGem;
import io.github.linpeilie.processor.gem.ReverseAutoMappingGem;
import io.github.linpeilie.processor.gem.ReverseAutoMappingsGem;
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
import io.github.linpeilie.processor.utils.ExceptionUtils;
import io.github.linpeilie.processor.utils.MapperUtils;
import io.github.linpeilie.processor.utils.ObjectUtils;
import io.github.linpeilie.utils.CollectionUtils;
import io.github.linpeilie.utils.StrUtil;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
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

import static io.github.linpeilie.processor.ProcessorOptions.ADAPTER_CLASS_NAME;
import static io.github.linpeilie.processor.ProcessorOptions.ADAPTER_PACKAGE;
import static io.github.linpeilie.processor.ProcessorOptions.BUILDER_BUILD_METHOD;
import static io.github.linpeilie.processor.ProcessorOptions.BUILDER_DISABLE_BUILDER;
import static io.github.linpeilie.processor.ProcessorOptions.COLLECTION_MAPPING_STRATEGY;
import static io.github.linpeilie.processor.ProcessorOptions.MAPPER_CONFIG_CLASS;
import static io.github.linpeilie.processor.ProcessorOptions.MAPPER_PACKAGE;
import static io.github.linpeilie.processor.ProcessorOptions.MAP_ADAPTER_CLASS_NAME;
import static io.github.linpeilie.processor.ProcessorOptions.NULL_VALUE_CHECK_STRATEGY;
import static io.github.linpeilie.processor.ProcessorOptions.NULL_VALUE_ITERABLE_MAPPING_STRATEGY;
import static io.github.linpeilie.processor.ProcessorOptions.NULL_VALUE_MAPPING_STRATEGY;
import static io.github.linpeilie.processor.ProcessorOptions.NULL_VALUE_MAP_MAPPING_STRATEGY;
import static io.github.linpeilie.processor.ProcessorOptions.NULL_VALUE_PROPERTY_MAPPING_STRATEGY;
import static io.github.linpeilie.processor.ProcessorOptions.SUPPRESS_TIMESTAMP_IN_GENERATED;
import static io.github.linpeilie.processor.ProcessorOptions.TYPE_CONVERSION_POLICY;
import static io.github.linpeilie.processor.ProcessorOptions.UNMAPPED_SOURCE_POLICY;
import static io.github.linpeilie.processor.ProcessorOptions.UNMAPPED_TARGET_POLICY;
import static javax.tools.Diagnostic.Kind.ERROR;

@SupportedAnnotationTypes({
    ContextConstants.Annotations.autoMapper,
    ContextConstants.Annotations.autoMappers,
    ContextConstants.Annotations.autoMapMapper,
    ContextConstants.Annotations.autoEnumMapper,
    ContextConstants.Annotations.mapperConfig,
    ContextConstants.Annotations.componentModel,
    ContextConstants.Annotations.mapper})
@SupportedOptions({MAPPER_CONFIG_CLASS, MAPPER_PACKAGE, UNMAPPED_SOURCE_POLICY, UNMAPPED_TARGET_POLICY,
                   NULL_VALUE_MAPPING_STRATEGY, NULL_VALUE_PROPERTY_MAPPING_STRATEGY, BUILDER_BUILD_METHOD,
                   BUILDER_DISABLE_BUILDER, ADAPTER_PACKAGE, ADAPTER_CLASS_NAME, MAP_ADAPTER_CLASS_NAME,
                   TYPE_CONVERSION_POLICY, COLLECTION_MAPPING_STRATEGY, NULL_VALUE_ITERABLE_MAPPING_STRATEGY,
                   NULL_VALUE_MAP_MAPPING_STRATEGY, NULL_VALUE_CHECK_STRATEGY, SUPPRESS_TIMESTAMP_IN_GENERATED})
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

    private static final Map<String, Integer> AUTO_MAPPER_INDEX = new HashMap<>();

    private final Map<String, List<ClassName>> typeRelationMappers = new HashMap<>();

    private Messager messager;

    public AutoMapperProcessor() {
        this.mapperGenerator = new AutoMapperGenerator();
        this.mapperConfigGenerator = new MapperConfigGenerator();
    }

    private boolean isAutoMapperAnnotation(TypeElement annotation) {
        return ContextConstants.Annotations.autoMapper.contentEquals(annotation.getQualifiedName());
    }

    private boolean isAutoMappersAnnotation(TypeElement annotation) {
        return ContextConstants.Annotations.autoMappers.contentEquals(annotation.getQualifiedName());
    }

    private boolean isAutoMapMapperAnnotation(TypeElement annotation) {
        return ContextConstants.Annotations.autoMapMapper.contentEquals(annotation.getQualifiedName());
    }

    private boolean isAutoEnumMapperAnnotation(TypeElement annotation) {
        return ContextConstants.Annotations.autoEnumMapper.contentEquals(annotation.getQualifiedName());
    }

    private boolean isMapperConfigAnnotation(TypeElement annotation) {
        return ContextConstants.Annotations.mapperConfig.contentEquals(annotation.getQualifiedName());
    }

    private boolean isComponentModelConfigAnnotation(TypeElement annotation) {
        return ContextConstants.Annotations.componentModel.contentEquals(annotation.getQualifiedName());
    }

    @Override
    public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        try {
            doProcess(annotations, roundEnv);
        } catch (Exception e) {
            messager.printMessage(ERROR, ExceptionUtils.getStackTrace(e));
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
        this.adapterMapperGenerator = AdapterMapperGeneratorFactory.instance(AutoMapperProperties.getComponentModel());

        // AutoMapMapper
        final TypeElement autoMapMapperAnnotation =
            processingEnv.getElementUtils().getTypeElement(ContextConstants.Annotations.autoMapMapper);
        processAutoMapMapperAnnotation(roundEnv, autoMapMapperAnnotation);

        // AutoEnumMapper
        final TypeElement autoEnumMapperAnnotation =
            processingEnv.getElementUtils().getTypeElement(ContextConstants.Annotations.autoEnumMapper);
        processAutoEnumMapperAnnotation(roundEnv, autoEnumMapperAnnotation);

        // AutoMapper
        final TypeElement autoMapperAnnotation =
            processingEnv.getElementUtils().getTypeElement(ContextConstants.Annotations.autoMapper);
        processAutoMapperAnnotation(roundEnv, autoMapperAnnotation);

        // AutoMappers
        final TypeElement autoMappersAnnotation =
            processingEnv.getElementUtils().getTypeElement(ContextConstants.Annotations.autoMappers);
        processAutoMappersAnnotation(roundEnv, autoMappersAnnotation);

        // custom mapper
        final TypeElement mapperAnnotation =
            processingEnv.getElementUtils().getTypeElement(ContextConstants.Mapper.qualifiedClassName);
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
            new BuildCollator(processingEnv, ContextConstants.MetaInf.mappers));

        elements.forEach(element -> customMapperList.add(element.asType()));
    }

    private void processAutoEnumMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        if (annotation == null) {
            return;
        }
        final List<TypeElement> elements = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, ContextConstants.MetaInf.enumMappers));
        elements.stream()
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
        if (autoEnumMapperMetadata == null) {
            return;
        }
        // toValue
        final AdapterEnumMethodMetadata toValueProxyMethod =
            new AdapterEnumMethodMetadata(autoEnumMapperMetadata.getSourceClassName(),
                ClassName.get(autoEnumMapperMetadata.mapperPackage(), autoEnumMapperMetadata.mapperName()),
                autoEnumMapperMetadata.toValueMethodName(), autoEnumMapperMetadata.getReturnType());
        // toEnum
        final AdapterEnumMethodMetadata toEnumProxyMethod =
            new AdapterEnumMethodMetadata(autoEnumMapperMetadata.getReturnType(),
                ClassName.get(autoEnumMapperMetadata.mapperPackage(), autoEnumMapperMetadata.mapperName()),
                autoEnumMapperMetadata.toEnumMethodName(), autoEnumMapperMetadata.getSourceClassName());
        methodMap.putIfAbsent(
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
            boolean isGetter = StrUtil.equalsIgnoreCase(ele.getSimpleName(), "get" + enumCodeFieldName) ||
                               StrUtil.equalsIgnoreCase(ele.getSimpleName(), "is" + enumCodeFieldName);
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
        if (annotation == null) {
            return;
        }

        final List<TypeElement> elements = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, ContextConstants.MetaInf.autoMapMappers));

        elements.stream()
            .map(this::buildAutoMapMapperMetadata)
            .filter(Objects::nonNull)
            .forEach(metadata -> {
                this.writeAutoMapperClassFile(metadata);
                addAdapterMapMethod(metadata);
            });

        if (mapMethodMap.isEmpty()) {
            return;
        }

        adapterMapperGenerator.write(processingEnv,
            mapMethodMap.values(),
            AutoMapperProperties.getMapAdapterClassName(),
            false);

        mapperConfigGenerator.write(processingEnv, AutoMapperProperties.getMapConfigClassName(),
            AutoMapperProperties.getMapAdapterClassName(), null);
    }

    private AutoMapperMetadata buildAutoMapMapperMetadata(TypeElement element) {
        if (element.getAnnotation(AutoMapMapper.class) == null) {
            return null;
        }
        ClassName source = ClassName.get(ContextConstants.Map.packageName, ContextConstants.Map.className);
        ClassName target = ClassName.get(element);
        List<ClassName> uses = Collections.singletonList(
            ClassName.get(ContextConstants.MapObjectConvert.packageName, ContextConstants.MapObjectConvert.className));

        final AutoMapperMetadata autoMapperMetadata = new AutoMapMapperMetadata(source, target);
        autoMapperMetadata.setUsesClassNameList(uses);
        autoMapperMetadata.setSuperClass(
            ClassName.get(ContextConstants.BaseMapMapper.packageName, ContextConstants.BaseMapMapper.className));
        autoMapperMetadata.setSuperGenerics(new ClassName[] {target});
        autoMapperMetadata.setMapstructConfigClass(
            ClassName.get(AutoMapperProperties.getConfigPackage(), AutoMapperProperties.getMapConfigClassName()));
        return autoMapperMetadata;
    }

    private void addAdapterMapMethod(AutoMapperMetadata metadata) {
        if (metadata == null) {
            return;
        }
        addAdapterMapMethod(metadata.getSourceClassName(), metadata.getTargetClassName(), metadata.mapperClass(),
            false);
    }

    private void loadMapperConfig(MapperConfigGem mapperConfigGem) {
        if (mapperConfigGem == null || !mapperConfigGem.isValid()) {
            return;
        }
        if (mapperConfigGem.mapperPackage().hasValue()) {
            AutoMapperProperties.setMapperPackage(mapperConfigGem.mapperPackage().get());
        }
        AutoMapperProperties.setUnmappedSourcePolicy(mapperConfigGem.unmappedSourcePolicy().getValue());
        // 重定义 MapStruct 中 unmappedTargetPolicy 的默认值 WARN ---> IGNORE
        AutoMapperProperties.setUnmappedTargetPolicy(mapperConfigGem.unmappedTargetPolicy().get());
        AutoMapperProperties.setTypeConversionPolicy(mapperConfigGem.typeConversionPolicy().getValue());
        AutoMapperProperties.setCollectionMappingStrategy(mapperConfigGem.collectionMappingStrategy().getValue());
        AutoMapperProperties.setNullValueMappingStrategy(mapperConfigGem.nullValueMappingStrategy().getValue());
        AutoMapperProperties.setNullValueIterableMappingStrategy(
            mapperConfigGem.nullValueIterableMappingStrategy().getValue());
        AutoMapperProperties.setNullValueMapMappingStrategy(mapperConfigGem.nullValueMapMappingStrategy().getValue());
        AutoMapperProperties.setNullValuePropertyMappingStrategy(
            mapperConfigGem.nullValuePropertyMappingStrategy().getValue());
        AutoMapperProperties.setNullValueCheckStrategy(mapperConfigGem.nullValueCheckStrategy().getValue());
        if (mapperConfigGem.mappingControl().hasValue()) {
            AutoMapperProperties.setMappingControl(transToClassName(mapperConfigGem.mappingControl().get()));
        }
        if (mapperConfigGem.unexpectedValueMappingException().hasValue()) {
            AutoMapperProperties.setUnexpectedValueMappingException(
                transToClassName(mapperConfigGem.unexpectedValueMappingException().get()));
        }
        AutoMapperProperties.setSuppressTimestampInGenerated(mapperConfigGem.suppressTimestampInGenerated().getValue());
        BuilderGem builderGem = mapperConfigGem.builder().get();
        if (builderGem.buildMethod().hasValue()) {
            AutoMapperProperties.setBuildMethod(builderGem.buildMethod().get());
        }
        AutoMapperProperties.setDisableBuilder(builderGem.disableBuilder().get());
        if (mapperConfigGem.adapterPackage().hasValue()) {
            AutoMapperProperties.setAdapterPackage(mapperConfigGem.adapterPackage().get());
        }
        if (mapperConfigGem.adapterClassName().hasValue()) {
            AutoMapperProperties.setAdapterClassName(mapperConfigGem.adapterClassName().get());
        }
        if (mapperConfigGem.mapAdapterClassName().hasValue()) {
            AutoMapperProperties.setMapAdapterClassName(mapperConfigGem.mapAdapterClassName().get());
        }
        if (mapperConfigGem.autoConfigPackage().hasValue()) {
            AutoMapperProperties.setAutoConfigPackage(mapperConfigGem.autoConfigPackage().get());
        }
        if (mapperConfigGem.autoMapperConfigClassName().hasValue()) {
            AutoMapperProperties.setAutoMapperConfigClassName(mapperConfigGem.autoMapperConfigClassName().get());
        }
        if (mapperConfigGem.autoMapMapperConfigClassName().hasValue()) {
            AutoMapperProperties.setAutoMapMapperConfigClassName(mapperConfigGem.autoMapMapperConfigClassName().get());
        }
    }

    private void refreshProperties(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        final BuildCollator buildCollator = new BuildCollator(processingEnv, ContextConstants.MetaInf.mapperConfig);

        // load previous mapper config
        final List<TypeElement> typeElements = buildCollator.getRecords();
        if (CollectionUtils.isNotEmpty(typeElements)) {
            messager.printMessage(Diagnostic.Kind.NOTE,
                "The previous Mapper Config Class was read , class name : " + typeElements.get(0));
            loadMapperConfig(MapperConfigGem.instanceOn(typeElements.get(0)));
        }

        // annotation --> MapperConfig
        final TypeElement mapperConfigAnnotation =
            processingEnv.getElementUtils().getTypeElement(ContextConstants.Annotations.mapperConfig);
        if (mapperConfigAnnotation != null) {
            final Optional<? extends Element> mapperConfigOptional =
                roundEnv.getElementsAnnotatedWith(mapperConfigAnnotation).stream().findFirst();
            if (mapperConfigOptional.isPresent()) {
                loadMapperConfig(MapperConfigGem.instanceOn(mapperConfigOptional.get()));
                // record
                buildCollator.writeTypeElements(Collections.singletonList((TypeElement) mapperConfigOptional.get()));
            }
        }

        // special MapperConfig Class
        final String mapperConfigClass = processingEnv.getOptions().get(MAPPER_CONFIG_CLASS);
        if (StrUtil.isNotEmpty(mapperConfigClass)) {
            final TypeElement typeElement = processingEnv.getElementUtils().getTypeElement(mapperConfigClass);
            if (typeElement != null) {
                loadMapperConfig(MapperConfigGem.instanceOn(typeElement));
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

    private void processAutoMapperAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        if (annotation == null) {
            return;
        }
        final List<TypeElement> elements = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, ContextConstants.MetaInf.autoMapper));

        final List<AutoMapperMetadata> autoMapperMetadataList =
            elements.stream().map(this::buildAutoMapperMetadata).filter(Objects::nonNull).collect(Collectors.toList());

        mapperList.addAll(autoMapperMetadataList);
    }

    private void processAutoMappersAnnotation(final RoundEnvironment roundEnv, final TypeElement annotation) {
        if (annotation == null) {
            return;
        }
        final List<AutoMapperMetadata> autoMapperMetadata = getElementAndMergeHistory(roundEnv, annotation,
            new BuildCollator(processingEnv, ContextConstants.MetaInf.autoMappers)).stream()
            .map(this::buildAutoMapperMetadataByAutoMappers)
            .filter(Objects::nonNull)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        mapperList.addAll(autoMapperMetadata);
    }

    private List<AutoMapperMetadata> generateReverseConverters() {
        List<AutoMapperMetadata> reverseMapperMetadataList = new ArrayList<>();

        mapperList.forEach(autoMapperMetadata -> {
            if (!autoMapperMetadata.isReverseConvertGenerate()) {
                return;
            }
            boolean defineReverseMapping = CollectionUtils.isNotEmpty(autoMapperMetadata.getFieldReverseMappingList());
            final AutoMapperMetadata reverseMapperMetadata = reverseMapper(autoMapperMetadata);
            if (defineReverseMapping) {
                addMapper(reverseMapperMetadata, true);
            } else if (!addMapper(reverseMapperMetadata, false)) {
                return;
            }
            reverseMapperMetadataList.add(reverseMapperMetadata);
        });

        return reverseMapperMetadataList;
    }

    private void typeRelationMapper(AutoMapperMetadata metadata) {
        String source = metadata.getSourceClassName().reflectionName();
        if (!typeRelationMappers.containsKey(source)) {
            typeRelationMappers.put(source, new ArrayList<>());
        }
        typeRelationMappers.get(source).add(metadata.mapperClass());

        String target = metadata.getTargetClassName().reflectionName();
        if (!typeRelationMappers.containsKey(target)) {
            typeRelationMappers.put(target, new ArrayList<>());
        }
        typeRelationMappers.get(target).add(metadata.mapperClass());
    }

    private void generateMapper() {
        mapperList.addAll(generateReverseConverters());

        mapperList.removeIf(metadata -> !metadata.isConvertGenerate());

        // 兼容同模块下，同名不同包的场景
        mapperList.forEach(metadata -> {
            String mapperName = metadata.mapperName();
            // 同名类时，增加后缀
            Integer index = AUTO_MAPPER_INDEX.getOrDefault(mapperName, 0);
            if (index > 0) {
                mapperName = mapperName + "__" + index;
            }
            AUTO_MAPPER_INDEX.put(metadata.mapperName(), ++index);
            metadata.setMapperName(mapperName);
        });

        mapperList.forEach(metadata -> {
            if (metadata.isCycleAvoiding()) {
                addAdapterMethod(metadata);
            } else {
                typeRelationMapper(metadata);
            }
        });

        // import dependency
        mapperList.forEach(metadata -> {
            Set<TypeName> dependencies = metadata.getDependencies();
            if (CollectionUtils.isNotEmpty(dependencies)) {
                List<ClassName> dependencyMappers = dependencies.stream().map(dependency ->
                    typeRelationMappers.get(dependency.toString())
                ).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());

                if (CollectionUtils.isNotEmpty(dependencyMappers)) {
                    metadata.addUseList(dependencyMappers);
                }
            }
            // source
            List<ClassName> sourceDependencies =
                typeRelationMappers.get(metadata.getSourceClassName().reflectionName());

            if (CollectionUtils.isNotEmpty(sourceDependencies)) {
                sourceDependencies.removeIf(
                    sourceDependency -> sourceDependency.reflectionName().equals(metadata.mapperName()));
                metadata.addUseList(sourceDependencies);
            }
        });

        mapperList.forEach(this::writeAutoMapperClassFile);

        adapterMapperGenerator.write(processingEnv,
            methodMap.values(),
            AutoMapperProperties.getAdapterClassName(),
            false);

        mapperConfigGenerator.write(processingEnv,
            AutoMapperProperties.getConfigClassName(),
            AutoMapperProperties.getAdapterClassName(),
            customMapperList);

        boolean needCycleAvoiding = methodMap.values().stream().anyMatch(
            AbstractAdapterMethodMetadata::needCycleAvoiding);

        if (needCycleAvoiding) {
            adapterMapperGenerator.write(processingEnv,
                methodMap.values(),
                AutoMapperProperties.getCycleAvoidingAdapterClassName(),
                true);
            mapperConfigGenerator.write(processingEnv,
                AutoMapperProperties.getCycleAvoidingConfigClassName(),
                AutoMapperProperties.getCycleAvoidingAdapterClassName(),
                customMapperList);
        }
    }

    private Set<TypeName> listDependencies(TypeElement autoMapperEle) {
        Set<TypeName> set = new HashSet<>();

        if (!autoMapperEle.getKind().isClass() && !autoMapperEle.getKind().isInterface()) {
            return set;
        }

        for (Element ele : autoMapperEle.getEnclosedElements()) {
            if (ele.getKind() != ElementKind.FIELD) {
                continue;
            }
            TypeName typeName = ClassName.get(ele.asType());
            if (typeName instanceof ArrayTypeName) {
                ArrayTypeName arrayTypeName = (ArrayTypeName) typeName;
                typeName = arrayTypeName.componentType;
            } else if (typeName instanceof ParameterizedTypeName) {
                ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
                List<TypeName> typeArguments = parameterizedTypeName.typeArguments;
                set.addAll(typeArguments);
                continue;
            }
            set.add(typeName);
        }

        // add super class dependencies
        getSuperClass(autoMapperEle).ifPresent(superClass -> set.addAll(listDependencies(superClass)));

        set.removeIf(ele -> {
            if (ele == null) {
                return true;
            }
            try {
                if (ele.box().isBoxedPrimitive()) {
                    return true;
                }
            } catch (Exception e) {
                // ignore
            }
            return false;
        });
        return set;
    }

    private AutoMapperMetadata reverseMapper(AutoMapperMetadata autoMapperMetadata) {
        AutoMapperMetadata reverseMapperMetadata =
            initAutoMapperMetadata(autoMapperMetadata.getTargetClassName(),
                autoMapperMetadata.getSourceClassName(),
                autoMapperMetadata.isCycleAvoiding());
        reverseMapperMetadata.setConvertGenerate(autoMapperMetadata.isReverseConvertGenerate());
        reverseMapperMetadata.setUsesClassNameList(autoMapperMetadata.getUsesClassNameList());
        reverseMapperMetadata.setImportsClassNameList(autoMapperMetadata.getImportsClassNameList());
        reverseMapperMetadata.setCycleAvoiding(autoMapperMetadata.isCycleAvoiding());
        if (reverseMapperMetadata.isCycleAvoiding()) {
            reverseMapperMetadata.setSuperClass(ClassName.get(ContextConstants.BaseCycleAvoidingMapper.packageName,
                ContextConstants.BaseCycleAvoidingMapper.className));
        } else {
            reverseMapperMetadata.setSuperClass(
                ClassName.get(ContextConstants.BaseMapper.packageName, ContextConstants.BaseMapper.className));
        }
        reverseMapperMetadata.setUnmappedSourcePolicy(autoMapperMetadata.getUnmappedSourcePolicy());
        reverseMapperMetadata.setUnmappedTargetPolicy(autoMapperMetadata.getUnmappedTargetPolicy());
        reverseMapperMetadata.setTypeConversionPolicy(autoMapperMetadata.getTypeConversionPolicy());
        reverseMapperMetadata.setCollectionMappingStrategy(autoMapperMetadata.getCollectionMappingStrategy());
        reverseMapperMetadata.setNullValueMappingStrategy(autoMapperMetadata.getNullValueMappingStrategy());
        reverseMapperMetadata.setNullValueIterableMappingStrategy(
            autoMapperMetadata.getNullValueIterableMappingStrategy());
        reverseMapperMetadata.setNullValuePropertyMappingStrategy(
            autoMapperMetadata.getNullValuePropertyMappingStrategy());
        reverseMapperMetadata.setNullValueCheckStrategy(autoMapperMetadata.getNullValueCheckStrategy());
        reverseMapperMetadata.setMappingControl(autoMapperMetadata.getMappingControl());
        reverseMapperMetadata.setMapperNameSuffix(autoMapperMetadata.getMapperNameSuffix());

        List<AutoMappingMetadata> fieldReverseMappingList = autoMapperMetadata.getFieldReverseMappingList();
        if (CollectionUtils.isEmpty(fieldReverseMappingList)) {
            // 默认的规则
            Map<String, AutoMappingMetadata> autoMappingMap =
                autoMapperMetadata.getFieldMappingList().stream()
                    .filter(AutoMappingMetadata::getReverseConvertGenerate)
                    .map(fieldMapping -> {
                        final AutoMappingMetadata autoMappingMetadata = new AutoMappingMetadata();
                        autoMappingMetadata.setSource(fieldMapping.getTarget());
                        autoMappingMetadata.setTarget(fieldMapping.getSource());
                        return autoMappingMetadata;
                    }).collect(Collectors.toMap(AutoMappingMetadata::getTarget, Function.identity(), (a, b) -> a));
            fieldReverseMappingList = new ArrayList<>(autoMappingMap.values());
        }
        reverseMapperMetadata.setFieldMappingList(fieldReverseMappingList);
        return reverseMapperMetadata;
    }

    private void writeAutoMapperClassFile(AutoMapperMetadata metadata) {
        mapperGenerator.write(metadata, processingEnv);
    }

    private void addAdapterMethod(AutoMapperMetadata metadata) {
        if (metadata == null) {
            return;
        }
        AdapterMethodMetadata adapterMethodMetadata =
            AdapterMethodMetadata.newInstance(metadata.getSourceClassName(), metadata.getTargetClassName(),
                metadata.mapperClass(), metadata.isCycleAvoiding());
        methodMap.putIfAbsent(adapterMethodMetadata.getMethodName(), adapterMethodMetadata);
    }

    private void addAdapterMapMethod(ClassName source, ClassName target, ClassName mapper, boolean objectConverter) {
        final AdapterMapMethodMetadata adapterMapMethodMetadata =
            new AdapterMapMethodMetadata(source, target, mapper, objectConverter);
        mapMethodMap.putIfAbsent(adapterMapMethodMetadata.getMethodName(), adapterMapMethodMetadata);
    }

    private AutoMapperMetadata initAutoMapperMetadata(ClassName source, ClassName target, boolean cycleAvoiding) {
        AutoMapperMetadata metadata = new AutoMapperMetadata(source, target);

        metadata.setSuperGenerics(new ClassName[] {source, target});
        ClassName mapStructConfigClass;
        if (cycleAvoiding) {
            mapStructConfigClass = ClassName.get(AutoMapperProperties.getConfigPackage(),
                AutoMapperProperties.getCycleAvoidingConfigClassName());
        } else {
            mapStructConfigClass = ClassName.get(AutoMapperProperties.getConfigPackage(),
                AutoMapperProperties.getConfigClassName());
        }
        metadata.setMapstructConfigClass(mapStructConfigClass);
        return metadata;
    }

    private List<AutoMapperMetadata> buildAutoMapperMetadataByAutoMappers(final Element ele) {
        AutoMappersGem autoMappersGem = AutoMappersGem.instanceOn(ele);
        if (autoMappersGem == null || !autoMappersGem.isValid()) {
            return null;
        }

        Set<String> targetClassNames = new HashSet<>();

        return autoMappersGem.value().getValue().stream().filter(autoMapperGem -> {
                if (autoMapperGem == null || !autoMapperGem.isValid()) {
                    return false;
                }
                TypeMirror target = autoMapperGem.target().getValue();
                return targetClassNames.add(target.toString());
            }).map(autoMapperGem -> buildAutoMapperMetadata(autoMapperGem, ele))
            .collect(Collectors.toList());
    }

    private AutoMapperMetadata buildAutoMapperMetadata(final Element ele) {
        AutoMapperGem autoMapperGem = AutoMapperGem.instanceOn(ele);
        if (autoMapperGem == null || !autoMapperGem.isValid()) {
            return null;
        }

        return buildAutoMapperMetadata(autoMapperGem, ele);
    }

    private List<AutoMappingMetadata> matchTargetFieldMapping(List<AutoMappingMetadata> autoMappingMetadataList,
        ClassName target) {
        // 先删除非当面目标类或父类的
        autoMappingMetadataList.removeIf(mappingMetadata -> !isTargetFieldMapping(target, mappingMetadata));
        // 适配目标属性同时配置了目标类及目标类父类的场景
        Map<String, List<AutoMappingMetadata>> targetFieldAutoMappingMap = autoMappingMetadataList.stream()
            .collect(Collectors.groupingBy(AutoMappingMetadata::getTarget));

        List<AutoMappingMetadata> result = new ArrayList<>();

        targetFieldAutoMappingMap.forEach((key, list) -> {
            // 只有一个场景
            if (list.size() == 1) {
                result.add(list.get(0));
                return;
            }
            // 有多个时，先匹配目标类型
            ClassName targetClassName = target;
            while (targetClassName != null) {
                ClassName finalTargetClassName = targetClassName;
                List<AutoMappingMetadata> matchedList = list.stream()
                    .filter(mappingMetadata -> mappingMetadata.getTargetClass()
                        .reflectionName()
                        .equals(finalTargetClassName.reflectionName()))
                    .collect(Collectors.toList());
                if (!matchedList.isEmpty()) {
                    result.addAll(matchedList);
                    return;
                }
                targetClassName = getSuperClass(classNameToTypeElement(targetClassName))
                    .map(ClassName::get)
                    .orElse(null);
            }
            // 如果没有匹配到，则添加所有的
            result.addAll(list);
        });

        return result;
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

    private AutoMapperMetadata buildAutoMapperMetadata(final AutoMapperGem autoMapperGem, final Element ele) {
        ClassName source = ClassName.get((TypeElement) ele);
        ClassName target = (ClassName) ClassName.get(autoMapperGem.target().getValue());
        if (target == null) {
            return null;
        }

        List<AutoMappingMetadata> autoMappingMetadataList =
            matchTargetFieldMapping(buildFieldMappingMetadata((TypeElement) ele), target);

        List<AutoMappingMetadata> reverseMappingMetadataList =
            matchTargetFieldMapping(buildFieldReverseMappingMetadata((TypeElement) ele), target);

        AutoMapperMetadata metadata = initAutoMapperMetadata(source, target, autoMapperGem.cycleAvoiding().get());

        List<ClassName> usesClassNameList = transToClassNameList(autoMapperGem.uses().get());
        List<ClassName> useEnumClassNameList = transToClassNameList(autoMapperGem.useEnums().get()).stream()
            .map(enumClass -> ClassName.get(MapperUtils.getMapperPackage(enumClass.packageName()),
                MapperUtils.getEnumMapperClassName(enumClass.simpleName())))
            .collect(Collectors.toList());

        // dependencies
        Set<TypeName> dependencies = listDependencies((TypeElement) ele);

        usesClassNameList.addAll(useEnumClassNameList);

        metadata.setUsesClassNameList(usesClassNameList);
        metadata.setImportsClassNameList(transToClassNameList(autoMapperGem.imports().get()));
        metadata.setFieldMappingList(autoMappingMetadataList);
        metadata.setFieldReverseMappingList(reverseMappingMetadataList);
        metadata.setConvertGenerate(autoMapperGem.convertGenerate().get());
        metadata.setReverseConvertGenerate(autoMapperGem.reverseConvertGenerate().get());
        metadata.setCycleAvoiding(autoMapperGem.cycleAvoiding().get());
        if (metadata.isCycleAvoiding()) {
            metadata.setSuperClass(ClassName.get(ContextConstants.BaseCycleAvoidingMapper.packageName,
                ContextConstants.BaseCycleAvoidingMapper.className));
        } else {
            metadata.setSuperClass(
                ClassName.get(ContextConstants.BaseMapper.packageName, ContextConstants.BaseMapper.className));
        }
        metadata.setUnmappedSourcePolicy(autoMapperGem.unmappedSourcePolicy().getValue());
        metadata.setUnmappedTargetPolicy(autoMapperGem.unmappedTargetPolicy().getValue());
        metadata.setTypeConversionPolicy(autoMapperGem.typeConversionPolicy().getValue());
        metadata.setCollectionMappingStrategy(autoMapperGem.collectionMappingStrategy().getValue());
        metadata.setNullValueMappingStrategy(autoMapperGem.nullValueMappingStrategy().getValue());
        metadata.setNullValueIterableMappingStrategy(autoMapperGem.nullValueIterableMappingStrategy().getValue());
        metadata.setNullValuePropertyMappingStrategy(autoMapperGem.nullValuePropertyMappingStrategy().getValue());
        metadata.setNullValueCheckStrategy(autoMapperGem.nullValueCheckStrategy().getValue());
        metadata.setMappingControl(transToClassName(autoMapperGem.mappingControl().getValue()));
        if (StrUtil.isNotEmpty(autoMapperGem.mapperName().getValue())) {
            metadata.setMapperName(autoMapperGem.mapperName().getValue());
        }
        metadata.setMapperNameSuffix(autoMapperGem.mapperNameSuffix().getValue());
        metadata.setDependencies(dependencies);

        addMapper(metadata, true);

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
            ReverseAutoMappingGem reverseAutoMappingGem = ReverseAutoMappingGem.instanceOn(field);
            if (reverseAutoMappingGem != null && reverseAutoMappingGem.isValid()) {
                list.add(buildAutoMappingMetadata(reverseAutoMappingGem, field));
            }
            ReverseAutoMappingsGem reverseAutoMappingsGem = ReverseAutoMappingsGem.instanceOn(field);
            if (reverseAutoMappingsGem != null && reverseAutoMappingsGem.isValid()) {
                reverseAutoMappingsGem.value().get().forEach(a -> buildAutoMappingMetadata(a, field));
            }
        }

        // super class
        getSuperClass(ele).ifPresent(superClass -> list.addAll(buildFieldReverseMappingMetadata(superClass)));

        list.removeIf(Objects::isNull);
        return list;
    }

    private AutoMappingMetadata buildAutoMappingMetadata(ReverseAutoMappingGem reverseAutoMappingGem, Element ele) {
        ClassName targetClass = transToClassName(reverseAutoMappingGem.targetClass().get());
        if (targetClass == null) {
            return null;
        }

        AutoMappingMetadata metadata = new AutoMappingMetadata();
        if (StrUtil.isNotEmpty(reverseAutoMappingGem.source().get())) {
            metadata.setSource(reverseAutoMappingGem.source().get());
        } else {
            metadata.setSource(ele.getSimpleName().toString());
        }
        if (StrUtil.isNotEmpty(reverseAutoMappingGem.target().get())) {
            metadata.setTarget(reverseAutoMappingGem.target().get());
        } else {
            metadata.setTarget(ele.getSimpleName().toString());
        }
        metadata.setTargetClass(targetClass);
        metadata.setDefaultValue(reverseAutoMappingGem.defaultValue().getValue());
        metadata.setIgnore(reverseAutoMappingGem.ignore().getValue());
        metadata.setExpression(reverseAutoMappingGem.expression().getValue());
        metadata.setDefaultExpression(reverseAutoMappingGem.defaultExpression().getValue());
        metadata.setConditionExpression(reverseAutoMappingGem.conditionExpression().getValue());
        metadata.setDateFormat(reverseAutoMappingGem.dateFormat().getValue());
        metadata.setNumberFormat(reverseAutoMappingGem.numberFormat().getValue());
        metadata.setQualifiedByName(reverseAutoMappingGem.qualifiedByName().getValue());
        metadata.setConditionQualifiedByName(reverseAutoMappingGem.conditionQualifiedByName().getValue());
        metadata.setDependsOn(reverseAutoMappingGem.dependsOn().getValue());
        metadata.setConstant(reverseAutoMappingGem.constant().getValue());
        metadata.setQualifiedBy(transToClassNameList(reverseAutoMappingGem.qualifiedBy().getValue()));
        metadata.setNullValueCheckStrategy(reverseAutoMappingGem.nullValueCheckStrategy().getValue());
        metadata.setNullValuePropertyMappingStrategy(
            reverseAutoMappingGem.nullValuePropertyMappingStrategy().getValue());
        metadata.setMappingControl(transToClassName(reverseAutoMappingGem.mappingControl().getValue()));

        return metadata;
    }

    private boolean addMapper(AutoMapperMetadata metadata, boolean throwsException) {
        boolean addSuccess = mapperSet.add(
            metadata.getSourceClassName().reflectionName() + "To" + metadata.getTargetClassName().reflectionName());
        if (throwsException) {

            if (!addSuccess) {
                throw new DuplicateMapperException("An exception occurred to generate " + metadata.mapperName() +
                                                   ", check the mapping configuration for " +
                                                   metadata.getSourceClassName().reflectionName() + " or " +
                                                   metadata.getTargetClassName().reflectionName());
            }
        }
        return addSuccess;
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
            AutoMappingGem autoMappingGem = AutoMappingGem.instanceOn(ele);
            if (autoMappingGem != null && autoMappingGem.isValid()) {
                list.add(buildAutoMappingMetadata(autoMappingGem, ele));
            }
            AutoMappingsGem autoMappingsGem = AutoMappingsGem.instanceOn(ele);
            if (autoMappingsGem != null && autoMappingsGem.isValid()) {
                autoMappingsGem.value().get().forEach(a -> list.add(buildAutoMappingMetadata(a, ele)));
            }
        }

        // add super class AutoMappings
        getSuperClass(autoMapperEle).ifPresent(superClass -> list.addAll(buildFieldMappingMetadata(superClass)));

        list.removeIf(Objects::isNull);
        return list;
    }

    private AutoMappingMetadata buildAutoMappingMetadata(AutoMappingGem autoMappingGem, Element ele) {
        ClassName targetClass = transToClassName(autoMappingGem.targetClass().get());
        if (targetClass == null) {
            return null;
        }

        AutoMappingMetadata metadata = new AutoMappingMetadata();
        String elementName = ele.getSimpleName().toString();

        if (ele.getKind() == ElementKind.METHOD) {
            elementName = ObjectUtils.defaultIfNull(StrUtil.getGeneralField(elementName), elementName);
        }

        if (StrUtil.isNotEmpty(autoMappingGem.source().get())) {
            metadata.setSource(autoMappingGem.source().get());
        } else {
            metadata.setSource(elementName);
        }
        if (StrUtil.isNotEmpty(autoMappingGem.target().get())) {
            metadata.setTarget(autoMappingGem.target().get());
        } else {
            metadata.setTarget(elementName);
        }
        metadata.setTargetClass(targetClass);
        metadata.setReverseConvertGenerate(autoMappingGem.reverseConvertGenerate().get());
        metadata.setDefaultValue(autoMappingGem.defaultValue().getValue());
        metadata.setIgnore(autoMappingGem.ignore().getValue());
        metadata.setExpression(autoMappingGem.expression().getValue());
        metadata.setDefaultExpression(autoMappingGem.defaultExpression().getValue());
        metadata.setConditionExpression(autoMappingGem.conditionExpression().getValue());
        metadata.setDateFormat(autoMappingGem.dateFormat().getValue());
        metadata.setNumberFormat(autoMappingGem.numberFormat().getValue());
        metadata.setQualifiedByName(autoMappingGem.qualifiedByName().getValue());
        metadata.setConditionQualifiedByName(autoMappingGem.conditionQualifiedByName().getValue());
        metadata.setDependsOn(autoMappingGem.dependsOn().getValue());
        metadata.setConstant(autoMappingGem.constant().getValue());
        metadata.setQualifiedBy(transToClassNameList(autoMappingGem.qualifiedBy().getValue()));
        metadata.setNullValueCheckStrategy(autoMappingGem.nullValueCheckStrategy().getValue());
        metadata.setNullValuePropertyMappingStrategy(autoMappingGem.nullValuePropertyMappingStrategy().getValue());
        metadata.setMappingControl(transToClassName(autoMappingGem.mappingControl().getValue()));
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

    private List<ClassName> transToClassNameList(List<TypeMirror> typeMirrors) {
        if (typeMirrors == null) {
            return new ArrayList<>();
        }
        return typeMirrors.stream()
            .map(this::transToClassName)
            .collect(Collectors.toList());
    }

    private ClassName transToClassName(TypeMirror typeMirror) {
        if (typeMirror == null) {
            return null;
        }
        return (ClassName) ClassName.get(typeMirror);
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
