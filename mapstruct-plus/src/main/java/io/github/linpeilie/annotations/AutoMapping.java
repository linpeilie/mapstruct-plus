package io.github.linpeilie.annotations;

import io.github.linpeilie.DefaultMapping;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.Condition;
import org.mapstruct.Named;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface AutoMapping {

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
     * @return One or more qualifier name(s)
     * @see Named
     * @since 1.4.0
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
     *
     * @return One or more qualifier name(s)
     * @see #qualifiedByName()
     * @see Named
     * @since 1.4.0
     */
    String[] conditionQualifiedByName() default {};

    /**
     * One or more properties of the result type on which the mapped property depends. The generated method
     * implementation will invoke the setters of the result type ordered so that the given dependency relationship(s)
     * are satisfied. Useful in case one property setter depends on the state of another property of the result type.
     * <p>
     * An error will be raised in case a cycle in the dependency relationships is detected.
     *
     * @return the dependencies of the mapped property
     * @since 1.4.0
     */
    String[] dependsOn() default {};

}
