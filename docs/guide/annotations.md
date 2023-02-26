---
title: 注解
order: 5
---

# 注解

## @AutoMapper

`@AutoMapper` 定义**标识需要生成类转换接口**
需要在实体类上面定义

### **`target`**

- 类型：`Class<?>`

**指定需要转换的目标类**

**该属性表明会生成被注解的类，转换为目标类的接口**

示例：：
如下配置，默认会生成 `Car` 转为 `CarDto` 的逻辑，如果没有配置 `CarDto` 转 `Car`
细节的话，还会根据默认的规则，生成相反的转换逻辑。具体细节，参考[转换接口生成约定](/guide/mapper-generate-appoint)

```java

@AutoMapper(target = CarDto.class)
public class Car {
    // ...
}
```

### `uses`

- 类型：`Class<?>[]`

**使用自定义类转换器**

**该属性可以将自定义映射器，传递给生成的转换接口使用。常用于在进行一些属性转换时，针对一些特定目标类型进行自定义的映射**

示例场景：

项目中会有字符串用 `,` 分隔，在一些类中，需要根据逗号拆分为字符串集合。针对于这种场景，可以有两种方式：首先可以指定字段映射时的表达式，但需要对每种该情况的字段，都添加表达式，复杂且容易出错。

第二，就可以自定义一个类型转换器，通过 `uses` 来使用

```java
public interface StringToListString {
    default List<String> stringToListString(String str) {
        return StrUtil.split(str);
    }
}

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

## @AutoMappers

`@AutoMappers` 定义标识需要生成多个类的转换接口
需要在实体类上面定义
当同时需要转换多个类时，不同类之间的属性配置，需要结合 `@AutoMappings` 来使用

例如，如下配置，同时会生成 `User <---> UserDto` 和 `User <---> UserVO` 之间的映射

```java
@AutoMappers({
    @AutoMapper(target = UserDto.class),
    @AutoMapper(target = UserVO.class)
})
public class User {
    // ...
}
```

## @AutoMapMapper

`@AutoMapMapper` 标识需要生成 `Map<String, Object>` 转为当前对象的转换接口
需要在实体类上定义

其中，`value` 支持的类型如下：
- String
- BigDecimal
- BigInteger
- Integer
- Long
- Double
- Number
- Boolean
- Date
- LocalDateTime
- LocalDate
- LocalTime
- URI
- URL
- Calendar
- Currency
- 自定义类（自定义类也需要增加 `@AutoMapMapper` 注解）

其中，类型的转换，交由 [hutool](https://hutool.cn) 包中的 `Convert` 接口实现，如果要转换的类已经是目标类型，则直接执行强转，否则，根据类型进行一定的适配转换。

例如：

定义两个对象，`MapModelA` 和 `MapModelB`：

- `MapModelA`
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

- `MapModelB`
```java
@AutoMapMapper
@Data
public class MapModelB {

    private Date date;

}
```

测试：

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

## @AutoMapping

`@AutoMapping` 用于**字段**上面，**配置特定属性映射时的规则**，该注解会在生成时，转换为 Mapstruct 中的 `@Mapping` 注解

### `targetClass`

**应用于的目标类**
用于多类转换时，区分当前规则，只应用于哪个类型之间的转换。
当指定了多个类转换时，如果该注解没有配置 `targetClass`，则当前配置的规则应用于所有的类型转换中。

例如：
```java
@AutoMappers({
    @AutoMapper(target = UserDto.class),
    @AutoMapper(target = UserVO.class)
})
public class User {

    @AutoMapping(targetClass = UserDto.class, target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList;

    @AutoMappings({
        @AutoMapping(targetClass = UserDto.class, target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss"),
        @AutoMapping(targetClass = UserVO.class, target = "birthday", ignore = true)
    })
    private Date birthday;

    @AutoMapping(target = "money", numberFormat = "$0.00")
    private double money;

}
```

### `target`

- 类型：`String`

**目标字段，指定该注解的配置，应用于目标类中的哪个字段**

示例：

```java

@AutoMapper(target = Goods.class)
public class GoodsDto {

    @AutoMapping(target = "price", numberFormat = "$#.00")
    private int price;
    // ...
}
```

### dateFormat

- 类型：`String`

**在字段映射时，进行时间格式化，应用于 Date 类型转为 String 类型**

例如：

```java
@AutoMapper(target = Goods.class)
public class GoodsDto {

    @AutoMapping(target = "takeDownTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date takeDownTime;

}
```

### numberFormat

- 类型：`String`

**在字段映射时，进行数字格式化，应用于数字类型转换为 String 类型，可以指定 `java.text.DecimalFormat` 所支持的格式化字符串**

例如：

```java
@AutoMapper(target = Goods.class)
public class GoodsDto {

    @AutoMapping(target = "price", numberFormat = "$#.00")
    private int price;

}
```

### expression

- 类型：`String`

**类型，这个比较强大，其字符串实际上是一行可执行的 Java 代码，需要写在 `java()` 括号内**

例如，用该属性，实现前面的列表转字符串：

```java
@AutoMapper(target = UserDto.class)
public class User {

    @AutoMapping(target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList;

}
```

## @AutoMappings

`@AutoMappings` 定义该字段在不同类之间的转换规则，一般和 `@AutoMappers` 结合使用。

例如：
下面的配置，当 `User` to `UserDto` 时，需要进行时间格式化转换；当 `User` to `UserVO` 时，忽略该字段。

```java
@AutoMappers({
    @AutoMapper(target = UserDto.class),
    @AutoMapper(target = UserVO.class)
})
public class User {
    
    @AutoMappings({
        @AutoMapping(targetClass = UserDto.class, target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss"),
        @AutoMapping(targetClass = UserVO.class, target = "birthday", ignore = true)
    })
    private Date birthday;

}
```

