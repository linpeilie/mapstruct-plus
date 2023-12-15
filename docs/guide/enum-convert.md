---
title: 枚举转换
order: 3
category:
- 指南 
description: MapStructPlus Map转为对象 map convert to class
---

> 当前特性从 1.2.2 开始支持

当需要进行枚举转换时（例如枚举转换为编码值，或者由编码转换为枚举），可以在目标枚举添加 `@AutoEnumMapper` 注解，
增加该注解后，在任意类型中需要转换该枚举时都可以自动转换。

使用该注解需要注意：**当前枚举必须有一个可以保证唯一的字段**，并在使用当前注解时，将该字段名，添加到注解提供的 `value` 属性中。

还有就是**枚举和使用枚举的类，要在同一个模块中**。

例如：

- 商品状态枚举（`Goods`）

```java
@Getter
@AllArgsConstructor
@AutoEnumMapper("state")
public enum GoodsStateEnum {
    ENABLED(1, "启用"),
    DISABLED(0, "禁用");

    private final Integer state;
    private final String desc;

}
```

在当前枚举中添加注解 `@AutoEnumMapper`，且指定唯一字段为 `state`。

- 商品类（`Goods`）

```java
@Data
@AutoMapper(target = GoodsVo.class, reverseConvertGenerate = false)
public class Goods {

    private GoodsStateEnum state;

}
```

- 商品VO对象（`GoodsVo`）

```java
@Data
public class GoodsVo {

    private Integer state;

}
```

- 测试类

```java
@Test
public void enumMapTest() {
    final GoodsVo goodsVo = converter.convert(goods, GoodsVo.class);
    System.out.println(goodsVo);
    Assert.equals(goodsVo.getState(), goods.getState().getState());

    final Goods goods2 = converter.convert(goodsVo, Goods.class);
    System.out.println(goods2);
    Assert.equals(goods2.getState(), GoodsStateEnum.ENABLED);
}
```

