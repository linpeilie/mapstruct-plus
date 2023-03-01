package io.github.linpeilie.annotations;

import io.github.linpeilie.DefaultMapping;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
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

    boolean ignore() default false;

    /**
     * 默认值
     * @return 当源属性为null时，设置的默认值
     */
    String defaultValue() default "";

}
