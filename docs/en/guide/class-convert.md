---
title: Conversion between two classes
order: 1
category:
- Guide
description: MapStructPlus 两个类之间的转换及配置 class convert Conversion between two classes
---

## Simple Conversion

**To convert between two classes, simply add the annotation `@AutoMapper` to one of the classes, configure the `target` attribute, and specify the target class.**

eg：

```java
@AutoMapper(target = CarDto.class)
public class Car {
    // ...
}
```

This example shows that an interface `CarToCarDtoMapper` and implementation class `CarToCarDtoMapperImpl` will be generated for `Car` to `CarDto`.
In the generated conversion code, all readable attributes of the source type(`Car`) are copied to the corresponding attribute of the target attribute type(`CarDto`).

When an attribute has the same name as its target entity counterpart, it is implicitly mapped.

In addition, MapStructPlus generates the `CarDto` to `Car` interface `CarDtoToCarMapper` and the implementation class `CarDtoToCarMapperImpl` according to the current default rules.
If you do not want to generate the transformation logic, you can configure it by using the `reverseConvertGenerate` property of the annotation. 

## The properties of a custom object are automatically converted

When property by custom class exists in the class to be converted, the conversion method for that type is automatically found.

For example, there are two sets of object module: `Car` and `SeatConfiguration`, `Car` depends on `SeatConfiguration`

The corresponding objects are as follows：

- car

:::: code-group
::: code-group-item Car
```java
@AutoMapper(target = CarDto.class)
@Data
public class Car {
    private SeatConfiguration seatConfiguration;
}
```
:::
::: code-group-item CarDto
```java 
@Data
public class CarDto {
    private SeatConfigurationDto seatConfiguration;
}
```
:::
::::

- seat configuration

:::: code-group
::: code-group-item SeatConfiguration
```java
@Data
@AutoMapper(target = SeatConfigurationDto.class)
public class SeatConfiguration {
    // fields
} 
```
:::
::: code-group-item SeatConfigurationDto
```java
@Data
public class SeatConfigurationDto {
    // fields
} 
```
:::
::::

In the above example, the `CarToCarDtoMapper` and `SeatConfigurationToSeatConfigurationDtoMapper` conversion interfaces are generated,
and `SeatConfigurationToSeatConfigurationDtoMapper` is automatically used to convert the seat properties in the `Car` conversion.

## Introduces custom type converter

When different types of properties want to be converted according to custom rules, there are two ways:

1. Configuration through the `expression` configured in `@AutoMapping`
2. Customize a type converter, introduced through the 'uses' attribute of `@AutoMapping`

For mode one, refer to the section[expresions](#expresions) below.

This is based on mode two, where the implementation converts a `String` type attribute, separated by commas, to a `List<String>` type attribute:

First, define a type converter --- `StringToListString`: 

```java
@Component
public class StringToListString {
    public List<String> stringToListString(String str) {
        return StrUtil.split(str);
    }
}
```

::: warning
- Type converter provides type conversion methods thas can be defined as `static` or `nonstatic`.
- If you are using the framework based on the `SpringBoot` approach, the type converter need to be defined as a Spring Bean.
:::

Next, use is:

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

Test：

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

## Custom Property conversions

When there are inconsistent scenarios for attributes in the two classes, such as name, type, and so on, 
you can used to configure the mapping rules by add `@AutoMapping` to the attributes.

### Different attribute name mappings

In the `@AutoMapping` annotation, the `target` attribute is provided to configure the mapping between the current attribute and the `target` attribute in the target class.

For example, when `Car` is converted to `CarDto`, the `seatConfiguration` attribute corresponds to the `seat` attribute:

```java
@AutoMapper(target = CarDto.class)
@Data
public class Car {
    @AutoMapping(target = "seat")
    private SeatConfiguration seatConfiguration;
}
```

The `@AutoMapping` annotation also provides the `source` attribute, which by default takes the name of the current property 
and can be configured to fit a scenario there a property of the current class, its internal property, to a property field in the target, 
you can configure it with the current property.

eg：

:::: code-group
::: code-group-item Goods
```java 
@Data
@AutoMapper(target = GoodsVo.class, reverseConvertGenerate = false)
public class Goods {

    @AutoMapping(source = "sku.price", target = "price")
    private Sku sku;

}
```
:::
::: code-group-item GoodsVo
```java 
@Data
public class GoodsVo {

    private Integer price;

}
```
:::
::::

### Specifies the time format

When the time type(for example `Date`、`LocalDateTime`、`LocalDate`...) needs to be converted with `String` by specifying the time format,
you can configure it with `dateFormat` in `@AutoMapping`

eg：

:::: code-group
::: code-group-item Order
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
:::
::: code-group-item OrderEntity
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
::::

### Specifies a numeric format

When the conversion between a numeric type(for example `int`/`Integer` and the wrapper class、`BigDecimal`) and `String` requires a numeric format, 
it can be configured with `numberFormat` in `@AutoMapping`

> This format need to be supported by `java.text.DecimalFormat`

eg：

:::: code-group
::: code-group-item Order
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
:::
::: code-group-item OrderEntity
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
::::

### Ignore the specifies property

When the transformation is performed, the transformation of the specified property 
needs to be ignored, which can be configured using the `ignore` of `@AutoMapping`

es：

```java
@AutoMapper(target = CarDto.class)
@Data
public class Car {

    @AutoMapping(target = "wheels", ignore = true)
    private Wheels wheels;
    
} 
```

### default value

`defaultValue` in `@AutoMapping` can specify the default value to convert to the target class when the property value is null.

eg：

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

### expression

When you perform a property conversion, you can perform the conversion operation by specifying that a piece of Java code be executed, for example, to return after converting a property in a source object.

Note that at compile time, the expression is inserted directly into the transformation logic and its synatax is not validated.

For example, the `List<String>` attribute in the source object is concatenated into a string by ',':

```java 
@AutoMapper(target = UserDto.class)
public class User {

    @AutoMapping(target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList;

}
```

## Automatically access the custom converter interface

::: info
since `1.2.3`
:::

When some type conversion logic is more complex, you can use define converter interface to achive,
that is, using MapStruct native way.

When used this way, the default generated type conversion is automatically referenced if there is previously provided type conversion.

例如：

:::: code-group
::: code-group-item Car
```java
@AutoMapper(target = CarDto.class)
@Data
public class Car {
    private Tyre tyre;
}
```
:::
::: code-group-item CarDto
```java
@Data
public class CarDto {
    private TyreDTO tyre;
}
```
:::
::::

Converter interface between `Tyre` and `TyreDTO` is defined here.

```java
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TyreMapper {

    TyreDTO tyreToTyreDTO(Tyre tyre);

    Tyre tyreDtoToTyre(TyreDTO tyreDTO);

}
```

The generated implementation classes for the `Car` and `CarDto` converter interfaces are as follows:

:::: code-group
::: code-group-item CarToCarDtoMapperImpl
```java
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T15:38:48+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class CarToCarDtoMapperImpl implements CarToCarDtoMapper {

    @Autowired
    private TyreMapper tyreMapper;

    @Override
    public CarDto convert(Car source) {
        if ( source == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        carDto.setTyre( tyreMapper.tyreToTyreDTO( source.getTyre() ) );

        return carDto;
    }

    @Override
    public CarDto convert(Car source, CarDto target) {
        if ( source == null ) {
            return target;
        }

        target.setTyre( tyreMapper.tyreToTyreDTO( source.getTyre() ) );

        return target;
    }
}
```
:::
::: code-group-item CarDtoToCarMapperImpl
```java
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T15:38:49+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class CarDtoToCarMapperImpl implements CarDtoToCarMapper {

    @Autowired
    private TyreMapper tyreMapper;

    @Override
    public Car convert(CarDto source) {
        if ( source == null ) {
            return null;
        }

        Car car = new Car();

        car.setTyre( tyreMapper.tyreDtoToTyre( source.getTyre() ) );

        return car;
    }

    @Override
    public Car convert(CarDto source, Car target) {
        if ( source == null ) {
            return target;
        }

        target.setTyre( tyreMapper.tyreDtoToTyre( source.getTyre() ) );

        return target;
    }
}
```
:::
::::

## Configuration for reverse property mapping

::: info
**In this article, all the mentioned source classes refer to classes annotated by `@AutoMapper`; 
the target classes refer to the type specified by the `target` attribute in `@AutoMapper`.**
:::

As mentioned earlier, when you add the `@AutoMapper` annotation to a class, 
by default, generate not only the source-to-target converter interface, but also the target-to-source converter interface and implementation class,
note here that the converter interface generated by default does not any custom configuration,
even if the `@AutoMapping` annotation is configured in the source class.

There are two ways to implement a custom converter configuration from the target class to the source class:

1. Add the `@AutoMapper` annotation above the target class.  This is the most recommended approach, when both sides add this annotation, the default converter interface for target-to-source is not generated, that is, it is generated according to custom rules.
2. When the target class does not have access to the source class, or the project specification does not allow such annotations to be added to the target class, you can add the custom configuration entirelyto the source class. This is the **reverse attribute mapping configuration** described below.

The `@ReverseAutoMapping` annotation is provided in the framework to configure custom conversion rules from the target class to the source class.

::: warning
Note here that to prevent configuration conflicts, once you add the `@ReverseAutoMapping` annotation,
you can not add any custom conversion annotations to the target class.
:::

**The meaning of the `@ReverseAutoMapping` annotation is that when the target class is converted to the source class,
the custom rules need to be specified, where the attributes can be configured, consistent with the `@AutoMapping` annotation.**

There are two attributes to note here, `source` and `target`.

**Here the `source` refers to the attributes in the target class, and the `target` refers to the attributes in the source class.**

One might wonder why the configuration here seems to be reversed? If not, you can skip it.

When the framework is designed, all the attribute transformation configurations are based on the type to be converted,
with the ultimate effect of converting the class to the target class.
So The `source` here should also be an attribute in the source class.


If you sill don't understand it, you can assume that the annotation is the `@AutoMapping` annotation that should have been applied to the target class. Just copy it to the current class and change the annotation name.

## Immutable type

> since 1.3.2

When source type is immutable, the `T convert(S source, @MappingTarget T target)` method generated by the previous default rule may have problems.

So, you can use the `Immutable` annotation under any package to identify a class as an Immutable type, When an immutable type is used, the `@MappingTarget` makes no sense, and the above method eventually generates the following:

```java
public T convert(S source, @MappingTarget T target) {
    return target;
}
```