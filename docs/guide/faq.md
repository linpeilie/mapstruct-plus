---
title: 常见问题
order: 8
category:
- 指南
description: MapStructPlus MapStructPlus常见问题 faq
---

## 下载不到依赖

- 近期阿里maven源一直没有同步最新的依赖包，建议换成腾讯maven源，参考[使用腾讯云镜像源加速maven](https://mirrors.tencent.com/help/maven.html)
- 1.3.3 腾讯源同步的jar包是错误的，升级为最新版本

## 生成的转换接口与转换类在哪里查看

在编译后的 target/generated-sources 目录下，如果没有该目录，则需要配置 IDEA 展示排除的文件（Show Excluded Files）

## "cannot find converter from xxx to xxx" / 没有生成转换接口

1. 建议按照[快速开始](/introduction/quick-start.html)重新查看一下自己的依赖和配置是否齐全，
   如果项目中使用了 Lombok，则按照[指南-常见问题#与Lombok整合](/guide/faq.html)来进行配置；
2. 重新加载 Maven 依赖（Reload All Maven Projects）
3. mvn clean compile
4. 在 /target/generated-sources 目录下，查看是否生成转换接口。
5. 如果没有生成，联系作者
6. 如果已经生成，需要确认 SpringBoot 能否扫描到生成接口所在的包！
7. 检测是否依赖 `spring-boot-devtools`，该工具会修改类的 `ClassLoader`！导致匹配类失败，不止该框架，该工具还会引发其他莫名奇妙的问题！去掉该依赖即可。

## 生成的转换接口及实现类的目录规则

默认情况下，会在生成在源类同包名下，可以通过[配置](/guide/configuration.html)来指定具体的目录。

> 需要注意，如果是外部依赖包，也会生成在外部依赖类所在的同名包下，导致 Spring 扫描不到，这种情况下，建议指定具体的目录。

## "Couldn't retrieve @Mapper annotation" 异常

该异常是因为 MapStruct 依赖冲突导致的，由于 MapStructPlus 中已经依赖了 MapStruct，所以在使用时无需再添加 MapStruct 的依赖。
同时，建议其它依赖中的 MapStruct，也建议排除掉，比如 springfox-swagger2 中就依赖了 MapStruct。

排除完依赖后，重新执行 clean compile

## "NoSuchMethodError: …… io.github.linpeilie.ConvertMapperAdapter.xxxMethod" 异常

当所有转换类都能正常生成，在执行时，抛出 `NoSuchMethodError` 异常，但看生成的代码中，`ConvertMapperAdapter` 类中是存在该方法的。

当出现该异常时，是因为存在多个模块，且模块之间存在依赖关系，每个模块中，都有需要转换的类，
故在生成时，每个模块中，都生成了 `io.github.linpeilie.ConvertMapperAdapter` 这个类，
由于类加载器在加载类时，同名同包的类，只会加载一个，所以在具体执行的时候，实际上调用的不是我们想要的那个类。故出现 `NoSuchMethod` 异常。

**解决方案**：为每个模块中添加 MapStructPlus 的配置类，每个模块中指定 `adapterPackage` 包不同的路径。

## 与 `lombok` 整合

与 Mapstruct 整合 lombok 的方式一致。

### lombok 1.18.16 之前：

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

### lombok 1.18.16 及以后：

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

## 本地开发时，修改类后启动报错，mvn clean compile 后又恢复正常

该问题是由于 IDEA 部分编译导致没有找到配置类导致的，先更新最新版本，更换配置方式，详情可以查看[指南 - 配置](/guide/configuration.html)