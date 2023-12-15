---
title: Map to Object
order: 2
category:
- Guide
description: MapStructPlus Map转为对象 map convert to class
---

MapStructPlus provides more powerful function for `Map<String, Object>` to object

## Usage

**Just add the `@AutoMapMapper` annotation to the target class when you want to automatically generate the interface and implementation classes that `Map<String, Object>` to the target class.**

## The supported value type

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
- `Custom classes(custom classes also require @AutoMapMapper annotation)`

## Transformation logic

**For an attribute in the target class, it first determintes whether the key exists in the Map.
If it does, it first determines the type, the conversion is attempted to the target type based on the type conversion tool provided by [Hutool](https://hutool.cn/docs/#/core/%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2/%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2%E5%B7%A5%E5%85%B7%E7%B1%BB-Convert)**

**It also supports internally nested `Map<String, Object` attributes to internally nested custom type attributes.**

## Example

- Define two class：`MapModelA` 和 `MapModelB`

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

- Test

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
