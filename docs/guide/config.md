---
title: 配置
order: 4
---

# 配置

由于 Mapstruct Plus 是注解处理器，主要工作是在编译阶段完成的，只能通过注解的方式，来进行项目配置。

且需要注意的是，**所有的配置类，都必须与需要进行对象转换的类，在同一个模块包中，且只能定义一个**。

目前提供了两个配置类：

- `ComponentModelConfig`
- `MapperConfig`

## ComponentModelConfig

`ComponentModelConfig` **定义获取 Mapper 接口对象的注入方式**，只有一个属性 `componentModel`。
可以在与要转换对象同模块包下的任意类或者接口上使用。

目前 Mapstruct plus 中支持两种：`default` 和 `spring`。

当使用非 Spring 模式时，必须定义该配置，`componentModel=default`。

## MapperConfig

`MapperConfig` 注解定义 **Mapper 接口生成的规则**，目前只支持配置生成接口的包名
可以在与要转换对象同模块包下的任意类或接口上使用。

### mapperPackage

指定生成的 Mapper 包名。

当没有指定该目录时，会在指定 `AutoMapper` 注解的类同级目录下，生成 Mapper 接口
