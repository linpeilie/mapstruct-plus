---
title: Map 转对象
order: 2
category:
- 指南
description: MapStructPlus Map转为对象 map convert to class
---

MapStructPlus 提供了更加强大的 `Map<String, Object>` 转对象的功能。

::: warning
MapStructPlus 1.4.0 及以后版本，不再内置 [Hutool](https://hutool.cn) 框架，如果需要用到该功能时，需要额外引入 `hutool-core` 依赖。
:::

## 使用

### 添加依赖

> 1.4.0 及以后的版本需要添加该依赖，1.4.0之前的版本内置 hutool，不需要额外添加。

```xml
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-core</artifactId>
    <version>${hutool.version}</version>
</dependency>
```

### 添加注解

**当想要自动生成 `Map<String, Object>` 转为目标类的接口及实现类时，只需要在目标类上添加 `@AutoMapMapper` 注解**。

## 支持的 value 类型

- `String`
- `BigDecimal`
- `BigInteger`
- `Integer`
- `Long`
- `Double`
- `Boolean`
- `Date`
- `LocalDateTime`
- `LocalDate`
- `LocalTime`
- `URI`
- `URL`
- `Calendar`
- `Currency`
- `自定义类（自定义类也需要增加 @AutoMapMapper 注解）`

## 转换逻辑

**针对目标类中的一个属性，首先会判断 `Map` 中是否存在该键，如果存在的话，首先判断类型，如果相同类型，直接强转，如果不同类型，则会基于 [Hutool](https://hutool.cn/docs/#/core/%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2/%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2%E5%B7%A5%E5%85%B7%E7%B1%BB-Convert) 提供的类型转换工具尝试转换为目标类型**。

**且同时支持内部嵌套 `Map<String, Object>` 属性转为内部嵌套的自定义类型属性**。

## 示例

- 定义两个对象：`MapModelA` 和 `MapModelB`

:::: code-group
::: code-group-item MapModelA
```java
@AutoMapMapper
@Data
public class MapModelA {

    private String str;
    private int i1;
    private Long l2;
    private MapModelB mapModelB;

}
```
:::
::: code-group-item MapModelB
```java
@AutoMapMapper
@Data
public class MapModelB {

    private Date date;

}
```
:::
::::

- 转换测试

```java 
@SpringBootTest
public class QuickStartTest {

    @Autowired
    private Converter converter;

    @Test
    public void test() {
        Map<String, Object> mapModel1 = new HashMap<>();
        mapModel1.put("str", "1jkf1ijkj3f");
        mapModel1.put("i1", 111);
        mapModel1.put("l2", 11231);

        Map<String, Object> mapModel2 = new HashMap<>();
        mapModel2.put("date", DateUtil.parse("2023-02-23 01:03:23"));

        mapModel1.put("mapModelB", mapModel2);

        final MapModelA mapModelA = converter.convert(mapModel1, MapModelA.class);
        System.out.println(mapModelA);  // MapModelA(str=1jkf1ijkj3f, i1=111, l2=11231, mapModelB=MapModelB(date=2023-02-23 01:03:23))
    }
}
```

## 自定义转换器

自 1.5.2 起，`@AutoMapMapper` 的类型转换器可完全自定义，摆脱 hutool 依赖。
支持三级优先级配置，从高到低依次为：

### 1. 类级覆盖 —— `@AutoMapMapper(use = ...)`

单个类指定自定义转换器，仅对该类生效：

```java
public class MyConverter implements MapObjectConverter {
    @Override
    public String objToString(Object obj) {
        // 自定义实现
    }
    // ... 实现其余方法
}

@AutoMapMapper(use = MyConverter.class)
public class MyModel {
    // ...
}
```

### 2. 全局默认 —— `@MapperConfig(mapObjectConverter = ...)`

一次配置，所有 `@AutoMapMapper` 自动继承（也可通过编译参数 `-Amapstruct.plus.mapObjectConverter=com.example.MyConverter` 配置）：

```java
@MapperConfig(mapObjectConverter = MyConverter.class)
public class MapStructPlusConfiguration {
}
```

### 3. 内置默认

不指定任何转换器时，回退到 `HutoolMapObjectConverter`（需引入 hutool-core），与旧版本行为一致。

::: tip
自定义转换器需实现 `MapObjectConverter` 接口，并提供公开无参构造函数。
当使用自定义转换器时，无需引入 hutool-core 依赖。
:::

::: warning
当使用 Spring/Spring Boot 组件模型（默认）时，自定义转换器需注册为 Spring Bean（如添加 `@Component` 注解）。
内置的 `HutoolMapObjectConverter` 已由框架自动注册。
:::
