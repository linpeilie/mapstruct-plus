package io.github.linpeilie.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author linpl
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface AutoMapper {

    Class<?> target();

    Class<?>[] uses() default {};

    Class<?>[] imports() default {};

    /**
     * 是否生成转换的接口，当只想生成反向接口时，可以指定当前属性为 {@code false}
     *
     * @return {@code true} 生成类型转换的接口 {@code false} 不生成类型转换的接口
     */
    boolean convertGenerate() default true;

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

}
