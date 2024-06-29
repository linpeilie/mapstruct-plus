package io.github.linpeilie.mapstruct;

import io.github.linpeilie.Converter;
import io.github.linpeilie.ConverterFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.github.linpeilie")
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
    public SpringContextUtils springContextUtils() {
        return new SpringContextUtils();
    }

}
