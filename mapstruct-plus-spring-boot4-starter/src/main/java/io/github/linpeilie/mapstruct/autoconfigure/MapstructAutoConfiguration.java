package io.github.linpeilie.mapstruct.autoconfigure;

import io.github.linpeilie.Converter;
import io.github.linpeilie.ConverterFactory;
import io.github.linpeilie.mapstruct.ModuleMapperRegistrar;
import io.github.linpeilie.mapstruct.SpringContextUtils4Msp;
import io.github.linpeilie.mapstruct.SpringConverterFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Spring Boot 4 entry point for manifest-based mapper registration and Spring-backed conversion.
 *
 * <p>The shared registrar owns mapper discovery. No component scan is added, and applications can replace
 * the converter, its factory, or the context bridge independently.
 *
 * @author hb0730
 */
@AutoConfiguration
@ConditionalOnClass(Converter.class)
@Import(ModuleMapperRegistrar.class)
public class MapstructAutoConfiguration {

    /**
     * Creates a factory that resolves generated mappers through the application's bean lifecycle.
     *
     * @param applicationContext the context containing the registered mappers
     * @return the default Spring-backed factory
     */
    @Bean
    @ConditionalOnMissingBean
    public ConverterFactory converterFactory(ApplicationContext applicationContext) {
        return new SpringConverterFactory(applicationContext);
    }

    /**
     * Creates the public conversion entry point using the application's selected factory.
     *
     * @param converterFactory the default or application-provided factory
     * @return the default converter
     */
    @Bean
    @ConditionalOnMissingBean
    public Converter converter(ConverterFactory converterFactory) {
        return new Converter(converterFactory);
    }

    /**
     * Registers the context bridge used by generated Spring mappers.
     *
     * <p>This factory is static because the bridge is a BeanFactoryPostProcessor; Spring must create it
     * before regular beans without prematurely instantiating this configuration class.
     *
     * @return the shared Spring context bridge
     */
    @Bean
    @ConditionalOnMissingBean
    public static SpringContextUtils4Msp springContextUtils4Msp() {
        return new SpringContextUtils4Msp();
    }
}
