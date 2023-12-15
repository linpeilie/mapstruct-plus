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

