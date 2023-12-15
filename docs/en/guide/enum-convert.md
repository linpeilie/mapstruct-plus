---
title: 枚举转换
order: 3
category:
- 指南 
description: MapStructPlus Map转为对象 map convert to class
---

> The current feature is supported from 1.2.2

当需要进行枚举转换时（例如枚举转换为编码值，或者又编码转换为枚举），可以在目标枚举添加 `@AutoEnumMapper` 注解，
增加该注解后，在任意类型中需要转换该枚举时都可以自动转换。

When an enum conversion is required(such as an enum converted to an encoded value, or from an encoding to an enum), you can add the `@AutoEnumMapper` annotation to the target enum, with this annotation added, the enum can be converted automatically whenever it is needed in any type.

Note to use this annotation: **The current enum must have a field that is guaranteed to be unique**, and then using the current annotation, add the field name to the `value` attribute provided by the annotation.

**There are also enum and classes that use enum, whick must be in the same module.**

eg:

- `GoodsStateEnum`

```java
@Getter
@AllArgsConstructor
@AutoEnumMapper("state")
public enum GoodsStateEnum {
    ENABLED(1, "Enabled"),
    DISABLED(0, "Disabled");

    private final Integer state;
    private final String desc;

}
```

Add the annotation `@AutoEnumMapper` in the current enum and specify that the only field is `state`.

- `Goods`

```java
@Data
@AutoMapper(target = GoodsVo.class, reverseConvertGenerate = false)
public class Goods {

    private GoodsStateEnum state;

}
```

- `GoodsVo`

```java
@Data
public class GoodsVo {

    private Integer state;

}
```

- Test

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

