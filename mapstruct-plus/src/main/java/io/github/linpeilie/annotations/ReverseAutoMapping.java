package io.github.linpeilie.annotations;

import io.github.linpeilie.DefaultMapping;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface ReverseAutoMapping {

    Class<?> targetClass() default DefaultMapping.class;

    /**
     * 来源，默认取当前字段名称
     * <ul><li>可以是当前类中的属性名</li>
     * <li>也可以是属性名.属性名，例如：address.city.name</li></ul>
     * <strong>这里的来源，指的是目标类中的字段信息</strong>
     *
     * @return 目标类中的属性
     */
    String source() default "";

    /**
     * 目标属性，默认取当前字段名称
     * <ul>
     *     <li>可以是当前类中的属性名</li>
     *     <li>也可以是属性名.属性名，例如：address.city.name</li>
     * </ul>
     *
     * @return 当前类中的属性
     */
    String target() default "";

    String dateFormat() default "";

    String numberFormat() default "";

    String expression() default "";

    String defaultExpression() default "";

    String conditionExpression() default "";

    boolean ignore() default false;

    /**
     * 默认值
     *
     * @return 当来源属性为null时，所设置的默认值
     */
    String defaultValue() default "";

    String[] qualifiedByName() default {};

    String[] conditionQualifiedByName() default {};

    String[] dependsOn() default {};

}
