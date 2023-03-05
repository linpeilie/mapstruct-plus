---
title: 安装
order: 2
category:
- 介绍 
tag:
- 安装
description: MapstructPlus依赖安装
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
