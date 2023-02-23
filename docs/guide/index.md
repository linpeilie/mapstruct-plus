---
nav: 指南
title: 简介
order: -1
---

# 简介

> 首先，先了解一下 Mapstruct：
> [Mapstruct](https://mapstruct.org) 是一个代码生成器，通过定义类转换的接口，自动实现属性转换的具体逻辑。主要为了简化 Java 类型之间转换的实现。

Mapstruct Plus 是 Mapstruct 的增强工具，在 Mapstruct 的基础上，实现了自动生成 Mapper 接口的功能，并强化了部分功能，使 Java 类型转换更加便捷、优雅。

和 Mapstruct 一样，本质上都是一个基于 JSR 269 的 Java 注释处理器，因此可以由 Maven、Gradle、Ant 等来构建触发。

Mapstruct Plus 内嵌 Mapstruct，和 Mapstruct 完全兼容，如果之前已经使用 Mapstruct，可以无缝替换依赖。
