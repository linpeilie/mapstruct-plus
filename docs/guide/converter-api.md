---
title: 类转换 API
order: 5
category:
- 指南
description: Mapstruct MapStructPlus Converter Converter接口API converter api 
---

## Converter

前面的章节，都是介绍，在实体类上面配置转换逻辑，它们主要应用在编译阶段。在实际应用时，框架提供了 `Converter` 类，来执行具体的转换逻辑。

针对类型转换该类提供了如下方法：

- **`<S, T> T convert(S source, Class<T> targetType)`**

传入需要转换的对象（`source`）与目标类型（`targetType`），最终返回目标类型的实例对象。

- **`<S, T> T convert(S source, T target)`**

传入需要转换对象（`source`）与目标对象（`target`），最终将 `source` 中的属性，转换到 `target` 对象中，该方法与上面的区别在于，该方法返回的是传入的 `target` 对象，上面的方法返回的是一个新的对象。

- **`<S, T> List<T> convert(List<S> source, Class<T> targetType)`**

该方法会将一个源类型（`source`）的集合转换为目标类型（`targetType`）的集合

- **`<T> T convert(Map<String, Object> map, Class<T> target)`**

该方法会将一个 `Map<String, Object>` 转换为目标类型的实例对象。