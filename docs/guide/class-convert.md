---
title: 两个类之间的转换
icon: creative
order: 1
category:
- 指南
description: MapstructPlus两个类之间的转换及配置
---

## 简单转换

**要实现两个类之间的转换，只需要在其中一个类上增加注解 `@AutoMapper` ，配置 `target` 属性，指定目标类即可**。

例如：

```java
@AutoMapper(target = CarDto.class)
public class Car {
    // ...
}
```
该例子表示，会生成 `Car` 转换为 `CarDto` 的接口 `CarToCarDtoMapper` 及实现类 `CarToCarDtoMapperImpl`。在生成的转换代码中，源类型（`Car`）的所有可读属性将被复制到目标属性类型（`CarDto`）的相应属性中。

当一个属性与它的目标实体对应物具有相同的名称时，将会被隐式映射。

除此之外，MapstructPlus 会根据当前的默认规则，生成 `CarDto` 转换为 `Car` 的接口 `CarDtoToCarMapper` 及实现类 `CarDtoToCarMapperImpl`。如果不想生成该转换逻辑的话，可以通过注解的 `reverseConvertGenerate` 属性来配置。

## 自定义对象的属性自动转换

当要转换的类中，存在自定义类时，会自动寻找该类型的转换方法。

例如，分别有两组对象模型：汽车（`Car`）和座椅（`SeatConfiguration`），其中 `Car` 依赖于 `SeatConfiguration`。

分别对应对象如下：

- 汽车模型

::: code-tabs#java

@tab Car

```java
@AutoMapper(target = CarDto.class)
@Data
public class Car {
    private SeatConfiguration seatConfiguration;
}
```

@tab CarDto

```java 
@Data
public class CarDto {
    private SeatConfigurationDto seatConfiguration;
}
```

:::

- 座椅模型

::: code-tabs#java

@tab SeatConfiguration

```java
@Data
@AutoMapper(target = SeatConfigurationDto.class)
public class SeatConfiguration {
    // fields
} 
```

@tab SeatConfigurationDto

```java
@Data
public class SeatConfigurationDto {
    // fields
} 
```

:::

在上面的例子中，首先会生成 `CarToCarDtoMapper` 和 `SeatConfigurationToSeatConfigurationDtoMapper` 两个转换接口，并且在转换 `Car` 时，会自动使用 `SeatConfigurationToSeatConfigurationDtoMapper` 来对其中的座椅属性来进行转换。

## 引入自定义类型转换器

当不同类型的属性，想要按照自定义的规则进行转换时，可以有两种办法：

1. 通过 `@AutoMapping` 中配置的 `expression` 表达式配置
2. 自定义一个类型转换器，通过 `@AutoMapper` 的 `uses` 属性来引入

方式一可以参考下面的[表达式](#表达式)章节。

这里基于方式二，实现将 `String` 类型的属性，根据逗号分隔，转换为 `List<String>` 类型的属性：

首先，定义一个类型转换器 —— `StringToListString`：

```java
@Component
public class StringToListString {
    public List<String> stringToListString(String str) {
        return StrUtil.split(str);
    }
}
```

::: warning
- 类型转换器提供的类型转换方法，可以定义为 `static` 或 `nonstatic`的。
- 如果是基于 `SpringBoot` 的方式使用该框架，则类型转换器需要定义为 Spring 的一个 Bean。
:::

下一步，使用该类型转换器：

```java
@AutoMapper(target = User.class, uses = StringToListStringConverter.class)
public class UserDto {

    private String username;
    private int age;
    private boolean young;
    @AutoMapping(target = "educationList")
    private String educations;
    // ......
}
```

测试：

```java
@SpringBootTest
public class QuickStartTest {

    @Autowired
    private Converter converter;

    @Test
    public void ueseTest() {
        UserDto userDto = new UserDto();
        userDto.setEducations("1,2,3");

        final User user = converter.convert(userDto, User.class);
        System.out.println(user.getEducationList());    // [1, 2, 3]

        assert user.getEducationList().size() == 3;
    }
}
```

## 自定义属性转换

当两个类中属性存在不一致的场景时，例如名称、类型等不一致，可以进行自定义转换，通过在属性上面添加 `@AutoMapping`，来配置映射规则。

### 不同属性名称映射

`@AutoMapping` 注解中，提供了 `target` 属性，可以配置当前属性与目标类中 `target` 属性之间映射。

例如，`Car` 转换为 `CatDto` 时，`seatConfiguration` 属性与 `seat` 属性相对应：

```java
@AutoMapper(target = CarDto.class)
@Data
public class Car {
    @AutoMapping(target = "seat")
    private SeatConfiguration seatConfiguration;
}
```

`@AutoMapping` 注解中还提供 `source` 方法，该配置默认取当前属性的名称，之所以可以配置，是为了适应一种场景，当前类的某个属性，其内部的属性，转换为目标中的属性字段，则可以通过当前属性来配置。

例如：

::: code-tabs#java

@tab Goods

```java 
@Data
@AutoMapper(target = GoodsVo.class, reverseConvertGenerate = false)
public class Goods {

    @AutoMapping(source = "sku.price", target = "price")
    private Sku sku;

}
```

@tab GoodsVo

```java 
@Data
public class GoodsVo {

    private Integer price;

}
```

:::

### 指定时间格式转换

当时间类型（例如：`Date`、`LocalDateTime`、`LocalDate` 等等）需要和 `String` 通过指定时间格式进行转换时，可以通过 `@AutoMapping` 中的 `dateFormat` 来配置：

例如：

::: code-tabs#java

@tab Order

```java
@Data
@AutoMapper(target = OrderEntity.class)
public class Order {

    @AutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    @AutoMapping(dateFormat = "yyyy_MM_dd HH:mm:ss")
    private Date createTime;

    @AutoMapping(target = "orderDate", dateFormat = "yyyy-MM-dd")
    private String date;

}
```

@tab OrderEntity

```java
@Data
@AutoMapper(target = Order.class)
public class OrderEntity {

    @AutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String orderTime;

    @AutoMapping(dateFormat = "yyyy_MM_dd HH:mm:ss")
    private String createTime;

    @AutoMapping(target = "date", dateFormat = "yyyy-MM-dd")
    private LocalDate orderDate;

}
```

:::

### 指定数字格式转换

当数字类型（例如：`int`/`Integer` 等数字基本类型及包装类、`BigDecimal`）和 `String` 之间的转换需要指定数字格式，可以通过 `@AutoMapping` 的 `numberFormat` 来配置。

> 该格式需要 `java.text.DecimalFormat` 所支持

例如：

::: code-tabs#java

@tab Order

```java 
@Data
@AutoMapper(target = OrderEntity.class)
public class Order {

    @AutoMapping(numberFormat = "$0.00")
    private BigDecimal orderPrice;

    @AutoMapping(numberFormat = "$0.00")
    private Integer goodsNum;
    
}
```

@tab OrderEntity

```java 
@Data
@AutoMapper(target = Order.class)
public class OrderEntity {

    @AutoMapping(numberFormat = "$0.00")
    private String orderPrice;

    @AutoMapping(numberFormat = "$0.00")
    private String goodsNum;
    
}
```

:::

### 忽略指定属性的转换

当在进行转换时，需要忽略指定属性的转换，可以通过 `@AutoMapping` 的 `ignore` 来配置。

例如：

```java
@AutoMapper(target = CarDto.class)
@Data
public class Car {

    @AutoMapping(target = "wheels", ignore = true)
    private Wheels wheels;
    
} 
```

### 属性转换时的默认值

`@AutoMapping` 中的 `defaultValue` 可以指定在转换属性时，当属性为 `null` 时，转换到目标类中的默认值。

例如：

```java 
@Data
@AutoMapper(target = DefaultVo.class)
public class DefaultDto {

    @AutoMapping(defaultValue = "18")
    private Integer i;

    @AutoMapping(defaultValue = "1.32")
    private Double d;

    @AutoMapping(defaultValue = "true")
    private Boolean b;

}
```

### 表达式

在执行属性转换时，可以通过指定执行一段 Java 代码来进行转换操作，例如，对源对象中的某个属性进行转换后返回。

需要注意的是，在生成时，会直接将表达式插入到转换逻辑中，并不会验证其语法。

例如，将源对象中的 `List<String>` 属性，通过 `,` 拼接为字符串：

```java 
@AutoMapper(target = UserDto.class)
public class User {

    @AutoMapping(target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList;

}
```

## 反向属性映射配置

::: info
**在该文中，所有提到的源类指通过 `@AutoMapper` 注解的类；目标类指的是 `@AutoMapper` 中 `target` 属性指定的类型。**
:::

前面提到，当在一个类上面添加 `@AutoMapper` 注解时，默认情况下，除了会生成源类到目标类的转换接口，还会生成目标类到源类的转换接口和实现类，这里需要注意的是，默认情况下生成的该转换接口，并没有任何自定义配置，即使在源类中配置了 `@AutoMapping` 注解。

这里要实现目标类到源类的自定义转换配置，可以有两种方式：

1. 在目标类上面添加 `@AutoMapper` 注解。这是最建议的方式，当转换双方都有添加该注解时，便不会生成默认的转换接口，即按照自定义的规则进行生成。
2. 当目标类访问不到源类，或者项目规范不允许在目标类上面添加该种注解时，可以将自定义配置全部添加在源类中。这就是下面要介绍的**反向属性映射配置**。

框架中提供了 `@ReverseAutoMapping` 注解，该注解就是为了配置目标类到源类的自定义转换规则。

::: warning
这里需要注意的是，防止配置冲突，一旦添加 `@ReverseAutoMapping` 注解，在目标类中，便不能添加任何自定义转换注解
:::

**`@ReverseAutoMapping` 注解表示的含义，是目标类到源类转换时，需要指定的自定义转换规则，其中可以配置的属性，与 `@AutoMapping` 注解一致。**

这里有两个属性需要注意，分别是 `source` 和 `target`。

**这里的 `source` 指的是目标类中的属性，`target` 指的是源类中的属性。**

可能会有人这里有疑问，为什么这里的配置像是反的？如果没有，可以直接跳过。

框架设计的时候，所有的属性转换配置，都是基于要转换的类型，该类转换为目标类，想要应用的效果。这里的 `source` 也应该是来源类中的属性。

如果还是不理解，这里可以认为，该注解就是本该应用在目标类中的 `@AutoMapping` 注解，原封不动拷贝到当前类，再修改注解名称即可。

