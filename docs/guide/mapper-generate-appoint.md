---
title: Mapper 接口生成约定
order: 7
---

# Mapper 接口生成约定

## Mapper 接口包名

`Mapper` 接口包名按照如下规则获取：

1. 转换对象所在模块中，配置的 `@sMapperConfig` 中指定的 `mapperPackage` 属性
2. 如果没有指定，则会取 `@AutoMapper` 类的包名

## Mapper 接口命名

`Mapper` 接口命名规则如下：`源类名` + `To` + `目标类名` + `Mapper`

例如，在 `User` 类上指定 `@AutoMapper(target = UserDto.class)`，则生成的 `Mapper` 接口类名为：`UserToUserDtoMapper`

## 自动生成相反类型转换及 `@AutoMapping` 继承

假如，在 `User` 类上指定 `@AutoMapper(target = UserDto.class)`，同时如果没有在 `UserDto` 上标注 `@AutoMapper(target = User.class)` 的话，除了会生成 `UserToUserDtoMapper` 接口，默认也会生成 `UserDtoToUserMapper` 接口。

即，**同时会生成两个类的相互转换接口**。所以[快速开始](/guide/quick_start)中才可以由 `UserDto` 转为 `User` 对象。

但是，具体属性上面的 `@AutoMapping` 注解，不能够完全继承。

例如，`User` 中的 `Date` 类型的属性，在 `@AutoMapping` 中指定了 `dateFormat` 时间格式，当反过来时，却不能这样子用，因为 `dateFormat` 只能应用于 String 类型。

由于这个原因，默认情况下生成反向转换 `Mapper`，`@AutoMapping` 的部分属性是不能够继承的。

目前，反向转换 `Mapper` 中，支持继承的 `@AutoMapping` 属性有如下：

- `target`：目标属性为当前源对象的标注 `@AutoMapping` 的属性
- `ignore`：忽略当前属性的转换

例如：

定义 `User` 类如下：

```java
@AutoMapper(target = UserDto.class)
public class User {

    private String username;
    private int age;
    private boolean young;

    @AutoMapping(target = "educations", ignore = true)
    private List<String> educationList;

    @AutoMapping(target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @AutoMapping(target = "assets", numberFormat = "$0.00")
    private double assets;

}
```

当在 `User` 类上没有定义 `@AutoMapper` 注解时，会按照默认规则进行生成。

相当于：

```java
@AutoMapper(target = User.class)
public class UserDto {

    private String username;
    private int age;
    private boolean young;

    @AutoMapping(target = "educationList", ignore = true)
    private List<String> educations;

    @AutoMapping(target = "birthday")
    private Date birthday;

    @AutoMapping(target = "assets")
    private double assets;

}
```
