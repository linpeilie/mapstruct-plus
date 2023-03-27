package io.github.linpeilie.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 为添加当前注解的类生成 Map 转为当前类的转换接口
 *
 * @author linpl
 * @since 1.2.2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface AutoEnumMapper {

    String value();

}
