package io.github.linpeilie.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
     * 所生成的 Mapper 接口的包
     * @return Mapper 接口自动生成后的包名，如果为空，则默认生成在要转换的类同包下
     */
    String mapperPackage() default "";

}
