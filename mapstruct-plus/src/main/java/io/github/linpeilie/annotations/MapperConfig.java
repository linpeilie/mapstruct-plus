package io.github.linpeilie.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.Builder;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

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

    /**
     * 当来源类中没有对应属性时的策略，默认忽略
     * @return {@link ReportingPolicy}
     */
    ReportingPolicy unmappedSourcePolicy() default ReportingPolicy.IGNORE;

    /**
     * 当目标类中没有对应属性时的策略，默认忽略
     * @return {@link ReportingPolicy}
     */
    ReportingPolicy unmappedTargetPolicy() default ReportingPolicy.IGNORE;

    /**
     * 空对象的映射策略，默认返回 <code>null</code>
     * @return {@link NullValueMappingStrategy}
     */
    NullValueMappingStrategy nullValueMappingStrategy() default NullValueMappingStrategy.RETURN_NULL;

    /**
     * 当属性值为 <code>null</code> 时应对的策略，默认设置为 <code>null</code>
     * @return {@link NullValuePropertyMappingStrategy}
     */
    NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy() default NullValuePropertyMappingStrategy.SET_TO_NULL;

    /**
     * 构造者模式配置，由于 mapstruct 和 lombok 和 builder 一起使用时，会丢失父类属性，这里默认改为关闭
     * @return  {@link Builder}
     */
    Builder builder() default @Builder(disableBuilder = true);

    /**
     * 默认包名为：io.github.linpeilie
     * @return ConvertAdapterClass 包名
     */
    String adapterPackage() default "";

    /**
     * 默认类名为：ConvertMapperAdapter
     * @return ConvertAdapterClass 类名
     */
    String adapterClassName() default "";

    /**
     * 默认类名为：MapConvertMapperAdapter
     * @return MapConvertAdapterClass 类名
     */
    String mapAdapterClassName() default "";

    /**
     * MapStructPlus 所生成的配置类(AutoMapperConfig/AutoMapMapperConfig)包路径
     * <br>
     * 默认包路径为 io.github.linpeilie
     * @return AutoMapperConfig / AutoMapMapperConfig 包路径
     */
    String autoConfigPackage() default "";

    /**
     * MapStructPlus 所生成的配置类转换的配置类名
     * <br>
     * 默认类名为 AutoMapperConfig
     * @return  AutoMapperConfig 类名
     */
    String autoMapperConfigClassName() default "";

    /**
     * MapStructPlus 所生成的配置 Map 与对象转换的配置类名
     * <br>
     * 默认类名为 AutoMapMapperConfig
     * @return  AutoMapMapperConfig 类名
     */
    String autoMapMapperConfigClassName() default "";

}
