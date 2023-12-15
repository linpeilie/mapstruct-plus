---
title: Class converted with multiple class
order: 4
category:
- Guide
description: MapStructPlus 一个类与多个类之间转换 multiple class convert
---

MapStructPlus supports conversion of a single class to multiple target types as well as a single class to a single target type.

## Configure multiplee class conversions

When you want to configure a class to convert to multiple classes, you can do so through `@AutoMappers`, which supports configure multiple `@AutoMapper`

eg：

```java 
@Data
@AutoMappers({
    @AutoMapper(target = UserDto.class),
    @AutoMapper(target = UserVO.class)
})
public class User {
    // fields
}
```

## Configures the rules for the specified class transformation

When configuring multiple class conversions, the same property has different conversion rules for different classes.

To solve this problem, you can first specify multiple transformation rules using `@AutoMappings` and,
when using the `@AutoMapping` annotation, configure the `targetClass` attribute to specify the application target transformation class for the current rule.

If `targetClass` is not specified when the `@AutoMapping` annotation is configured, the current rule applies to all  class conversions.

eg：

```java 
@Data
@AutoMappers({
    @AutoMapper(target = UserDto.class),
    @AutoMapper(target = UserVO.class)
})
public class User {

    private String username;

    private int age;
    private boolean young;

    @AutoMapping(targetClass = UserDto.class, target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList;

    @AutoMappings({
        @AutoMapping(targetClass = UserDto.class, dateFormat = "yyyy-MM-dd HH:mm:ss"),
        @AutoMapping(targetClass = UserVO.class, ignore = true)
    })
    private Date birthday;

    @AutoMapping(targetClass = UserDto.class, numberFormat = "$0.00")
    private double assets;

    @AutoMapping(numberFormat = "$0.00")
    private double money;

    @AutoMappings({
        @AutoMapping(targetClass = UserVO.class, target = "voField")
    })
    private String voField;

}
```