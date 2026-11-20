package io.github.linpeilie.compatibility;

import io.github.linpeilie.Converter;
import io.github.linpeilie.ConverterFactory;
import io.github.linpeilie.mapstruct.SpringConverterFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests installed starter artifacts using an independent application's Boot BOM.
 *
 * <p>No mapper component scan is configured. Successful conversion therefore exercises
 * imports discovery, the processor-generated manifest and Spring mapper registration.
 *
 * @author hb0730
 */
class StarterCompatibilityTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
        .withUserConfiguration(Application.class);

    @Test
    void discoversTheStarterAndConvertsBothDirections() {
        runner.run(context -> {
            assertNull(context.getStartupFailure());
            assertTrue(context.getBean(ConverterFactory.class) instanceof SpringConverterFactory);
            Converter converter = context.getBean(Converter.class);
            MappingFixtures.Source source = new MappingFixtures.Source();
            source.setName("compatibility");
            MappingFixtures.Target target = converter.convert(source, MappingFixtures.Target.class);
            assertEquals("compatibility", target.getName());
            assertEquals("compatibility", converter.convert(target, MappingFixtures.Source.class).getName());
        });
    }

    @Test
    void usesTheApplicationBootAndSpringVersions() {
        assertEquals(System.getProperty("compat.boot.version"), SpringBootVersion.getVersion());
        String prefix = System.getProperty("compat.spring.major") + ".";
        assertVersionPrefix(SpringVersion.getVersion(), prefix);
        assertVersionPrefix(BeanFactory.class.getPackage().getImplementationVersion(), prefix);
        assertVersionPrefix(ApplicationContext.class.getPackage().getImplementationVersion(), prefix);
    }

    @Test
    void doesNotExposeTheProcessorOnTheApplicationClasspath() {
        ClassLoader loader = getClass().getClassLoader();
        assertThrows(ClassNotFoundException.class,
            () -> Class.forName("io.github.linpeilie.processor.AutoMapperProcessor", false, loader));
        boolean boot4 = System.getProperty("compat.boot.version").startsWith("4.");
        if (boot4) {
            // Loading both starters would make automatic configuration depend on classpath order.
            assertThrows(ClassNotFoundException.class,
                () -> Class.forName("io.github.linpeilie.mapstruct.MapstructAutoConfiguration", false, loader));
        }
    }

    private static void assertVersionPrefix(String actual, String prefix) {
        assertNotNull(actual);
        assertTrue(actual.startsWith(prefix), actual + " must start with " + prefix);
    }

    @Configuration(proxyBeanMethods = false)
    @EnableAutoConfiguration
    static class Application {
    }
}
