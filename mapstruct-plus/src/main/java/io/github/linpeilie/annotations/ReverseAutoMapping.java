package io.github.linpeilie.annotations;

import io.github.linpeilie.DefaultMapping;

import java.lang.annotation.*;

import org.mapstruct.BeanMapping;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Qualifier;
import org.mapstruct.control.MappingControl;

import static org.mapstruct.NullValueCheckStrategy.ON_IMPLICIT_CONVERSION;

/**
 * 由目标类生成当前类的配置
 * <p>
 * {@link AutoMapper} 的生成规则是由配置注解的当前类，生成与目标类之间的转换接口，所以其内部字段中的 {@link AutoMapping} 都是基于该条件来配置的。
 * 虽然默认情况下会生成目标类到当前类的转换，但如果自定义配置的话，仍需要到目标类上面进行配置。
 * </p>
 * <p>
 * 但现实情况中，可能会出现目标类，不能或者不建议增加配置注解，所以，这里提供一种在一种类上面自定义配置两个类转换的方式。 如果需要配置由目标类转换为配置注解的当前类具体转换逻辑的话，可以使用当前注解
 * </p>
 * <p>
 * <strong>需要注意的是，如果在当前类中配置了该注解信息，则在目标类中不能够再定义与该类转换相关的注解，例如{@code AutoMapper}、{@code AutoMapping}</strong>
 * </p>
 *
 * @author linpl
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.CLASS)
@Repeatable(ReverseAutoMappings.class)
public @interface ReverseAutoMapping {


    Class<?> targetClass() default DefaultMapping.class;

    /**
     * 来源，默认取当前字段名称
     * - 可以是当前类中的属性名
     * - 也可以是属性名.属性名，例如：address.city.name
     * @return 当前类中的属性
     */
    String source() default "";

    String target() default "";

    String dateFormat() default "";

    String numberFormat() default "";

    String constant() default "";

    String expression() default "";

    String defaultExpression() default "";

    String conditionExpression() default "";

    boolean ignore() default false;

    /**
     * 默认值
     * @return 当源属性为null时，设置的默认值
     */
    String defaultValue() default "";

    /**
     * A qualifier can be specified to aid the selection process of a suitable mapper. This is useful in case multiple
     * mapping methods (hand written or generated) qualify and thus would result in an 'Ambiguous mapping methods found'
     * error. A qualifier is a custom annotation and can be placed on a hand written mapper class or a method.
     * <p>
     * Note that {@link #defaultValue()} usage will also be converted using this qualifier.
     *
     * qualifier 可以置顶限定符来帮助选择合适的转换方法。qualifier 是一个自定义的注解，可以标注在 mapper 类或者方法上。
     * <p>
     * Note that {@link #defaultValue()} usage will also be converted using this qualifier.
     * <p>
     *
     * @since 1.4.2
     *
     * @return the qualifiers
     * @see Qualifier
     */
    Class<? extends Annotation>[] qualifiedBy() default { };

    /**
     * String-based form of qualifiers; When looking for a suitable mapping method for a given property, MapStruct will
     * only consider those methods carrying directly or indirectly (i.e. on the class-level) a {@link Named} annotation
     * for each of the specified qualifier names.
     * <p>
     * Note that annotation-based qualifiers are generally preferable as they allow more easily to find references and
     * are safe for refactorings, but name-based qualifiers can be a less verbose alternative when requiring a large
     * number of qualifiers as no custom annotation types are needed.
     * <p>
     * Note that {@link #defaultValue()} usage will also be converted using this qualifier.
     *
     * @since 1.4.0
     *
     * @return One or more qualifier name(s)
     * @see Named
     */
    String[] qualifiedByName() default {};

    /**
     * String-based form of qualifiers for condition / presence check methods;
     * When looking for a suitable presence check method for a given property, MapStruct will
     * only consider those methods carrying directly or indirectly (i.e. on the class-level) a {@link Named} annotation
     * for each of the specified qualifier names.
     *
     * This is similar like {@link #qualifiedByName()} but it is only applied for {@link Condition} methods.
     * <p>
     *   Note that annotation-based qualifiers are generally preferable as they allow more easily to find references and
     *   are safe for refactorings, but name-based qualifiers can be a less verbose alternative when requiring a large
     *   number of qualifiers as no custom annotation types are needed.
     * </p>
     *
     * @since 1.4.0
     *
     * @return One or more qualifier name(s)
     * @see #qualifiedByName()
     * @see Named
     */
    String[] conditionQualifiedByName() default {};

    /**
     * One or more properties of the result type on which the mapped property depends. The generated method
     * implementation will invoke the setters of the result type ordered so that the given dependency relationship(s)
     * are satisfied. Useful in case one property setter depends on the state of another property of the result type.
     * <p>
     * An error will be raised in case a cycle in the dependency relationships is detected.
     *
     * @since 1.4.0
     *
     * @return the dependencies of the mapped property
     */
    String[] dependsOn() default {};


    /**
     * Determines when to include a null check on the source property value of a bean mapping.
     *
     * Can be overridden by the one on {@link org.mapstruct.MapperConfig}, {@link Mapper} or {@link BeanMapping}.
     *
     * @since 1.4.2
     *
     * @return strategy how to do null checking
     */
    NullValueCheckStrategy nullValueCheckStrategy() default ON_IMPLICIT_CONVERSION;

    /**
     * The strategy to be applied when the source property is {@code null} or not present. If no strategy is configured,
     * the strategy given via {@link MapperConfig#nullValuePropertyMappingStrategy()},
     * {@link BeanMapping#nullValuePropertyMappingStrategy()} or
     * {@link Mapper#nullValuePropertyMappingStrategy()} will be applied.
     *
     * {@link NullValuePropertyMappingStrategy#SET_TO_NULL} will be used by default.
     *
     * @since 1.4.2
     *
     * @return The strategy to be applied when {@code null} is passed as source property value or the source property
     * is not present.
     */
    NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy()
        default NullValuePropertyMappingStrategy.SET_TO_NULL;

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
