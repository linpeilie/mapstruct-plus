---
title: 安装
order: 2
category:
- 介绍 
tag:
- 安装
description: MapStructPlus 依赖安装 install
---

::: warning
由于其已经内嵌 Mapstruct，为了防止不同版本之间的差异，请不要再引入 Mapstruct 相关依赖
:::

## 非 SpringBoot 环境

### Maven

```xml
<properties>
    <mapstruct-plus.version>最新版本</mapstruct-plus.version>
</properties>
<dependencies>
    <dependency>
        <groupId>io.github.linpeilie</groupId>
        <artifactId>mapstruct-plus</artifactId>
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
                <source>1.8</source>    <!-- 这里根据自己的需要进行切换 -->
                <target>1.8</target>    <!-- 这里根据自己的需要进行切换 -->
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
    implementation 'io.github.linpeilie:mapstruct-plus:最新版本'
    
    annotationProcessor 'io.github.linpeilie：mapstruct-plus-processor:最新版本'
}
```

## SpringBoot 环境

Spring Boot 4 使用独立的 `mapstruct-plus-spring-boot-starter-4`，要求 JDK 17 或以上。
请使用包含该模块的 MapStruct Plus 发布版本；它不能与旧 starter 同时引入。
下面的通用示例继续使用原有 starter；Boot 4 的完整配置见本节末尾。

### Maven

```xml
<properties>
    <mapstruct-plus.version>最新版本</mapstruct-plus.version>
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
                <source>1.8</source>    <!-- 这里根据自己的需要进行切换 -->
                <target>1.8</target>    <!-- 这里根据自己的需要进行切换 -->
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
    implementation 'io.github.linpeilie:mapstruct-plus-spring-boot-starter:最新版本'
    
    annotationProcessor 'io.github.linpeilie:mapstruct-plus-processor:最新版本'
}
```

### Spring Boot 4

在现有 Boot 4 parent / BOM 下，替换 starter 依赖，保留相同版本的注解处理器：

```xml
<dependency>
    <groupId>io.github.linpeilie</groupId>
    <artifactId>mapstruct-plus-spring-boot-starter-4</artifactId>
    <version>${mapstruct-plus.version}</version>
</dependency>
```

编译器使用 `release` 17 或项目自己的更高 Java 基线，并显式声明 `annotationProcessorPaths`。
JDK 23 起不能依赖类路径上的处理器被自动发现。运行时无需额外的 `@Import` 或组件扫描。
完整 Maven / Gradle 配置、自动配置覆盖方式与兼容检查见
[Boot 4 starter 手册](https://github.com/linpeilie/mapstruct-plus/tree/main/mapstruct-plus-spring-boot-starter-4)。
