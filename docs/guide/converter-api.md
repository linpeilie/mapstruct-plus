---
title: 类型转换API
order: 6
---

# 类型转换 API

在 Mapstruct Plus 中，类型转换对外提供了一个接口：`Converter`

> `Converter` 类实际根据源类型和目标类型，寻找相应的 `Mapper` 接口，然后交由 `Mapper` 接口来转换。
> 其内部做了缓存处理，所以，建议全局只定义一个 `Converter` 对象。

其中提供了三个方法：

- `T convert(S source, Class<T> targetType)`
  将 `S` 类型的对象，转换为 `targetType` 类型的对象并返回

- `T convert(S source, T target)`
  将 `S` 类型的对象，按照配置的映射字段规则，给 `target` 类型的对象赋值，并返回 `target` 对象

- `List<T> convert(List<S> source, Class<T> targetType)`
  将 `S` 类型的集合，转换为 `targetType` 类型的集合并返回
