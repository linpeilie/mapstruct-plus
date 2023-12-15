---
title: configuration
order: 6
category:
- Guide
description: MapStructPlus MapStructPlus配置项 configuration
---

MapStructPlus provides multiple configuration items to specify some behavior when the conversion interface is generated.

## How to use it

In the module that needs to be configured, create a new configuration class an annotate it with `@MapperConfig` annotation.

In a module, there can noly be one class with this annotation.

Also, note that **the configuration classes must be placed in the module to be effective**.

eg：

```java
@MapperConfig(adapterClassName = "DemoConvertMapperAdapter",
    adapterPackage = "io.github.linpeilie.adapter",
    mapAdapterClassName = "DemoMapConvertMapperAdapter")
public class MapStructPlusConfiguration {
}
```

In addition, the configuration property supports **adding compilation parameters** to the compiler in the form of `-Akey=value`.

For example, when using Maven, you can use the `compilerArgs` property in the `maven-compiler-plugin` plugin configuration to configure delivery, for example:

**And configuration in this way takes precedence**, that is, when the mode and configuration class exist together, the property configured in this way takes precedence. This feature is supported from `1.3.0`.

eg:

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

## Configuration Item

### mapperPackage

- **Description**：The package name of the generated Mapper transformation interfaces
- **Type**：`String`
- **Default**：The default is under the same package as the class to be converted.
- **Compile Parameter**：`-Amapstruct.plus.mapperPackage`

### unmappedSourcePolicy

- **Description**：Policy when there is no corresponding attribute in the source class
- **Type**：`ReportingPolicy`
- **Optional**：
  - `IGNORE`：ignore
  - `WARN`：print warning log
  - `ERROR`：throw exception
- **Default**：`IGNORE`
- **Compile Parameter**：`-Amapstruct.plus.unmappedSourcePolicy`

### unmappedTargetPolicy

- **Description**：Policy when there is no corresponding attribute in the target class
- **Type**：`ReportingPolicy`
- **Optional**：
  - `IGNORE`：ignore
  - `WARN`：print warning log
  - `ERROR`：throw exception
- **Default**：`IGNORE`
- **Compile Parameter**：`-Amapstruct.plus.unmappedTargetPolicy`

### nullValueMappingStrategy

- **Description**：Null object handing policy
- **Type**：`NullValueMappingStrategy`
- **Optional**：
  - `RETURN_NULL`：return null
  - `RETURN_DEFAULT`：return default value
- **Default**：`RETURN_NULL`
- **Compile Parameter**：`-Amapstruct.plus.nullValueMappingStrategy`

### nullValuePropertyMappingStrategy

- **Description**：Policy to deal with when the property value is `null`
- **Type**：`NullValuePropertyMappingStrategy`
- **Optional**：
  - `SET_TO_NULL`：setting is null
  - `SET_TO_DEFAULT`：setting is default value
  - `IGNORE`：ignore
- **Default**：`SET_TO_NULL`
- **Compile Parameter**：`-Amapstruct.plus.nullValuePropertyMappingStrategy`

### builder

- **Description**：Constructor mode configuration, MapStruct loses the parent class property when used with Lombok's builder, so the default constructor mode is turned off.
- **Type**：`Builder`
- **Optional**：
  - `buildMethod`：The constructor creates the constructor when the type is to be build
  - `disableBuilder`：Open/Close the constructor, and if closed, use only regular getters/setters
- **Default**：
  - `buildMethod`：`build`
  - `disableBuilder`：`true`
- **Compile Parameter**：
  - `-Amapstruct.plus.builder.buildMethod`
  - `-Amapstruct.plus.builder.disableBuilder`

### adapterPackage

> since `1.2.3`

- **Description**：The package name of ConvertAdapterClass and MapConvertMapperAdapter
- **Type**：`String`
- **Default**：io.github.linpeilie
- **Compile Parameter**：`-Amapstruct.plus.adapterPackage`

### adapterClassName

> since `1.2.3`

- **Description**：the class name of ConvertAdapterClass
- **Type**：`String`
- **Default**：ConvertMapperAdapter
- **Compile Parameter**：`-Amapstruct.plus.adapterClassName`

### mapAdapterClassName

> since `1.2.3`

- **Description**：the class name of MapConvertMapperAdapter
- **Type**：`String`
- **Default**：MapConvertMapperAdapter
- **Compile Parameter**：`-Amapstruct.plus.mapAdapterClassName`