---
title: 更新日志
order: 1
category:
- 更新日志
description: MapStructPlus release log
---

### 1.4.4

- fix: 修复部分Spring版本下找不到类的问题

### 1.4.3

- feat: `ComponentModel` 增加 `spring-lazy` 可选项，懒加载 Spring Bean，解决互相依赖的问题，并将默认配置改为该选项；
- fix: 解决 `unmappedTargetPolicy` 默认配置不生效的问题；
- enhance: 优化 IDEA 本地开发构建效率，一定程度上缩短构建时间、减小元空间占用；[Issue #89](https://github.com/linpeilie/mapstruct-plus/issues/89)

### 1.4.2

- feat: `AutoMapper` 注解增加 `mapperNameSuffix` 属性，支持配置生成的转换接口名称增加后缀，默认规则下生成的反向转换接口同时生效；
- feat : 适配 `Mapper` 注解的 `unmappedSourcePolicy`、`unmappedTargetPolicy`、`typeConversionPolicy`、`collectionMappingStrategy`、`nullValueMappingStrategy`、`nullValueIterableMappingStrategy`、`nullValuePropertyMappingStrategy`、`nullValueCheckStrategy`、`mappingControl` 属性；
- feat : 适配 `Mapping` 注解的 `constant`、`qualifiedBy`、`nullValueCheckStrategy`、`nullValuePropertyMappingStrategy`、`mappingControl`；
- feat : 适配 MapStruct 配置的 `typeConversionPolicy`、`collectionMappingStrategy`、`nullValueIterableMappingStrategy`、`nullValueMapMappingStrategy`、`nullValueCheckStrategy`、`mappingControl`、`unexpectedValueMappingException`、`suppressTimestampInGenerated` 属性；
- fix : 适配同一个模块中同类不同包生成类名冲突的问题；
- feat : `AutoMapping` 注解增加 `reverseConvertGenerate`，控制是否生成反向转换逻辑，适配更加复杂的应用场景；
- fix : 修复 `targetClass` 同时配置父类和子类时，转换规则冲突的问题；
- fix : 修复不同模块配置类、代理类类名冲突的问题；
- feat : `AutoMapper` 增加 `useEnums` 属性，支持手动配置转换时需要的枚举，解决跨模块枚举无法自动转换的问题；
- 优化转换接口生成逻辑；

### 1.4.0

- **优化复杂对象转换逻辑，占用元空间更小！性能更快！**
- 去除 hutool 等依赖，目前项目中只依赖了 MapStruct
- 适配对象循环嵌套场景
- [feature#63](https://github.com/linpeilie/mapstruct-plus/pull/63)`AutoMapping`、`ReverseAutoMapping` 支持 `qualifiedByName`、`conditionQualifiedByName` 和 `dependsOn` 属性
- [issue#I93Z2Z](https://gitee.com/easii/mapstruct-plus/issues/I93Z2Z)`AutoMappings` 支持配置在方法上面


> 升级 1.4.0 注意事项：
> - 1.4.0 及以后的版本，复杂对象比较依赖项目中生成的 `ConvertMapperAdapter`，
  在多模块下，由于类加载机制只会加载一个的原因，可能会导致 [`NoSuchMethodError`](/guide/faq.html) 的异常，
  当然，这个问题在之前也会有，几率可能低一些，所以多模块下，务必配置 `adapterPackage` 来避免该问题。
> - Map 与对象的转换，还是依赖 hutool 中的类转换实现，如果需要该功能，需要额外引入 `hutool-core` 依赖包。


### 1.3.6

- 兼容内部类转换
- feature : AutoMapping 注解中的 targetClass 支持配置父类
- [issue#I8QPRO](https://gitee.com/easii/mapstruct-plus/issues/I8QPRO) : 框架自动生成的 AutoMapperConfig 和 AutoMapMapper 包和类名支持配置
- [issue#I8T7EF](https://gitee.com/easii/mapstruct-plus/issues/I8T7EF) : 支持在父类中配置的 AutoMapping 注解 

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