---
title: 注解
order: 5
---

# 注解

## @AutoMapper

- **描述**：定义标识需要生成类转换接口
- **使用位置**：实体类

### `target`

**`Class<?>` 类型，指定需要转换的目标类**

**该属性表明会生成被注解的类，转换为目标类的接口**

**示例：**：
如下配置，默认会生成 `Car` 转为 `CarDto` 的逻辑，如果没有配置 `CarDto` 转 `Car`
细节的话，还会根据默认的规则，生成相反的转换逻辑。具体细节，参考[转换接口生成细则](/guide/generate-mapper)

```java

@AutoMapper(target = CarDto.class)
public class Car {
    // ...
}
```

### `uses`

**`Class<?>[]` 类型，自定义类转换器**

**该属性可以将自定义映射器，传递给生成的转换接口使用。常用于在进行一些属性转换时，针对一些特定目标类型进行自定义的映射**

**示例场景**：

项目中会有字符串用 `,` 分隔，在一些类中，需要根据逗号拆分为字符串集合。针对于这种场景，可以指定一个默认的转换规则，避免每个字段大量的表达式：

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

## @AutoMapping

- **描述**：配置特定属性映射时的规则，该注解会在生成时，转换为 Mapstruct 中的 `@Mapping` 注解
- **使用位置**：字段

### `target`

**`String` 类型，目标字段，指定该注解的配置，应用于目标类中的哪个字段**

```java

@AutoMapper(target = Goods.class)
public class GoodsDto {

    @AutoMapping(target = "price", numberFormat = "$#.00")
    private int price;
    // ...
}
```

### dateFormat

**`String` 类型，在字段映射时，进行时间格式化，应用于 Date 类型转为 String 类型**

例如：
```java
@AutoMapper(target = Goods.class)
public class GoodsDto {

    @AutoMapping(target = "takeDownTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date takeDownTime;

}
```

### numberFormat

**`String` 类型，在字段映射时，进行数字格式化，应用于数字类型转换为 String 类型，可以指定 `java.text.DecimalFormat` 所支持的格式化字符串**

例如：
```java
@AutoMapper(target = Goods.class)
public class GoodsDto {

    @AutoMapping(target = "price", numberFormat = "$#.00")
    private int price;

}
```


### expression

**`String` 类型，这个比较强大，其字符串实际上是一行可执行的 Java 代码**

例如，用该属性，实现前面的列表转字符串：
```java
@AutoMapper(target = UserDto.class)
public class User {

    @AutoMapping(target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList;
    
}
```