package io.github.linpeilie.mapstruct.autoconfigure;

import io.github.linpeilie.Converter;
import io.github.linpeilie.ConverterFactory;
import io.github.linpeilie.DefaultConverterFactory;
import io.github.linpeilie.mapstruct.SpringContextUtils4Msp;
import io.github.linpeilie.mapstruct.SpringConverterFactory;
import io.github.linpeilie.mapstruct.autoconfigure.fixture.Boot4MappingFixtures.InjectedMapper;
import io.github.linpeilie.mapstruct.autoconfigure.fixture.Boot4MappingFixtures.InjectedSource;
import io.github.linpeilie.mapstruct.autoconfigure.fixture.Boot4MappingFixtures.PrefixFormatter;
import io.github.linpeilie.mapstruct.autoconfigure.fixture.Boot4MappingFixtures.Source;
import io.github.linpeilie.mapstruct.autoconfigure.fixture.Boot4MappingFixtures.Target;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Loads the starter through Boot's imports discovery rather than directly importing its configuration.
 *
 * <p>The fixtures are outside any component scan. Passing conversion tests therefore requires both
 * annotation processing and manifest-driven registration to work with Spring Framework 7.
 *
 * @author hb0730
 */
class MapstructAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withUserConfiguration(Application.class);

    @Test
    void discoversAutoConfigurationAndSharedContextBridge() {
        contextRunner.run(context -> {
            assertThat(context).hasNotFailed()
                .hasSingleBean(Converter.class)
                .hasSingleBean(ConverterFactory.class)
                .hasSingleBean(SpringContextUtils4Msp.class);
            assertThat(context.getBean(ConverterFactory.class)).isInstanceOf(SpringConverterFactory.class);
            assertThat(SpringContextUtils4Msp.getBean(Converter.class)).isSameAs(context.getBean(Converter.class));
        });
    }

    @Test
    void convertsGeneratedMappersInBothDirectionsWithoutComponentScanning() {
        contextRunner.run(context -> {
            assertThat(context).hasNotFailed();
            Source source = new Source();
            source.setName("manifest");
            Converter converter = context.getBean(Converter.class);
            Target target = converter.convert(source, Target.class);
            assertThat(target.getName()).isEqualTo("manifest");
            assertThat(converter.convert(target, Source.class).getName()).isEqualTo("manifest");
        });
    }

    @Test
    void injectsApplicationBeansIntoCustomGeneratedMappers() {
        contextRunner.run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(InjectedMapper.class);
            InjectedSource source = new InjectedSource();
            source.setName("injected");
            assertThat(context.getBean(InjectedMapper.class).convert(source).getName()).isEqualTo("boot4:injected");
        });
    }

    @Test
    void backsOffForAnApplicationConverter() {
        contextRunner.withUserConfiguration(CustomConverterConfiguration.class).run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(Converter.class);
            assertThat(context.getBean(Converter.class)).isSameAs(context.getBean("customConverter"));
            assertThat(context).doesNotHaveBean("converter");
        });
    }

    @Test
    void usesAnApplicationConverterFactory() {
        contextRunner.withUserConfiguration(CustomFactoryConfiguration.class).run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(ConverterFactory.class).hasSingleBean(Converter.class);
            assertThat(context.getBean(ConverterFactory.class)).isSameAs(context.getBean("customFactory"));
            assertThat(context).doesNotHaveBean("converterFactory");
            Source source = new Source();
            source.setName("custom-factory");
            assertThat(context.getBean(Converter.class).convert(source, Target.class).getName())
                .isEqualTo("custom-factory");
        });
    }

    @Test
    void backsOffForAnApplicationContextBridge() {
        contextRunner.withUserConfiguration(CustomBridgeConfiguration.class).run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(SpringContextUtils4Msp.class);
            assertThat(context.getBean(SpringContextUtils4Msp.class)).isSameAs(context.getBean("customBridge"));
            assertThat(context).doesNotHaveBean("springContextUtils4Msp");
        });
    }

    @Test
    void supportsStandardAutoConfigurationExclusion() {
        contextRunner.withPropertyValues("spring.autoconfigure.exclude=" + MapstructAutoConfiguration.class.getName())
            .run(context -> assertThat(context).hasNotFailed()
                .doesNotHaveBean(Converter.class)
                .doesNotHaveBean(ConverterFactory.class)
                .doesNotHaveBean(SpringContextUtils4Msp.class)
                .doesNotHaveBean(InjectedMapper.class));
    }

    @Test
    void usesBoot4AndAConsistentSpring7Runtime() {
        assertThat(SpringBootVersion.getVersion()).startsWith("4.");
        assertThat(SpringVersion.getVersion()).startsWith("7.");
        assertThat(ApplicationContext.class.getPackage().getImplementationVersion())
            .isEqualTo(SpringVersion.getVersion());
        assertThat(BeansException.class.getPackage().getImplementationVersion()).isEqualTo(SpringVersion.getVersion());
    }

    /** No component scan: the starter alone must register the generated mappers. */
    @Configuration(proxyBeanMethods = false)
    @EnableAutoConfiguration
    static class Application {

        @Bean
        PrefixFormatter prefixFormatter() {
            return new PrefixFormatter();
        }
    }

    /** A consumer can provide its own public conversion entry point. */
    @Configuration(proxyBeanMethods = false)
    static class CustomConverterConfiguration {

        @Bean
        Converter customConverter() {
            return new Converter(new DefaultConverterFactory());
        }
    }

    /** The default converter must accept a factory declared by the consumer. */
    @Configuration(proxyBeanMethods = false)
    static class CustomFactoryConfiguration {

        @Bean
        ConverterFactory customFactory(ApplicationContext context) {
            return new SpringConverterFactory(context);
        }
    }

    /** Static post-processor registration avoids creating this configuration prematurely. */
    @Configuration(proxyBeanMethods = false)
    static class CustomBridgeConfiguration {

        @Bean
        static SpringContextUtils4Msp customBridge() {
            return new SpringContextUtils4Msp();
        }
    }
}
