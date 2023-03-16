---
title: Map 转对象
order: 2
category:
- 指南
description: MapStructPlus Map转为对象 map convert to class
---

MapstructPlus 提供了更加强大的 `Map<String, Object>` 转对象的功能。

## 使用

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

::: code-tabs#java

@tab MapModelA

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

@tab MapModelB

```java
@AutoMapMapper
@Data
public class MapModelB {

    private Date date;

}
```

:::

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
