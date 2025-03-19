---
title: 更新日志
order: 1
category:
- 更新日志
description: MapStructPlus release log
---

### 1.4.8

- Fixed the issue where the generated conversion implementation class conflicted when using the default `Eclipse jdt.core` environment in Vscode;
- Repackaged `javapoet` to prevent conflicts with other projects.
- Upgraded spring-boot-autoconfigure version from 2.7.9 to 2.7.18.

### 1.4.6

- Modify the `SpringContextUtils` class name to prevent conflicts with other project class names;
- fix [#108](https://github.com/linpeilie/mapstruct-plus/issues/108) :  SpringContextUtils#getBeanFactory method changes to static;
- [PR](https://github.com/linpeilie/mapstruct-plus/pull/114) : increase annotation batch `AutoMapping`、`AutoMappings`、`ReverseAutoMapping`、`ReverseAutoMappings` features.

### 1.4.5

- fix: fiexed an issue where the `ReverseAutoMapping` configuration did not take effect.

### 1.4.4

- fix: fixed a problem with missing classes in some Spring release version.

### 1.4.3

- **feat**: Added `spring-lazy` option to `ComponentModel` for lazy loading Spring Beans, resolving mutual dependency issues, and set this option as the default configuration.
- **fix**: Fixed the issue where the default configuration for `unmappedTargetPolicy` was not effective.
- **enhance**: Optimized IDEA local development build efficiency, reducing build time and metaspace usage to some extent.[Issue #89](https://github.com/linpeilie/mapstruct-plus/issues/89)

### 1.4.2

- **feat**: Added the `mapperNameSuffix` attribute to the `AutoMapper` annotation. This supports adding a suffix to the generated conversion interface name, and the reverse conversion interface will be effective under the default rules.
- **feat**: Adapted the `Mapper` annotation to support the following attributes: `unmappedSourcePolicy`, `unmappedTargetPolicy`, `typeConversionPolicy`, `collectionMappingStrategy`, `nullValueMappingStrategy`, `nullValueIterableMappingStrategy`, `nullValuePropertyMappingStrategy`, `nullValueCheckStrategy`, and `mappingControl`.
- **feat**: Adapted the `Mapping` annotation to support the following attributes: `constant`, `qualifiedBy`, `nullValueCheckStrategy`, `nullValuePropertyMappingStrategy`, and `mappingControl`.
- **feat**: Adapted MapStruct configuration to support the following attributes: `typeConversionPolicy`, `collectionMappingStrategy`, `nullValueIterableMappingStrategy`, `nullValueMapMappingStrategy`, `nullValueCheckStrategy`, `mappingControl`, `unexpectedValueMappingException`, and `suppressTimestampInGenerated`.
- **fix**: Resolved the issue of class name conflicts generated in different packages within the same module.
- **feat**: Added the `reverseConvertGenerate` attribute to the `AutoMapping` annotation to control whether to generate reverse conversion logic, adapting to more complex application scenarios.
- **fix**: Fixed the issue of conversion rule conflicts when both parent and child classes are configured in `targetClass`.
- **fix**: Resolved class name conflicts of configuration classes and proxy classes in different modules.
- **feat**: Added the `useEnums` attribute to `AutoMapper`, supporting manual configuration of required enums for conversion, solving the issue of automatic conversion of enums across modules.
- Optimized the logic for generating conversion interfaces.

### 1.4.0

- **Optimize complex object conversion logic, take up less meta-space! and faster!**
- Get rid of dependencies such as hutool, which currently only rely on MapStruct in the project.
- The adaptation object loop nesting scenario
- [feature#63](https://github.com/linpeilie/mapstruct-plus/pull/63) `AutoMapping`、`ReverseAutoMapping` supports `qualifiedByName`,`conditionQualifiedByName`,and `dependsOn` properties.
- [issue#I93Z2Z](https://gitee.com/easii/mapstruct-plus/issues/I93Z2Z) `AutoMappings` supports configuration on methods.

> Points to note for upgrading 1.4.0
> - 1.4.0 and later versions, complex object comparisons reply on `ConvertMapperAdapter` generated in the project,
    which may cause [`NoSuchMethodError`](/guide/faq.html) exceptions under multiple modules because the Class Loading mechanism
    will load only one, of course, this problem has been around before, and the odds are probably lower,
    so be sure to configure the `adapterPackage` to avoid this problem with multiple modules.
> - Map to object conversions still rely on class conversions in hutool, and additional `hutool-core` dependencies need to be introduced if this
    functionality is required.

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