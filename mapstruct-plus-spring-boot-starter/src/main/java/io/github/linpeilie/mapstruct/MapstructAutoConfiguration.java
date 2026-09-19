package io.github.linpeilie.mapstruct;

import io.github.linpeilie.Converter;
import io.github.linpeilie.ConverterFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * mapper 注册说明：注册统一由 {@link ModuleMapperRegistrar} 依据编译期清单
 * （META-INF/mapstruct-plus/module-mappers）完成，是 spring 线唯一注册通道；
 * classpath 上没有清单的产物不做任何兜底处理
 */
@Configuration
@Import(ModuleMapperRegistrar.class)
public class MapstructAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ConverterFactory converterFactory(ApplicationContext applicationContext) {
        return new SpringConverterFactory(applicationContext);
    }

    @Bean
    @ConditionalOnMissingBean
    public Converter converter(ConverterFactory converterFactory) {
        return new Converter(converterFactory);
    }

    @Bean
    public static SpringContextUtils4Msp springContextUtils4Msp() {
        return new SpringContextUtils4Msp();
    }

}
