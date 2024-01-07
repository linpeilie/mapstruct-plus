---
title: 更新日志
order: 1
category:
- 更新日志
description: MapStructPlus release log
---

### 1.3.6

- Compatible with internal class conversion.
- The targetClass in the AutoMapping annotation supports configuring the parent class.
- AutoMapperConfig and AutoMapMapperConfig package and class name generated automatically by the framework support configuration.
- Supports AutoMapping annotations configured in the parent class.

### 1.3.5

- AutoMapping、ReverseAutoMapping 支持配置在方法上面；
- AutoMapping、ReverseAutoMapping 支持 defaultExpression 和 conditionExpression 属性

### 1.3.4

……什么都没更新，腾讯云maven源同步的jar有问题，只能重新发个新包

### 1.3.3

- fixbug: 修复 win JDK8 编译报错问题

### 1.3.2

- 不可变对象支持，可以使用任意包下的 `Immutable` 标注类型为不可变类
- 全面适配 IDEA 部分编译问题，使用更加流畅丝滑

### 1.3.1

- 增加编译参数中指定配置类的功能
- 更好地适配 IDEA 部分编译场景

### 1.3.0

- fix: 解决本地开发时 IDEA 编译或者运行时报错等与预期不一致的问题
- feature: AutoMapper 注解增加 imports 属性支持

### 1.2.5

- fix: 解决 MapConvertMapperAdapter 编译警告问题
- feat: 增加 `nullValueMappingStrategy` 和 `nullValuePropertyMappingStrategy` 配置项
- feat: 适配 solon

### 1.2.4

- fixbug: 修复当项目中只有 AutoMappers 注解时，没有生成转换接口的问题

### 1.2.3

- MapStructPlus 版本由 `1.5.3.Final` 升级为 `1.5.5.Final`
- 增加自定义 `ConvertMapperAdapter` 和 `MapConvertMapperAdapter` 类名和包名的功能
- 生成的转换接口，自动接入自定义转换接口，具体[详见](/guide/class-convert.html#自动接入自定义转换接口)

### 1.2.2

- fixbug: 定义多个uses时的问题
- feature: 增加 `@AutoEnumMapper` 注解，可以在类型转换时，自动转换枚举

### 1.2.1

- 解决JDK17编译警告

### 1.2.0

- 增加unmappedSourcePolicy、unmappedTargetPolicy配置，并且设置unmappedTargetPolicy默认为ignore
- 增加Builder配置，并设置disableBuilder默认为true

### 1.1.8

- 解决当只有 @AutoMapMapper 注解时，没有生成转换器的问题
- 修改没有找到转换器时的异常描述

## 1.1.7

- fixBug: [issue#8](https://github.com/linpeilie/mapstruct-plus/issues/11) Converter 转换已有对象不生效的问题
- 添加寻找转换接口的缓存，转换速度更快

## 1.1.6

- 支持在添加 `AutoMapper` 的类中，配置目标类到当前类的转换规则，适配多种场景下的使用；
- `AutoMapper` 增加注解，提供可以配置是否生成转换接口的功能；
- `AutoMapping` 的 target 属性默认可以不填，不填则取当前字段
- 升级 mapstruct 版本为 1.5.3.FINAL

## 1.1.5

- `AutoMapping` 增加 `source` 和 `defaultValue` 属性支持

## 1.1.4

- 增加反向转换配置功能
- 解决树状结构转换bug

## 1.1.3

- 适配 SpringBoot3

## 1.1.1

- 增加 Map 转对象的功能
- 增加单个对象与多个对象转换并配置的功能