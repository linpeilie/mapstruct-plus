package io.github.linpeilie.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface AutoMapper {

    Class<?> target();

    Class<?>[] uses() default {};

    /**
     * 是否生成反向转换的接口
     *
     * @return true : 生成反向转换的接口
     *         false : 不生成反向转换的接口
     */
    boolean reverseConvertGenerate() default true;

}
