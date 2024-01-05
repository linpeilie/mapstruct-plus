---
title: FAQ
order: 7
category:
- Guide
description: MapStructPlus MapStructPlus常见问题 faq
---

#### Where to view the generated mapper interfaces and implemation.

In the compiled `target/generated-sources` directory, If do not have this directory, need to configure the IDEA Show Excluded Files.

#### "cannot find converter from xxx to xxx"

1. It is recommended that you re-examine your dependencies and configuration against the [Quick Start](/en/introduction/quick-start.html) section.
2. If Lombok is used in your project, follow the [Guide - FAQ#Integration with Lombok](/en/guide/faq.html) to configure it.
3. Reload all maven projects.
4. mvn clean compile
5. Under the `/target/generated-sources` directory, see if the conversion interface generated, if not, contact the author.
6. If it has already been generated, make sure that SpringBoot scans to the package where the generated interface is located!
7. Check for dependencies on `spring-boot-devtools`, which modifies the class's ClassLoader! This causes the matching class to fail, and not only the framework, but the tool can also cause other puzzling problems! Remove the dependency.

#### Diretory rules for the generated converter interfaces and implementation class

By default, generated under the same package name as the source class, you can specify diirectory through [Configuration](/en/guide/configuration.html)

> Note that if it is an external dependency package, it will also be generated under the same package as the external dependency class, causing Spring not to scan it, in which case it is recommended to specify a specific directory.

#### "Couldn't retrieve @Mapper annotation" Error

This exception is due to MapStruct dependency conflicts, and since MapStructPlus already relies on MapStruct, there is no need to add MapStruct dependency when using it.

It is also recommended that other dependencies, such as the springfox-swagger2 dependency on MapStruct, be eliminated.

Once you've eliminated the dependencies, re-execute clean compile

#### "NoSuchMethodError: …… io.github.linpeilie.ConvertMapperAdapter.xxxMethod" Error

When all the converter class are generated correctly, an exception of `NoSuchMethodError` is thrownat execution time, but in the generated code, the method exists in the `ConverterMapperAdapter` class.

When this exception occurs, it is because there are multiple modules, and there are dependencies between the modules, and in each module, there are classes that need to be converterd. 
So at build time, `io.github.linpeilie.ConvertMapperAdapter` is generated in each module, because the ClassLoader only loads one class with the same name and package when it loads the class, when it actually executes, it doesn't actually call the class we want. Hence the `NoSuchMethod` exception.

**Solution**: add a configuration class for MapStructPlus to each module, specifying a different path for the `adapterPackage` package in each module.

#### Integration with `lombok`

In line with how MapStruct integrates lombok

##### before lombok 1.18.16：

:::: code-group
::: code-group-item Maven

```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>${lombok.version}</version>
                    </path>
                    <path>
                        <groupId>io.github.linpeilie</groupId>
                        <artifactId>mapstruct-plus-processor</artifactId>
                        <version>${mapstruct-plus.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

:::

::: code-group-item Gradle

```groovy
dependencies {
    annotationProcessor group: 'org.projectlombok', name: 'lombok', version: {lombok.version}
    annotationProcessor group: 'io.github.linpeilie', name: 'mapstruct-plus-processor', version: ${mapstruct-plus.version}
}
```

:::
::::

##### lombok 1.18.16 et seq：

:::: code-group
::: code-group-item Maven

```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>${lombok.version}</version>
                    </path>
                    <path>
                        <groupId>io.github.linpeilie</groupId>
                        <artifactId>mapstruct-plus-processor</artifactId>
                        <version>${mapstruct-plus.version}</version>
                    </path>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok-mapstruct-binding</artifactId>
                        <version>0.2.0</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```
:::
::: code-group-item Gradle

```groovy
dependencies {
    annotationProcessor group: 'org.projectlombok', name: 'lombok', version: {lombok.version}
    annotationProcessor group: 'io.github.linpeilie', name: 'mapstruct-plus-processor', version: ${mapstruct-plus.version}
    annotationProcessor group: 'org.projectlombok', name: 'lombok-mapstruct-binding', version: '0.2.0'
}
```

:::
::::

#### When developed locally, error reporting is started after the class is modified and returned to normal after mvn clean compile

The problem is that no configuration class was found due to the IDEA partial compilation.
Update the latest version and change the configuration. See [Guide - Configuration](/en/guide/configuration.html) for details.