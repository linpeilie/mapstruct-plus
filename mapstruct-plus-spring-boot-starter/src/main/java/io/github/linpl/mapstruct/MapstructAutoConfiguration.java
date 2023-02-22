package io.github.linpl.mapstruct;

import io.github.linpl.Converter;
import io.github.linpl.ConverterFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapstructAutoConfiguration {

    @Bean
    public ConverterFactory converterFactory(ApplicationContext applicationContext) {
        return new SpringConverterFactory(applicationContext);
    }

    @Bean
    public Converter converter(ConverterFactory converterFactory) {
        return new Converter(converterFactory);
    }

}
