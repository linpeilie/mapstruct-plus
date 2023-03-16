---
title: MapStruct 1.5.3.Final
order: 1
description: MapStruct中文文档 MapStruct MapStructPlus chinese document
category:
- MapStruct中文文档
tag:
- Mapstruct
- Mapstruct中文
---

这是 MapStruct 的参考文档，MapStruct 是一个基于注解处理器（annotation processor）的类转换器，它具有类型安全、高性能、没有其他依赖的特点。


这是 MapStruct 的参考文档，MapStruct 是一个基于注解处理器（annotation processor）的类转换器，它具有类型安全、高性能、没有其他依赖的特点。

## 1 介绍

MapStruct 是一个 Java 注解处理器，用于生成类型安全的 bean 映射类。

你所要做的就是定义一个 `mapper` 接口，在接口中声明所需要的映射方法。在编译期间，MapStruct 将生成这个接口的实现类。这个实现类基于纯 Java 方法，来执行源对象和目标对象的映射。

和手写映射代码相比，这种冗长且容易出错的代码，MapStruct 会自动生成，从而节省了时间。根据约定优于配置的原则，MapStruct 会使用合理的默认值，来生成这些转换代码，同时，还允许按照自己的方式，来配置或者实现特殊的行为。

与动态映射框架相比，MapStruct 具有以下优点：

- 通过普通方法调用，代替反射，执行速度更快；
- 编译时类型安全：只能映射相互映射的对象和属性，不会出现将一个 `OrderEntity` 映射到 `CustomerDTO` 这种意外情况。
- 当出现下面情况下，在构建的时候，会有清楚的错误报告：
    - 映射不完整（并非所有目标属性都被映射）
    - 映射不正确（找不到适当的映射方法或者类型转换）

## 2 安装

MapStruct 是一个基于 JSR 269 的 Java 注释处理器，因此可以在命令行构建（javac、Ant、Maven 等）中使用，也可以在 IDE 中使用。

它包含以下组件：

- `org.mapstruct:mapstruct`：包含所需要的注解，例如 `@Mapper`
- `org.mapstruct:mapstruct-processor`：包含生成 mapper 实现类的注解处理器

### 2.1 Apache Maven

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

### 2.2 Gradle

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

### 2.3 Apache Ant

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

### 2.4 配置选项

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

::: details 支持配置的属性

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
:::

### 2.5 将 MapStruct 与 Java 模块系统一起使用

> 模块系统（Module System）：Java 9 的新特性

MapStruct 可以与 Java 9 及更高版本一起使用。

> 原文：To allow usage of the @Generated annotation java.annotation.processing.Generated (part of the java.compiler module) can be enabled.

这一段不知道该如何翻译。但可以说一下大概想法。

`@Generated` JDK 11 之后被默认去除了，MapStruct 会根据运行环境，当 Java 11 及以后，会自动添加 `javax.annotation-api` 依赖包，从而使用该注解。

### 2.6 IDE 整合

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

## 3 定义一个 mapper

在本节，您将学习如何使用 MapStruct 定义 bean 映射器（mapper），以及可以配置哪些选项。

### 3.1 基本映射（Basic Mappings）

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

MapStruct 的基本理念就是让生成的代码，尽可能地看起来像是您亲手编写的代码。典型的是从源对象复制值到目标类型，通过普通的 `getter`/`setter` 调用，而不是通过反射或者其他类似的方式。

如示例所示，生成的代码考虑了通过 `@Mapping` 指定的任何名称直接的映射。如果需要映射的属性在源实体和目标实体中类型是不同的，MapStruct 要么应用一个自动转换（例如，对于 `price` 属性的转换，请参考[隐式类型转换](#隐式类型转换))，或者，调用/生成另一个映射方法（例如 `driver`/`engine` 属性的转换，请参考[转换对象引用](#转换对象引用))。
只有当源属性和目标属性是 Bean 的属性，并且它们本身是 Bean 或简单属性时，MapStruct 才会创建一个新的映射方法。比如，他们不是 `Collection` 或 `Map` 类型的属性。

对于元素类型相同的 `Collection` 集合，在执行映射时，会创建一个新的集合，并拷贝源对象的集合数据。对于元素类型不同的 `Collection` 集合，每个元素会单独映射，再添加到目标集合中。（详情可以参考[集合映射](#集合映射))

MapStruct 会对源类型和目标类型中所有公开（public)属性进行映射，包括定义在父类中的属性。

### 3.2 Mapping 组合（实验性）

MapStruct 支持使用元注解。`@Mapping` 注解除了支持配置在方法上面，还可以配置在注解上面。这允许通过其他（用户定义）的注解来重复利用 `@Mapping` 注解。例如：

```java
@Retention(RetentionPolicy.CLASS)
@Mapping(target = "id", ignore = true)
@Mapping(target = "creationDate", expression = "java(new java.util.Date())")
@Mapping(target = "name", source = "groupName")
public @interface ToEntity { }
```

这可以在不需要具有公共基本类型的基础上描述实体。例如下面的示例中，`ShelveEntity` 和 `BoxEntity` 在 `StorageMapper` 中不共享公共基本类型。

```java
@Mapper
public interface StorageMapper {

    StorageMapper INSTANCE = Mappers.getMapper( StorageMapper.class );

    @ToEntity
    @Mapping( target = "weightLimit", source = "maxWeight")
    ShelveEntity map(ShelveDto source);

    @ToEntity
    @Mapping( target = "label", source = "designation")
    BoxEntity map(BoxDto source);
}
```

然而，他们必须要有一些共同的属性。`@ToEntity` 注解假设所有的目标类型（`ShelveEntity` 和 `BoxEntity`）都包含属性："`id`"、"`createDate`"、"`name`"。此外，还假设所有的来源类型（`ShelveDto`、`BoxDto`）都有属性 "`groupName`"。
这个概念也被称为“鸭子类型“，换而言之，如果它像鸭子一样嘎嘎叫，像鸭子一样走路，那么它可能就是一只鸭子。

这个功能还属于实验特性。当出现异常情况时，描述信息并不完善：会直接显示出现问题的方法，以及 `@Mapping` 中的相关值。
然而，不能直接显示这个组合的影响方面。这些信息“好像”是 `@Mapping` 直接出现在相关方法上。因此，用户应该谨慎地使用这个特性，特别是不确定一个属性是否一直存在。

一种类型更安全（但也会更加啰嗦）的方式是定义一个基本类或者接口，目标类和源类继承该类，并且使用 `@InheritConfiguration` 注解，实现相同的结果（请参考[Mapping 配置继承](#Mapping 配置继承)）。

### 3.3 在转换类中添加自定义方法

在某些情况下，可能需要手动实现从一种类型映射为另一种类型的特性实现，这种实现是 MapStruct 无法生成的。处理这个问题的一种方式是在另一个类上实现自定义方法，然后由 MapStruct 生成的映射器来使用这个方法（请参考[执行其他映射器](#执行其他映射器)）

当 Java 8 或者之后的版本后，也可以选择另一种方法：可以直接在映射接口（mapper interface）中实现自定义方法。生成映射代码时，当参数和返回类型与该方法相同，则将默认调用该方法。

假如，当 `Person` 映射为 `PersonDto` 时，需要一些特殊的逻辑，而而抓拍逻辑无法由 MapStruct 来生成。基于前面的例子，通过在转换器接口中定义转换方法的方式，实现这个要求：

::: details 例8：在转换接口中定义默认的自定义映射方法

```java
@Mapper
public interface CarMapper {

    @Mapping(...)
    ...
    CarDto carToCarDto(Car car);

    default PersonDto personToPersonDto(Person person) {
        //hand-written mapping logic
    }
}
```

:::

MapStruct 生成的实现类中实现了 `carToCarDto()` 方法，在生成的这个方法中，当执行 `driver` 属性映射时，会执行手动实现的 `personToPersonDto()` 方法。

映射器除了接口的形式，也可以是抽象类的形式，可以直接在抽象映射器类中实现自定义方法。在这种情况下，MapStruct 会实现抽象类中的所有非抽象方法。这种方式比在接口中声明默认方法的优点是，可以在抽象类中声明其他属性。

在前面 `Person` 转换为 `PersonDto` 的特殊映射逻辑例子中，还可以像如下这样定义：

::: details 例9 定义抽象类映射器

```java
@Mapper
public abstract class CarMapper {

    @Mapping(...)
    ...
    public abstract CarDto carToCarDto(Car car);

    public PersonDto personToPersonDto(Person person) {
        //hand-written mapping logic
    }
}
```

:::

MapStruct 将会生成 `CarMapper` 的子类，并且实现其中的抽象方法 `carToCarDto()`。在生成的 `carToCarDto()` 代码中，映射 `driver` 属性时，会执行手动实现的 `personToPersonDto()` 方法。

### 3.4 多来源参数的映射方法

MapStruct 支持多个来源参数的映射方法。这很有用。例如将几个实体组合到一个数据传输对象中。示例如下：

::: details 多来源参数的映射方法

```java
@Mapper
public interface AddressMapper {

    @Mapping(target = "description", source = "person.description")
    @Mapping(target = "houseNumber", source = "address.houseNo")
    DeliveryAddressDto personAndAddressToDeliveryAddressDto(Person person, Address address);
}
```

:::

上面显示的映射方法中，接口了两个来源参数，并返回了一个组合的目标对象。与单参数映射方法一致，根据参数名称来映射其属性。

如果在多个来源对象中，都有定义相同名称的属性，那么在映射时，必须通过 `@Mapping` 来指定这个属性的来源参数。如下面的示例中 `description` 属性的转换。
当没有解决这种歧义问题时，将会报错。对于仅存在与给定的来源对象中的一个时，可以省略指定这个属性的来源参数，因为它可以自动确定。

::: warning
在使用 `@Mapping` 注解时，必须指定参数的来源属性。
:::

::: info
多来源参数映射中，当所有来源参数都为 `null` 时，方法最终会返回 `null`。
否则，会根据提供的源对象来生成目标对象。
:::

MapStruct 还支持直接使用来源参数进行转换。

::: details 例11：直接引用来源参数的转换方法
```java
@Mapper
public interface AddressMapper {

    @Mapping(target = "description", source = "person.description")
    @Mapping(target = "houseNumber", source = "hn")
    DeliveryAddressDto personAndAddressToDeliveryAddressDto(Person person, Integer hn);
}
```
:::

在这个例子中，来源参数直接映射到目标对象。参数 `hn` 是一个非 bean 类型（在这个例子是 `java.lang.Integer`）映射到 `houseNumber` 属性。

### 3.5 嵌套对象属性映射到目标属性

如果不想显式地命名来源对象中嵌套 bean 的属性，可以配置 `target` 参数为 `.`。这将告诉 MapStruct 映射转换每一个嵌套 bean 的属性到目标对象中。下面是示例：

::: details 例12：使用 `.` 来配置注解中的 target
```java
 @Mapper
 public interface CustomerMapper {

     @Mapping( target = "name", source = "record.name" )
     @Mapping( target = ".", source = "record" )
     @Mapping( target = ".", source = "account" )
     Customer customerDtoToCustomer(CustomerDto customerDto);
 }
```
:::

生成的代码将会直接映射 `CustomerDto.record` 中的每一个属性到 `Customer`，而不需要命名具体的参数。`Customer.account` 也是同样的道理。

当发生命名冲突时，可以通过显式定义映射关系来解决这些冲突。例如上面的例子中，`name` 属性同时存在于 `CustomerDto.record` 和 `CustomerDto.account` 中，`@Mapping(target = "name", source = "record.name")` 就是为了解决这个冲突的。

当多层级对象映射到平铺对象的时候（反之亦然 `@InheritInverseConfiguration`），这个特性非常有用.

### 3.6 修改已经存在的对象实例

有的时候，需要执行映射时，不返回一个新的对象，而是更新现有的对象实例。这种可以通过将已经存在的目标对象，添加到映射方法的参数中，并用 `@MappingTarget` 注解标注。
下面是一个例子：

::: details 例13：更改方法
```java
@Mapper
public interface CarMapper {

    void updateCarFromDto(CarDto carDto, @MappingTarget Car car);
}
```
:::

生成的 `updateCarFromDto()` 方法实现中，会使用给定的 `CarDto` 对象，来修改传递的 `Car` 实例。
这里可能只有一个参数标记为映射的目标对象。除了将映射方法的返回类型设置为 `void`，还可以设置返回为目标对象的类型，这种情况下，生成的实现方法中，会修改传入的映射目标对象，并将其返回。这样可以支持流式调用映射方法。

当目标属性类型是 `Collection` 或者 `Map`，当策略为 `CollectionMappingStrategy.ACCESSOR_ONLY` 时，将会先将集合清空（clear），再用源对象中的值填充。
除此之外，当策略为 `CollectionMappingStrategy.ADDER_PREFERRED` 或 `CollectionMappingStrategy.TARGET_IMMUTABLE` 时，目标属性的集合不会清空，且立即填充值。

### 3.7 直接访问属性的映射

MapStruct 同样支持 `public` 类型的字段（没有 getters/setters ）进行映射。
当找不到这些属性的 getter/setter 方法时，会使用这些字段进行读写。

只有当一个属性为 `public` 或 `public final` 时，才可以作为读访问器。
如果字段是 `static` 类型的话，不能够作为读访问器。

只有当一个属性为 `public` 时，才能作为写访问器。
如果字段是 `final` 或者 `static` 时，不能够作为读访问器。

示例：

::: details 例14：类映射示例
```java
public class Customer {

    private Long id;
    private String name;

    //getters and setter omitted for brevity
}

public class CustomerDto {

    public Long id;
    public String customerName;
}

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    @Mapping(target = "name", source = "customerName")
    Customer toCustomer(CustomerDto customerDto);

    @InheritInverseConfiguration
    CustomerDto fromCustomer(Customer customer);
}
```
:::

对于上面的配置，生成的映射器如下所示：

::: details 例15：生成的类映射器示例
```java
// GENERATED CODE
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(CustomerDto customerDto) {
        // ...
        customer.setId( customerDto.id );
        customer.setName( customerDto.customerName );
        // ...
    }

    @Override
    public CustomerDto fromCustomer(Customer customer) {
        // ...
        customerDto.id = customer.getId();
        customerDto.customerName = customer.getName();
        // ...
    }
}
```
:::

可以在 [mapstruct-example-field-mapping](https://github.com/mapstruct/mapstruct-examples/tree/master/mapstruct-field-mapping) 中查看完整的示例。

### 3.8 使用构造器

MapStruct 支持使用构造器来映射不可变的类型。在 MapStruct 执行映射时，会检查是否存在可以用于映射类型的构造器。
这是通过 `BuilderProvider` SPI 来实现的，如果存在的话，则会使用该构造器来映射。

`BuilderProvider` 默认实现假设如下「*这里可以认为是只有满足如下条件时，该类才会生效*」：

- 该类型有一个无参公共静态构造器创建方法，该方法返回一个构造器。例如，`Person` 有一个返回 `PersonBuilder` 的公共静态方法。
- 构造器类中有一个无参的公共方法（构造方法），该方法返回其内部构造的类型。例如示例中 `PersonBuilder` 有一个返回 `Person` 的方法。
- 当有多个构造方法时，MapStruct 将会寻找一个名为 `build` 的方法，如果存在就用该方法，否则创建一个编译异常。
- 特殊的构造方法，可以在 `@BeanMapping`、`@Mapper` 或 `@MapperConfig` 中提供的 `@Builder` 来指定。
- 当有多个满足条件的构造器创建方法存在时，`DefaultBuilderProvider` SPI 将会抛出一个 `MoreThanOneBuilderCreationMethodException` 异常。对于该异常，MapStruct 会在编译中写入「*记录*」警告，并且不适用任何生成器。

如果找到了这样子的构造器，MapStruct 会使用该类来执行映射（即使找到了该类提供的 setters）。MapStruct 会调用构造器的构造方法来生成映射代码。

::: warning
构造器检测的特性，可以通过 `@Builder.disableBuilder` 来关闭。当关闭后，MapStruct 将像以前一样使用常规的 getter/setter。
:::

::: warning
对于构造器类型创建，还可以使用使用对象工厂。例如，在 `PersonBuilder` 中存在一个对象工厂时，将会使用这个工厂类来代替构造器创建方法
:::

::: warning
构造器会影响 `@BeforeMapping` 和 `@AfterMapping` 行为，请参考章节 `Mapping customization with before-mapping and after-mapping methods`[定制化转换-转换前和转换后](#定制化转换-转换前和转换后) 来了解更多信息
:::

::: details 例16：Person 构造器示例
```java
public class Person {

    private final String name;

    protected Person(Person.Builder builder) {
        this.name = builder.name;
    }

    public static Person.Builder builder() {
        return new Person.Builder();
    }

    public static class Builder {

        private String name;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Person create() {
            return new Person( this );
        }
    }
}
```
:::

::: details 例17：定义 Person 转换器
```java
public interface PersonMapper {

    Person map(PersonDto dto);
}
```
:::

::: details 例18：基于构造器来生成转换器实现类
```java
// GENERATED CODE
public class PersonMapperImpl implements PersonMapper {

    public Person map(PersonDto dto) {
        if (dto == null) {
            return null;
        }

        Person.Builder builder = Person.builder();

        builder.name( dto.getName() );

        return builder.create();
    }
}
```
:::

支持的构造器框架：

- Lombok：~~需要将 Lombok 类放在单独的模块中。有关更多信息，可以查看 [lombok#1538](https://github.com/projectlombok/lombok/issues/1538)~~，并使用 MapStruct 设置 Lombok，请参阅 [Lombok](#Lombok)。

> 这里说的单独的模块，应该是 Lombok 之前的问题，现在已经解决，可以忽略。

- AutoValue
- Immutables：当注释处理器路径上存在 Immutables 时，默认使用 `ImmutablesAccessorNamingStrategy` 和 `ImmutablesBuilderProvider`。
- FreeBuilder：当注释处理器路径上存在 FreeBuilder 时，默认使用 `FreeBuilderAccessorNamingStrategy`。当使用 FreeBuilder 时，应当遵循 JavaBean 约定，否则 MapStruct 将无法识别流利的 getters。
- 同样适用于自定义的构造器（手写构造器），前提构造器实现支持 `BuilderProvider` 定义的规则。否则，需要编写一个自定义的 `BuilderProvider`。

::: warning
如果想要禁用构造器，那么可以将 MapStruct 处理器选项 `mapstruct.disablebuilders` 传递给编译期。例如：`-Amapstruct.disableBuilders=true`
:::

### 3.9 使用构造函数

MapStruct 支持使用构造函数来映射目标类型。当 MapStruct 执行映射时，会检查是否存在目标类型的构造器。
如果没有构造器，MapStruct 会寻找一个可访问的构造函数。当有多个构造函数时，将按照如下规则，选择一个构造函数来使用：

- 如果一个构造函数被 `@Default`（任意包都可以，参考[未列出注释](#未列出注释)） 注解标注，那么会使用该构造函数。
- 如果只存在一个公开的构造函数，则会使用它来构造对象，其他的非公开构造函数将被忽略。
- 如果存在无参构造函数，那么会用它来构造对象，而其他构造函数将被忽略。
- 如果存在多个符合条件的构造函数，优于模棱两可的构造函数将出现编译异常。为了打破歧义，可以使用 `@Default` 注解来标注。

::: details 例19：决定使用哪个构造函数
```java
public class Vehicle {

    protected Vehicle() { }

    // MapStruct will use this constructor, because it is a single public constructor
    public Vehicle(String color) { }
}

public class Car {

    // MapStruct will use this constructor, because it is a parameterless empty constructor
    public Car() { }

    public Car(String make, String color) { }
}

public class Truck {

    public Truck() { }

    // MapStruct will use this constructor, because it is annotated with @Default
    @Default
    public Truck(String make, String color) { }
}

public class Van {

    // There will be a compilation error when using this class because MapStruct cannot pick a constructor

    public Van(String make) { }

    public Van(String make, String color) { }

}
```
:::

