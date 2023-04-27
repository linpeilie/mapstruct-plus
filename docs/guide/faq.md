---
title: 常见问题
order: 7
category:
- 指南
description: MapStructPlus MapStructPlus常见问题 faq
---

## 与 `lombok` 整合

与 Mapstruct 整合 lombok 的方式一致。

### lombok 1.18.16 之前：

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

### lombok 1.18.16 及以后：

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

## 生成的转换接口与转换类在哪里查看

在编译后的 target/generated-sources 目录下，如果没有该目录，则需要配置 IDEA 展示排除的文件（Show Excluded Files）

## 没有生成转换接口

1. 建议按照[快速开始](/introduction/quick-start.html)重新查看一下自己的依赖和配置是否齐全，
如果项目中使用了 Lombok，则按照[指南-常见问题#与Lombok整合](/guide/faq.html)来进行配置；
2. 重新加载 Maven 依赖（Reload All Maven Projects）
3. mvn clean compile

## 生成的转换接口及实现类的目录规则

默认情况下，会在生成在源类同包名下，可以通过[配置](/guide/configuration.html)来指定具体的目录。

> 需要注意，如果是外部依赖包，也会生成在外部依赖类所在的同名包下，导致 Spring 扫描不到，这种情况下，建议指定具体的目录。