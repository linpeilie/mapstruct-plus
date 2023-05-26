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

## 配置项

### mapperPackage

- **说明**：生成的 Mapper 转换接口的包名
- **类型**：`String`
- **默认值**：默认生成在要转换的类同包名下

### unmappedSourcePolicy

- **说明**：当来源类中没有对应属性时的策略，默认忽略
- **类型**：`ReportingPolicy`
- **可选项**：
  - `IGNORE`：忽略
  - `WARN`：打印警告日志
  - `ERROR`：抛出异常
- **默认值**：`IGNORE`

### unmappedTargetPolicy

- **说明**：当目标类中没有对应属性时的策略，默认忽略
- **类型**：`ReportingPolicy`
- **可选项**：
  - `IGNORE`：忽略
  - `WARN`：打印警告日志
  - `ERROR`：抛出异常
- **默认值**：`IGNORE`

### nullValueMappingStrategy

- **说明**：空值对象处理策略，默认返回空值
- **类型**：`NullValueMappingStrategy`
- **可选项**：
  - `RETURN_NULL`：返回空值
  - `RETURN_DEFAULT`：返回默认值
- **默认值**：`RETURN_NULL`

### nullValuePropertyMappingStrategy

- **说明**：当属性值为 `null` 时应对的策略，默认设置 `null`
- **类型**：`NullValuePropertyMappingStrategy`
- **可选项**：
  - `SET_TO_NULL`：设置为 `null`
  - `SET_TO_DEFAULT`：设置为默认值
  - `IGNORE`：忽略
- **默认值**：`SET_TO_NULL`

### builder

- **说明**：构造者模式配置，MapStruct 与 lombok 的 builder 一起使用时，会丢失父类属性，所以这里将默认使用构造者模式关闭
- **类型**：`Builder`
- **支持配置项**：
  - `buildMethod`：构造器创建要构建类型时的构造方法
  - `disableBuilder`：打开/关闭构造器，如果关闭，则只使用常规的 getters/setters
- **默认值**：
  - `buildMethod`：`build`
  - `disableBuilder`：`true`

### adapterPackage

> since `1.2.3`

- **说明**：ConvertAdapterClass 和 MapConvertMapperAdapter 的包名
- **类型**：`String`
- **默认值**：io.github.linpeilie

### adapterClassName

> since `1.2.3`

- **说明**：ConvertAdapterClass 类名
- **类型**：`String`
- **默认值**：ConvertMapperAdapter

### mapAdapterClassName

> since `1.2.3`

- **说明**：MapConvertMapperAdapter 类名
- **类型**：`String`
- **默认值**：MapConvertMapperAdapter