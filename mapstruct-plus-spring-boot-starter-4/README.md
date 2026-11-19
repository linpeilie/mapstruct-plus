# MapStruct Plus Spring Boot 4 Starter

Use `mapstruct-plus-spring-boot-starter-4` with Spring Boot 4 and JDK 17 or newer.
Its version follows the MapStruct Plus release containing this module. Do not use it together with
`mapstruct-plus-spring-boot-starter`; applications on older Boot versions keep the existing starter.

The Boot 4 starter and the existing starter share `mapstruct-plus-spring`. The shared module retains Java 8
bytecode and the existing class names for mapper registration, converter lookup, and the generated-code
context bridge. Each starter supplies its own Spring runtime. The Boot 4 starter is built against Boot 4.0.3;
the compatibility workflow also exercises Boot 4.1.2 on JDK 25.

## Maven

In a Spring Boot 4 application, use its normal Boot parent or BOM and add the starter:

```xml
<dependency>
    <groupId>io.github.linpeilie</groupId>
    <artifactId>mapstruct-plus-spring-boot-starter-4</artifactId>
    <version>${mapstruct-plus.version}</version>
</dependency>
```

Configure the processor explicitly. It is a build dependency, not a runtime dependency of the starter;
explicit discovery also works with JDK 23 and later.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.14.1</version>
    <configuration>
        <release>17</release>
        <annotationProcessorPaths>
            <path>
                <groupId>io.github.linpeilie</groupId>
                <artifactId>mapstruct-plus-processor</artifactId>
                <version>${mapstruct-plus.version}</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

Set `release` to the application's Java baseline. Applications using Lombok must also keep their Lombok
processor and `lombok-mapstruct-binding` entries as described in the existing installation guide.

## Gradle

```groovy
dependencies {
    implementation "io.github.linpeilie:mapstruct-plus-spring-boot-starter-4:${mapstructPlusVersion}"
    annotationProcessor "io.github.linpeilie:mapstruct-plus-processor:${mapstructPlusVersion}"
}
```

## Runtime behavior

Boot discovers `io.github.linpeilie.mapstruct.autoconfigure.MapstructAutoConfiguration` through
`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`. No `@Import` or
additional component scan is needed. Generated mappers and adapters are registered from their existing
`META-INF/mapstruct-plus/module-mappers` manifests and participate in Spring's normal dependency injection.

Inject `io.github.linpeilie.Converter` to convert objects. Applications may independently provide their own
`Converter`, `ConverterFactory`, or `SpringContextUtils4Msp` bean; the corresponding default backs off.
To disable the integration, use Boot's `spring.autoconfigure.exclude` with the auto-configuration name above.

## Contributor verification

Build the complete reactor with JDK 17 or newer. Legacy modules retain their Java 8 bytecode target.
Run the starter contract tests with JDK 17:

```sh
mvn -B -ntp -pl mapstruct-plus-spring-boot-starter,mapstruct-plus-spring-boot-starter-4 -am \
  -Dtest=MapstructAutoConfigurationTest,ModuleMapperRegistrarTest \
  -Dsurefire.failIfNoSpecifiedTests=false test
```

For the Boot 4.1 compatibility case, use JDK 25:

```sh
mvn -B -ntp -pl mapstruct-plus-spring-boot-starter-4 -am \
  -Dspring-boot4.version=4.1.2 -Dtest=MapstructAutoConfigurationTest \
  -Dsurefire.failIfNoSpecifiedTests=false test
```
