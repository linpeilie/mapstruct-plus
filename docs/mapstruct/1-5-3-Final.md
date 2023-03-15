---
title: MapStruct 1.5.3.Final
order: 1
description: Mapstruct中文文档
category:
- MapStruct中文文档
tag:
- Mapstruct
- Mapstruct中文
---

这是 MapStruct 的参考文档，MapStruct 是一个基于注解处理器（annotation processor）的类转换器，它具有类型安全、高性能、没有其他依赖的特点。


这是 MapStruct 的参考文档，MapStruct 是一个基于注解处理器（annotation processor）的类转换器，它具有类型安全、高性能、没有其他依赖的特点。

## 介绍

MapStruct 是一个 Java 注解处理器，用于生成类型安全的 bean 映射类。

你所要做的就是定义一个 `mapper` 接口，在接口中声明所需要的映射方法。在编译期间，MapStruct 将生成这个接口的实现类。这个实现类基于纯 Java 方法，来执行源对象和目标对象的映射。

和手写映射代码相比，这种冗长且容易出错的代码，MapStruct 会自动生成，从而节省了时间。根据约定优于配置的原则，MapStruct 会使用合理的默认值，来生成这些转换代码，同时，还允许按照自己的方式，来配置或者实现特殊的行为。

与动态映射框架相比，MapStruct 具有以下优点：

- 通过普通方法调用，代替反射，执行速度更快；
- 编译时类型安全：只能映射相互映射的对象和属性，不会出现将一个 `OrderEntity` 映射到 `CustomerDTO` 这种意外情况。
- 当出现下面情况下，在构建的时候，会有清楚的错误报告：
    - 映射不完整（并非所有目标属性都被映射）
    - 映射不正确（找不到适当的映射方法或者类型转换）

## 安装

MapStruct 是一个基于 JSR 269 的 Java 注释处理器，因此可以在命令行构建（javac、Ant、Maven 等）中使用，也可以在 IDE 中使用。

它包含以下组件：

- `org.mapstruct:mapstruct`：包含所需要的注解，例如 `@Mapper`
- `org.mapstruct:mapstruct-processor`：包含生成 mapper 实现类的注解处理器

### Apache Maven

对于基于 Maven 的项目，在 pom 文件中添加以下内容：

::: details 例1：Maven 配置

```xml
...
<properties>
    <org.mapstruct.version>1.5.3.Final</org.mapstruct.version>
</properties>
...
<dependencies>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>${org.mapstruct.version}</version>
    </dependency>
</dependencies>
...
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
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>${org.mapstruct.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
...
```
:::

### Gradle

在 Gradle 构建文件中添加如下：

::: details 例2：Gradle 配置

```groovy
...
plugins {
    ...
    id "com.diffplug.eclipse.apt" version "3.26.0" // Only for Eclipse
}

dependencies {
    ...
    implementation "org.mapstruct:mapstruct:${mapstructVersion}"
    annotationProcessor "org.mapstruct:mapstruct-processor:${mapstructVersion}"

    // If you are using mapstruct in test code
    testAnnotationProcessor "org.mapstruct:mapstruct-processor:${mapstructVersion}"
}
...
```
:::

可以在 Github 中的 [mapstruct-example]([mapstruct-examples/mapstruct-on-gradle at main · mapstruct/mapstruct-examples (github.com)](https://github.com/mapstruct/mapstruct-examples/tree/main/mapstruct-on-gradle)) 查看完整的例子。

### Apache Ant

在 `build.xml` 文件中，添加如下 `javac` 任务配置：

其中的路径需要按照你项目的结构来调整。

::: details 例3：Ant 配置
```xml
...
<javac
    srcdir="src/main/java"
    destdir="target/classes"
    classpath="path/to/mapstruct-1.5.3.Final.jar">
    <compilerarg line="-processorpath path/to/mapstruct-processor-1.5.3.Final.jar"/>
    <compilerarg line="-s target/generated-sources"/>
</javac>
...
```
:::

可以在 Github 中的 [mapstruct-example]([mapstruct-examples/mapstruct-on-gradle at main · mapstruct/mapstruct-examples (github.com)](https://github.com/mapstruct/mapstruct-examples/tree/main/mapstruct-on-gradle)) 查看完整的例子。

### 配置选项

MapStruct 代码生成器可以使用注解处理器选项（annotation processor options）进行配置。

当直接调用 `javac` 时，可以以 `-Akey=value` 的形式，传递给编译器。

当通过 Maven 使用 MapStruct 时，任何选项都可以通过在 Maven 处理器插件中，使用 `compilerArgs` 属性来配置传递。如下图所示：

::: details 例4：Maven 配置
```xml
...
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.5.1</version>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <annotationProcessorPaths>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>${org.mapstruct.version}</version>
            </path>
        </annotationProcessorPaths>
        <!-- due to problem in maven-compiler-plugin, for verbose mode add showWarnings -->
        <showWarnings>true</showWarnings>
        <compilerArgs>
            <arg>
                -Amapstruct.suppressGeneratorTimestamp=true
            </arg>
            <arg>
                -Amapstruct.suppressGeneratorVersionInfoComment=true
            </arg>
            <arg>
                -Amapstruct.verbose=true
            </arg>
        </compilerArgs>
    </configuration>
</plugin>
...
```
:::

::: details 例5：Gradle 配置
```groovy
...
compileJava {
    options.compilerArgs += [
        '-Amapstruct.suppressGeneratorTimestamp=true',
        '-Amapstruct.suppressGeneratorVersionInfoComment=true',
        '-Amapstruct.verbose=true'
    ]
}
...
```
:::

有下列属性可以配置：

| 配置项 | 用途                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | 默认值 |
| ----- |---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| ---- |
| mapstruct. suppressGeneratorTimestamp | 如果设置为 `true`，则禁止在生成的 Mapper 实现类上面的 `@Generated` 注解中添加时间戳                                                                                                                                                                                                                                                                                                                                                                                                                        | false |
| mapstruct.verbose | 如果设置为 `true`，MapStruct 会打印其重要决策信息。需要注意的是，在使用 Maven是，还需要添加 `showWarnings` 这个属性，这是由于 maven-compiler-plugin 配置中的一个问题                                                                                                                                                                                                                                                                                                                                                               | false |
| mapstruct.suppressGeneratorVersionInfoComment | 如果设置为 `true` 的话，则禁止在生成的 Mapper 实现类上的 `@Generated` 注解中添加 `comment` 属性。该属性包含有关 `MapStruct` 版本和用于注释处理器的编译器信息                                                                                                                                                                                                                                                                                                                                                                       | false |
| mapstruct.defaultComponentModel | 生成的 Mapper 实现类时的组件模型（Component Model）名称（请参见[检索映射器](#检索映射器))章节。<br/><br/>支持属性如下：<br/> - `default`：Mapper 不使用组件模型，通常通过 `Mappers#getMapper(Class)` 来检索实例<br/> - `cdi`：生成的转换器是一个 CDI 应用 bean，可以通过 `@Inject` 检索。 - `spring`：生成的映射器是一个 Spring 的单例 bean，可以通过 `@Autowired` 来检索。- `jsr330`： 生成的映射器被 `@Named` 注解，可以通过 `@Inject`（javax.inject 或 jakarta.inject 包下，javax.inject 优先级更高） 来检索<br/> - `jakarta`：生成的映射器被 `@Named` 注解。<br/><br/>还可以通过 `@Mapper#componentModel()` 来配置具体的转换器，且优先级更高 | default |
| mapstruct.defaultInjectionStrategy | 通过 `uses` 属性使用的转换器，注入的方式。仅用于基于注解的组件模型，如 CDI、Spring、JSR 330。 <br/><br/> 支持配置的值如下：<br/> -`field`：通过属性的方式注入依赖 <br/> -`constructor`：将为实现类生成构造器，且通过构造器的方式注入依赖 <br/><br/> 当组件模型是 CDI 时，会生成一个默认的构造器。<br/> 该策略还可以通过 `@Mapper#injectionStrategy()` 来配置指定的 Mapper 接口，且优先级更高。                                                                                                                                                                                                              | field |
| mapstruct.unmappedTargetPolicy | 当目标属性没有基于来源值的填充方法（例如：setXxx）时，MapStruct 的默认报告策略。<br/><br/>支持如下值：-`Error`：任何未映射的目标属性都将导致映射代码生成失败 <br/> -`WARN`：在编译阶段任何未映射的目标属性都会造成一个异常 -`IGNORE`：忽略未映射的目标属性 <br/><br/> 该策略还可以通过 `@Mapper#unmappedTargetPolicy()` 为具体的映射器指定，且优先级更高。除此之外，还可以通过 `@BeanMapping#unmappedTargetPolicy()` 未特定的 bean 映射指定策略，该配置优先级最高。                                                                                                                                                                    | WARN |
| mapstruct.unmappedSourcePolicy | 当来源属性没有可以给目标属性填充值的方法时，MapStruct 的默认报告策略。<br/><br/>支持如下值：-`Error`：任何未映射的来源属性都将导致映射代码生成失败 <br/> -`WARN`：在编译阶段任何未映射的来源属性都会造成一个异常 -`IGNORE`：忽略未映射的来源属性 <br/><br/> 该策略还可以通过 `@Mapper#unmappedSourcePolicy()` 为具体的映射器指定，且优先级更高。除此之外，还可以通过 `@BeanMapping#unmappedSourcePolicy()` 未特定的 bean 映射指定策略，该配置优先级最高。                                                                                                                                                                            | WARN |
| mapstruct. disableBuilders | 如果设置为 true，那么在进行映射时 MapStruct 将不会使用构造器模式。这相当于为所有映射器添加了 `@Mapper(build = @Builder(disableBuilder = true))` 配置 | false |

### 将 MapStruct 与 Java 模块系统一起使用

> 模块系统（Module System）：Java 9 的新特性

MapStruct 可以与 Java 9 及更高版本一起使用。

> 原文：To allow usage of the @Generated annotation java.annotation.processing.Generated (part of the java.compiler module) can be enabled.

这一段不知道该如何翻译。但可以说一下大概想法。

`@Generated` JDK 11 之后被默认去除了，MapStruct 会根据运行环境，当 Java 11 及以后，会自动添加 `javax.annotation-api` 依赖包，从而使用该注解。

### IDE 整合

#### Intellij

Intellij IDEA 中可以安装 [MapStruct Support](https://plugins.jetbrains.com/plugin/10036-mapstruct-support) 插件，更好的使用 MapStruct。

该插件包含以下的特性：

- `target`、`source`、`expression` 自动提示
- 支持 `target`、`source` 直接跳转属性定义的地方
- 查找 `target`、`source` 属性的用法
- 支持重构
- 异常和快速修复

#### Eclipse 

Eclipse 同样提供了 [MapStruct Eclipse Plugin] 插件，以方便 MapStruct 的使用。

包含以下特定：

- `target` 和 `source` 代码提示
- 快速修复

## 定义一个 mapper

在本节，您将学习如何使用 MapStruct 定义 bean 映射器（mapper），以及可以配置哪些选项。

### 基本映射（Basic Mappings）

要创建一个映射器，只需要定义一个接口、需要的映射方法，及在该接口上面添加 `org.mapstruct.Mapper` 注解。

::: details 例6：通过 Java 接口的方式定义一个转换器

```java
@Mapper
public interface CarMapper {

    @Mapping(target = "manufacturer", source = "make")
    @Mapping(target = "seatCount", source = "numberOfSeats")
    CarDto carToCarDto(Car car);

    @Mapping(target = "fullName", source = "name")
    PersonDto personToPersonDto(Person person);
}
```

:::

在编译时，`@Mapper` 注解会使 MapStruct 的代码生成器，创建这个接口（`CarMapper`）的实现类。

在生成的方法实现中，源类型（`Car`）的所有可读属性都将复制到目标类型（`CarDto`）的相应属性中：

- 当源类型与目标类型有相同名称的属性时，将被隐式映射；
- 当属性在目标实体中是不同的名称时，可以通过 `@Mapping` 注解指定其名称。

> JavaBeans 规范中定义的属性名称，必须在 `@Mapping` 注解中指定，例如属性 seatCount 和其访问方法 `getSeatCount()`、`setSeatCount()`
> 
> 当指定 `@BeanMapping(ignoreByDefault = true)` 时，则不会隐式的进行属性转换，这意味着必须通过 `@Mapping` 指定所有映射关系，并且不会在缺少目标属性时发出任何警告。这允许忽略所有的除了 `@Mapping` 注解之外的字段。
> 
> 流式(fluent) setters 也是支持的。所谓流式 setters 是指 setters 方法返回当前实例。
> 
> 例如：
> 
> ```java
> public Builder seatCount(int seatCount) {
>     this.seatCount = seatCount;
>     return this;
> }
> ```

为了更好的理解 MapStruct 的功能，来看一下 MapStruct 生成的 `carToCarDto()` 实现方法：

::: details 例7：通过 MapStruct 代码生成的实现类

```java
// GENERATED CODE
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto carToCarDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        if ( car.getFeatures() != null ) {
            carDto.setFeatures( new ArrayList<String>( car.getFeatures() ) );
        }
        carDto.setManufacturer( car.getMake() );
        carDto.setSeatCount( car.getNumberOfSeats() );
        carDto.setDriver( personToPersonDto( car.getDriver() ) );
        carDto.setPrice( String.valueOf( car.getPrice() ) );
        if ( car.getCategory() != null ) {
            carDto.setCategory( car.getCategory().toString() );
        }
        carDto.setEngine( engineToEngineDto( car.getEngine() ) );

        return carDto;
    }

    @Override
    public PersonDto personToPersonDto(Person person) {
        //...
    }

    private EngineDto engineToEngineDto(Engine engine) {
        if ( engine == null ) {
            return null;
        }

        EngineDto engineDto = new EngineDto();

        engineDto.setHorsePower(engine.getHorsePower());
        engineDto.setFuel(engine.getFuel());

        return engineDto;
    }
}
```

:::

