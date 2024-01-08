---
title: 配置项
order: 6
category:
- 指南
description: MapStructPlus MapStructPlus配置项 configuration
---

MapStructPlus 提供了多个配置项，来指定生成转换接口时的一些行为。

## 使用方式

在需要进行配置的模块中，新建配置类，在该类上面增加注解：`@MapperConfig`，**在一个模块中，只能有一个有该注解的类**。
同时，还要注意**配置类一定要放在要生效的模块中**

例如：

```java
@MapperConfig(adapterClassName = "DemoConvertMapperAdapter",
    adapterPackage = "io.github.linpeilie.adapter",
    mapAdapterClassName = "DemoMapConvertMapperAdapter")
public class MapStructPlusConfiguration {
}
```

除此之外，配置属性还支持**增加编译参数**的方式，以 `-Akey=value` 的形式，传递给编译器。

例如，使用 Maven 时，可以在 `maven-compiler-plugin` 插件配置中，使用 `compilerArgs` 属性来配置传递，例如：

**且使用该方式配置优先级更高**，即，当该方式和配置类同时存在时，以该方式配置的属性为准。该功能从 `1.3.0` 开始支持。

示例：

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.8.0</version>
      <configuration>
        <source>${maven.compiler.source}</source>
        <target>${maven.compiler.target}</target>
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
        <compilerArgs>
          <arg>-Amapstruct.plus.adapterClassName=DemoConvertMapperAdapter</arg>
          <arg>-Amapstruct.plus.adapterPackage=io.github.linpeilie.adapter</arg>
          <arg>-Amapstruct.plus.mapAdapterClassName=DemoMapConvertMapperAdapter</arg>
        </compilerArgs>
      </configuration>
    </plugin>
  </plugins>
</build>
```

## 配置项

### mapperPackage

- **说明**：生成的 Mapper 转换接口的包名
- **类型**：`String`
- **默认值**：默认生成在要转换的类同包名下
- **对应编译参数**：`-Amapstruct.plus.mapperPackage`

### unmappedSourcePolicy

- **说明**：当来源类中没有对应属性时的策略，默认忽略
- **类型**：`ReportingPolicy`
- **可选项**：
  - `IGNORE`：忽略
  - `WARN`：打印警告日志
  - `ERROR`：抛出异常
- **默认值**：`IGNORE`
- **对应编译参数**：`-Amapstruct.plus.unmappedSourcePolicy`

### unmappedTargetPolicy

- **说明**：当目标类中没有对应属性时的策略，默认忽略
- **类型**：`ReportingPolicy`
- **可选项**：
  - `IGNORE`：忽略
  - `WARN`：打印警告日志
  - `ERROR`：抛出异常
- **默认值**：`IGNORE`
- **对应编译参数**：`-Amapstruct.plus.unmappedTargetPolicy`

### nullValueMappingStrategy

- **说明**：空值对象处理策略，默认返回空值
- **类型**：`NullValueMappingStrategy`
- **可选项**：
  - `RETURN_NULL`：返回空值
  - `RETURN_DEFAULT`：返回默认值
- **默认值**：`RETURN_NULL`
- **对应编译参数**：`-Amapstruct.plus.nullValueMappingStrategy`

### nullValuePropertyMappingStrategy

- **说明**：当属性值为 `null` 时应对的策略，默认设置 `null`
- **类型**：`NullValuePropertyMappingStrategy`
- **可选项**：
  - `SET_TO_NULL`：设置为 `null`
  - `SET_TO_DEFAULT`：设置为默认值
  - `IGNORE`：忽略
- **默认值**：`SET_TO_NULL`
- **对应编译参数**：`-Amapstruct.plus.nullValuePropertyMappingStrategy`

### builder

- **说明**：构造者模式配置，MapStruct 与 lombok 的 builder 一起使用时，会丢失父类属性，所以这里将默认使用构造者模式关闭
- **类型**：`Builder`
- **支持配置项**：
  - `buildMethod`：构造器创建要构建类型时的构造方法
  - `disableBuilder`：打开/关闭构造器，如果关闭，则只使用常规的 getters/setters
- **默认值**：
  - `buildMethod`：`build`
  - `disableBuilder`：`true`
- **分别对应的编译参数**：
  - `-Amapstruct.plus.builder.buildMethod`
  - `-Amapstruct.plus.builder.disableBuilder`

### adapterPackage

> since `1.2.3`

- **说明**：ConvertAdapterClass 和 MapConvertMapperAdapter 的包名
- **类型**：`String`
- **默认值**：io.github.linpeilie
- **对应编译参数**：`-Amapstruct.plus.adapterPackage`

### adapterClassName

> since `1.2.3`

- **说明**：ConvertAdapterClass 类名
- **类型**：`String`
- **默认值**：ConvertMapperAdapter
- **对应编译参数**：`-Amapstruct.plus.adapterClassName`

### mapAdapterClassName

> since `1.2.3`

- **说明**：MapConvertMapperAdapter 类名
- **类型**：`String`
- **默认值**：MapConvertMapperAdapter
- **对应编译参数**：`-Amapstruct.plus.mapAdapterClassName`

### autoConfigPackage

> since `1.3.6`

- **说明**：MapStructPlus 框架自动生成的配置类 --- `AutoMapperConfig`/`AutoMapMapperConfig` 所在的包路径
- **类型**：`String`
- **默认值**：io.github.linpeilie
- **对应编译参数**：`-Amapstruct.plus.autoConfigPackage`

### autoMapperConfigClassName

> since `1.3.6`

- **说明**：MapStructPlus 框架自动生成的的配置类（配置对象之间的转换）类名
- **类型**：`String`
- **默认值**：AutoMapperConfig
- **对应编译参数**：`-Amapstruct.plus.autoMapperConfigClassName`

### autoMapMapperConfigClassName

> since `1.3.6`

- **说明**：MapStructPlus 框架自动生成的配置类（配置Map与对象之间的转换）类名
- **类型**：`String`
- **默认值**：AutoMapMapperConfig
- **对应编译参数**：`-Amapstruct.plus.autoMapMapperConfigClassName`
