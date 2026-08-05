package io.github.linpeilie.annotations;

import io.github.linpeilie.map.MapObjectConverter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 为添加当前注解的类生成 Map 转为当前类的转换接口。
 *
 * @author linpl
 * @since 1.1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface AutoMapMapper {

    /**
     * 指定 Map 转对象时使用的类型转换器实现类（类级覆盖）。
     * <p>
     * 未指定时（默认哨兵值），依次回退到：
     * <ol>
     *   <li>{@code @MapperConfig.mapObjectConverter} 全局配置</li>
     *   <li>内置默认 {@code HutoolMapObjectConverter}</li>
     * </ol>
     *
     * @return 转换器实现类，默认哨兵值表示未指定
     * @since 1.5.2
     */
    Class<? extends MapObjectConverter> use() default MapObjectConverter.class;

}
