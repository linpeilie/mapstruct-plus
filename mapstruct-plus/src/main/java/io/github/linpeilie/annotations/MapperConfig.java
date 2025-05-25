package io.github.linpeilie.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.MappingControl;

import static org.mapstruct.NullValueCheckStrategy.ON_IMPLICIT_CONVERSION;

/**
 * 在一个类或者接口上添加该注释，作为自动生成 Mapper 的配置类。在一个模块中，只能有一个有该注释的类。
 * Marks a class or interface as configuration source. There can be only one annotated type in
 * each compiled module.
 *
 * @author linpl
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperConfig {

    /**
     * 设置公共的转换器或其它 Mapper 类，在这里主要用途是设置全局共享的自定义转换类，不用再单个注解上重复设置 {@link org.mapstruct.MapperConfig#uses()}
     */
    Class<?>[] uses() default {};

    /**
     * 所生成的 Mapper 接口的包
     * @return Mapper 接口自动生成后的包名，如果为空，则默认生成在要转换的类同包下
     */
    String mapperPackage() default "";

    /**
     * 当来源类中没有对应属性时的策略，默认忽略
     * @return {@link ReportingPolicy}
     */
    ReportingPolicy unmappedSourcePolicy() default ReportingPolicy.IGNORE;

    /**
     * 当目标类中没有对应属性时的策略，默认忽略
     * @return {@link ReportingPolicy}
     */
    ReportingPolicy unmappedTargetPolicy() default ReportingPolicy.IGNORE;

    /**
     * How lossy (narrowing) conversion, for instance: long to integer should be
     * reported.
     *
     * @since 1.4.2
     *
     * @return The reporting policy for type conversion.
     */
    ReportingPolicy typeConversionPolicy() default ReportingPolicy.IGNORE;

    /**
     * The strategy to be applied when propagating the value of collection-typed properties. By default, only JavaBeans
     * accessor methods (setters or getters) will be used, but it is also possible to invoke a corresponding adder
     * method for each element of the source collection (e.g. {@code orderDto.addOrderLine()}).
     *
     * @since 1.4.2
     *
     * @return The strategy applied when propagating the value of collection-typed properties.
     */
    CollectionMappingStrategy collectionMappingStrategy() default CollectionMappingStrategy.ACCESSOR_ONLY;

    /**
     * 空对象的映射策略，默认返回 <code>null</code>
     * @return {@link NullValueMappingStrategy}
     */
    NullValueMappingStrategy nullValueMappingStrategy() default NullValueMappingStrategy.RETURN_NULL;

    /**
     * The strategy to be applied when {@code null} is passed as source argument value to an {@link IterableMapping}.
     * If no strategy is configured, the strategy given via {@link #nullValueMappingStrategy()} will be applied, using
     * {@link NullValueMappingStrategy#RETURN_NULL} by default.
     *
     * @since 1.4.2
     *
     * @return The strategy to be applied when {@code null} is passed as source value to an {@link IterableMapping}.
     */
    NullValueMappingStrategy nullValueIterableMappingStrategy() default NullValueMappingStrategy.RETURN_NULL;

    /**
     * The strategy to be applied when {@code null} is passed as source argument value to a {@link MapMapping}.
     * If no strategy is configured, the strategy given via {@link #nullValueMappingStrategy()} will be applied, using
     * {@link NullValueMappingStrategy#RETURN_NULL} by default.
     *
     * @since 1.4.2
     *
     * @return The strategy to be applied when {@code null} is passed as source value to a {@link MapMapping}.
     */
    NullValueMappingStrategy nullValueMapMappingStrategy() default NullValueMappingStrategy.RETURN_NULL;

    /**
     * 当属性值为 <code>null</code> 时应对的策略，默认设置为 <code>null</code>
     * @return {@link NullValuePropertyMappingStrategy}
     */
    NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy() default NullValuePropertyMappingStrategy.SET_TO_NULL;

    /**
     * Determines when to include a null check on the source property value of a bean mapping.
     *
     * Can be overridden by the one on {@link Mapper}, {@link BeanMapping} or {@link Mapping}.
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

    /**
     * Exception that should be thrown by the generated code if no mapping matches for enums.
     * If no exception is configured, {@link IllegalArgumentException} will be used by default.
     *
     * <p>
     * Note:
     * <ul>
     *     <li>
     *      The defined exception should at least have a constructor with a {@link String} parameter.
     *     </li>
     *     <li>
     *      If the defined exception is a checked exception then the enum mapping methods should have that exception
     *      in the throws clause.
     *     </li>
     * </ul>
     *
     * @since 1.4.2
     *
     * @return the exception that should be used in the generated code
     */
    Class<? extends Exception> unexpectedValueMappingException() default IllegalArgumentException.class;

    /**
     * Flag indicating whether the addition of a time stamp in the {@code @Generated} annotation should be suppressed.
     * i.e. not be added.
     *
     * The method overrides the flag set through an annotation processor option.
     *
     * @since 1.4.2
     *
     * @return whether the addition of a timestamp should be suppressed
     */
    boolean suppressTimestampInGenerated() default false;

    /**
     * 构造者模式配置，由于 mapstruct 和 lombok 和 builder 一起使用时，会丢失父类属性，这里默认改为关闭
     * @return  {@link Builder}
     */
    Builder builder() default @Builder(disableBuilder = true);

    /**
     * 默认包名为：io.github.linpeilie
     * @return ConvertAdapterClass 包名
     */
    String adapterPackage() default "";

    /**
     * 默认类名为：ConvertMapperAdapter
     * @return ConvertAdapterClass 类名
     */
    String adapterClassName() default "";

    /**
     * 默认类名为：MapConvertMapperAdapter
     * @return MapConvertAdapterClass 类名
     */
    String mapAdapterClassName() default "";

    /**
     * MapStructPlus 所生成的配置类(AutoMapperConfig/AutoMapMapperConfig)包路径
     * <br>
     * 默认包路径为 io.github.linpeilie
     * @return AutoMapperConfig / AutoMapMapperConfig 包路径
     */
    String autoConfigPackage() default "";

    /**
     * MapStructPlus 所生成的配置类转换的配置类名
     * <br>
     * 默认类名为 AutoMapperConfig
     * @return  AutoMapperConfig 类名
     */
    String autoMapperConfigClassName() default "";

    /**
     * MapStructPlus 所生成的配置 Map 与对象转换的配置类名
     * <br>
     * 默认类名为 AutoMapMapperConfig
     * @return  AutoMapMapperConfig 类名
     */
    String autoMapMapperConfigClassName() default "";

}
