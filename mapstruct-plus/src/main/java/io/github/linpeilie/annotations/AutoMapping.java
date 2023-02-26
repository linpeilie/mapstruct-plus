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

    String target();

    String dateFormat() default "";

    String numberFormat() default "";

    String expression() default "";

    boolean ignore() default false;

}