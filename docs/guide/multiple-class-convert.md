---
title: 一个类与多个类之间转换
order: 4
category:
- 指南
description: MapStructPlus 一个类与多个类之间转换 multiple class convert
---

MapStructPlus 除了支持一个类与单个目标类型进行转换，还支持一个类与多个目标类型进行转换。

## 配置多个类转换

当想要配置一个类与多个类进行转换时，可以通过 `@AutoMappers` 来配置，该注解支持配置多个 `@AutoMapper`

例如：

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

## 配置指定类转换的规则

当配置多个类转换时，同一属性针对不同的类有不一样的转换规则。

针对这个问题，首先可以使用 `@AutoMappings` 指定多个转换规则，并且在使用 `@AutoMapping` 注解时，配置 `targetClass` 属性，指定当前规则的应用目标转换类。

如果在配置 `@AutoMapping` 注解时，没有指定 `targetClass` 时，则当前规则，会应用于与所有类转换。

:::info
`targetClass` 同时支持配置父类，当目标类是所配置的 `targetClass` 的子类时，即可应用于该规则。

> 该特性从 1.3.6 开始支持
:::


例如：

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