package io.github.linpeilie.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.BeanMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.MappingControl;

import static org.mapstruct.NullValueCheckStrategy.ON_IMPLICIT_CONVERSION;

/**
 * @author linpl
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface AutoMapper {

    Class<?> target();

    Class<?>[] uses() default {};

    /**
     * 转换需要依赖的枚举，依赖的枚举，需要被 AutoEnumMapper 所注解
     */
    Class<?>[] useEnums() default {};

    Class<?>[] imports() default {};

    /**
     * 是否生成转换的接口，当只想生成反向接口时，可以指定当前属性为 {@code false}
     *
     * @return {@code true} 生成类型转换的接口 {@code false} 不生成类型转换的接口
     */
    boolean convertGenerate() default true;

    /**
     * 生成的 Mapper 接口名称
     *
     * @return 生成的 Mapper 接口名称
     */
    String mapperName() default "";

    /**
     * 生成的 Mapper 接口名称后缀，生成的反向转换接口同时生效
     *
     * @return 生成的 Mapper 接口名称后缀
     */
    String mapperNameSuffix() default "";

    /**
     * 是否生成反向转换的接口
     *
     * @return true : 生成反向转换的接口 false : 不生成反向转换的接口
     */
    boolean reverseConvertGenerate() default true;

    /**
     * 是否需要避免对象循环嵌套
     * <p>
     *     循环嵌套：A中有属性类型B，B中有属性类型A，且可能对象之间互相引用
     * </p>
     *
     * @return true: 需要避免对象循环嵌套；false：不需要
     */
    boolean cycleAvoiding() default false;

    /**
     * How unmapped properties of the source type of a mapping should be
     * reported. The method overrides an unmappedSourcePolicy set in a central
     * configuration
     *
     * @since 1.4.2
     *
     * @return The reporting policy for unmapped source properties.
     */
    ReportingPolicy unmappedSourcePolicy() default ReportingPolicy.IGNORE;

    /**
     * How unmapped properties of the target type of a mapping should be
     * reported. The method overrides an unmappedTargetPolicy set in a central
     * configuration
     *
     * @since 1.4.2
     *
     * @return The reporting policy for unmapped target properties.
     */
    ReportingPolicy unmappedTargetPolicy() default ReportingPolicy.WARN;

    /**
     * How lossy (narrowing) conversion, for instance long to integer should be
     * reported. The method overrides an typeConversionPolicy set in a central
     * configuration
     *
     * @since 1.4.2
     *
     * @return The reporting policy for unmapped target properties.
     */
    ReportingPolicy typeConversionPolicy() default ReportingPolicy.IGNORE;

    /**
     * The strategy to be applied when propagating the value of collection-typed properties. By default, only JavaBeans
     * accessor methods (setters or getters) will be used, but it is also possible to invoke a corresponding adder
     * method for each element of the source collection (e.g. {@code orderDto.addOrderLine()}).
     * <p>
     * Any setting given for this attribute will take precedence over {@link org.mapstruct.MapperConfig#collectionMappingStrategy()},
     * if present.
     *
     * @since 1.4.2
     *
     * @return The strategy applied when propagating the value of collection-typed properties.
     */
    CollectionMappingStrategy collectionMappingStrategy() default CollectionMappingStrategy.ACCESSOR_ONLY;

    /**
     * The strategy to be applied when {@code null} is passed as source argument value to the methods of this mapper.
     * If no strategy is configured, the strategy given via {@link MapperConfig#nullValueMappingStrategy()} will be
     * applied, using {@link NullValueMappingStrategy#RETURN_NULL} by default.
     *
     * @since 1.4.2
     *
     * @return The strategy to be applied when {@code null} is passed as source value to the methods of this mapper.
     */
    NullValueMappingStrategy nullValueMappingStrategy() default NullValueMappingStrategy.RETURN_NULL;

    /**
     * The strategy to be applied when {@code null} is passed as source argument value to an {@link IterableMapping} of
     * this mapper. If unset, the strategy set with {@link #nullValueMappingStrategy()} will be applied. If neither
     * strategy is configured, the strategy given via {@link MapperConfig#nullValueIterableMappingStrategy()} will be
     * applied, using {@link NullValueMappingStrategy#RETURN_NULL} by default.
     *
     * @since 1.4.2
     *
     * @return The strategy to be applied when {@code null} is passed as source value to an {@link IterableMapping} of
     * this mapper.
     */
    NullValueMappingStrategy nullValueIterableMappingStrategy() default NullValueMappingStrategy.RETURN_NULL;


    /**
     * The strategy to be applied when a source bean property is {@code null} or not present. If no strategy is
     * configured, the strategy given via {@link MapperConfig#nullValuePropertyMappingStrategy()} will be applied,
     * {@link NullValuePropertyMappingStrategy#SET_TO_NULL} will be used by default.
     *
     * @since 1.4.2
     *
     * @return The strategy to be applied when {@code null} is passed as source property value or the source property
     * is not present.
     */
    NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy() default
        NullValuePropertyMappingStrategy.SET_TO_NULL;

    /**
     * Determines when to include a null check on the source property value of a bean mapping.
     *
     * Can be overridden by the one on {@link MapperConfig}, {@link BeanMapping}  or {@link Mapping}.
     *
     * @since 1.4.2
     *
     * @return strategy how to do null checking
     */
    NullValueCheckStrategy nullValueCheckStrategy() default ON_IMPLICIT_CONVERSION;

    /**
     * Allows detailed control over the mapping process.
     *
     * @return the mapping control
     *
     * @since 1.4.2
     *
     * @see org.mapstruct.control.DeepClone
     * @see org.mapstruct.control.NoComplexMapping
     * @see org.mapstruct.control.MappingControl
     */
    Class<? extends Annotation> mappingControl() default MappingControl.class;


}
