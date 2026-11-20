---
title: Install
order: 2
category:
- Introduction
tag:
- Install
description: MapStructPlus 依赖安装 install
---

::: warning
Because it is already embedded with MapStruct, do not introduce MapStruct related dependencies to prevent differences between versions.
:::

## Non-SpringBoot environment

### Maven

```xml
<properties>
    <mapstruct-plus.version>latest version</mapstruct-plus.version>
</properties>
<dependencies>
    <dependency>
        <groupId>io.github.linpeilie</groupId>
        <artifactId>mapstruct-plus</artifactId>
        <version>{mapstruct-plus.version}</version>
    </dependency>
</dependencies>
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>    <!-- Here to switch according to their need -->
                <target>1.8</target>    <!-- Here to switch according to their need -->
                <annotationProcessorPaths>
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

### Gradle

```groovy
dependencies {
    implementation 'io.github.linpeilie:mapstruct-plus:latest version'
    
    annotationProcessor 'io.github.linpeilie：mapstruct-plus-processor:latest version'
}
```

## SpringBoot environment

Spring Boot 4 uses the separate `mapstruct-plus-spring-boot4-starter` and requires JDK 17 or newer.
Use a MapStruct Plus release containing that module and do not combine it with the existing starter.
The examples below keep the existing starter; the Boot 4 setup is described at the end of this section.

### Maven

```xml
<properties>
    <mapstruct-plus.version>latest version</mapstruct-plus.version>
</properties>
<dependencies>
    <dependency>
        <groupId>io.github.linpeilie</groupId>
        <artifactId>mapstruct-plus-spring-boot-starter</artifactId>
        <version>${mapstruct-plus.version}</version>
    </dependency>
</dependencies>
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>    <!-- Here to switch according to their need -->
                <target>1.8</target>    <!-- Here to switch according to their need -->
                <annotationProcessorPaths>
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

### Gradle

```groovy
dependencies {
    implementation 'io.github.linpeilie:mapstruct-plus-spring-boot-starter:latest version'
    
    annotationProcessor 'io.github.linpeilie:mapstruct-plus-processor:latest version'
}
```

### Spring Boot 4

Under the application's Boot 4 parent or BOM, replace the starter dependency and retain the processor
at the same MapStruct Plus version:

```xml
<dependency>
    <groupId>io.github.linpeilie</groupId>
    <artifactId>mapstruct-plus-spring-boot4-starter</artifactId>
    <version>${mapstruct-plus.version}</version>
</dependency>
```

Use compiler `release` 17 or the application's higher Java baseline and configure `annotationProcessorPaths`
explicitly. JDK 23 and newer do not discover processors implicitly from the classpath. No additional `@Import`
or component scan is needed at runtime. See the
[Boot 4 starter guide](https://github.com/linpeilie/mapstruct-plus/tree/main/mapstruct-plus-spring-boot4-starter)
for complete Maven / Gradle setup, bean overrides, and compatibility checks.

