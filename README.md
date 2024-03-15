# mapstruct-plus

## What is MapStruct Plus

MapStruct Plus is an enhancement to the MapStruct framework. It only does the enhancement, does not make the modification, and can automatically generate the transformation operation between two classes through an annotation, omitting the operation of defining the interface of MapStruct, makes Java type conversion easy and elegant.

Goal: To be the simplest and most powerful type conversion tool

**If this project helps you, hope to click a Star to encourage it!**

## Link

- [Document](https://mapstruct.plus)

## Other open source projects

- **EasyRelation**：[GitHub](https://github.com/linpeilie/easy-relation) | [Gitee](https://gitee.com/easii/easy-relation) | [Document](https://easy-relation.easii.cn)

## Quick start


The following shows how to convert two objects using MapStructPlus.

Suppose there are two classes, `UserDto` and `User`, representing the data-layer object and business-layer object, respectively:

- `UserDto`

```java
public class UserDto {
    private String username;
    private int age;
    private boolean young;

    // getter、setter、toString、equals、hashCode
}
```

- `User`

```java
public class User {
    private String username;
    private int age;
    private boolean young;

    // getter、setter、toString、equals、hashCode
}
```

Introducing `mapstruct-plus-spring-boot-starter` dependencies:

```xml
<properties>
    <mapstruct-plus.version>latest version</mapstruct-plus.version>
</properties>
<dependencies>
    <dependency>
        <groupId>io.github.linpeilie</groupId>
        <artifactId>mapstruct-plus-spring-boot-starter</artifactId>
        <version>${mapstruct-plus.version}</version>
    </dependency>
</dependencies>
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>io.github.linpeilie</groupId>
                        <artifactId>mapstruct-plus-processor</artifactId>
                        <version>${mapstruct-plus.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Test

```java
@SpringBootTest
public class QuickStartTest {

    @Autowired
    private Converter converter;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("jack");
        user.setAge(23);
        user.setYoung(false);

        UserDto userDto = converter.convert(user, UserDto.class);
        System.out.println(userDto);    // UserDto{username='jack', age=23, young=false}

        assert user.getUsername().equals(userDto.getUsername());
        assert user.getAge() == userDto.getAge();
        assert user.isYoung() == userDto.isYoung();

        User newUser = converter.convert(userDto, User.class);

        System.out.println(newUser);    // User{username='jack', age=23, young=false}

        assert user.getUsername().equals(newUser.getUsername());
        assert user.getAge() == newUser.getAge();
        assert user.isYoung() == newUser.isYoung();
    }

}
```

## Summary

With the introduction of dependencies, the steps to using MapStructPlus are very simple.

1. Add an `AutoMapper` annotation to the class you want to convert
2. Get the `Converter` instance and call the convert method.

--------


## 这是什么？

MapStruct Plus 是对 MapStruct 框架的一个增强，只做增强，不做修改，可以通过一个注解，自动生成两个类之间的转换操作，省略了 Mapstruct 定义接口的操作，使 Java 类型转换更加便捷、优雅。

目标：做最简单、最强大的类型转换工具

**如果该项目帮助了您，希望能点个 Star 鼓励一下！**

## 链接

- [Document](https://mapstruct.plus)
- [Document 国内站点](https://easii.gitee.io/mapstruct-plus/)
- [Mapstruct](https://mapstruct.org)
- [Gitee](https://gitee.com/linpeilie/mapstruct-plus)
- [Github](https://github.com/linpeilie/mapstruct-plus)
- [彻底干掉 BeanUtils，最优雅的 Mapstruct 增强工具全新出炉](https://juejin.cn/post/7204307381688909882)

## 个人主页

- [代码笔耕](https://easii.gitee.io/)

## 其他开源项目

- **EasyRelation**：[GitHub](https://github.com/linpeilie/easy-relation) | [Gitee](https://gitee.com/easii/easy-relation) | [Document](https://easy-relation.easii.cn)

## 快速开始

下面演示如何使用 MapStruct Plus 来映射两个对象。

假设有两个类 `UserDto` 和 `User`，分别表示数据层对象和业务层对象：

- `UserDto`

```java
public class UserDto {
    private String username;
    private int age;
    private boolean young;

    // getter、setter、toString、equals、hashCode
}
```

- `User`

```java
public class User {
    private String username;
    private int age;
    private boolean young;

    // getter、setter、toString、equals、hashCode
}
```

### 添加依赖

引入 `mapstruct-plus-spring-boot-starter` 依赖：

```xml
<properties>
    <mapstruct-plus.version>1.4.0</mapstruct-plus.version>
</properties>
<dependencies>
    <dependency>
        <groupId>io.github.linpeilie</groupId>
        <artifactId>mapstruct-plus-spring-boot-starter</artifactId>
        <version>${mapstruct-plus.version}</version>
    </dependency>
</dependencies>
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>io.github.linpeilie</groupId>
                        <artifactId>mapstruct-plus-processor</artifactId>
                        <version>${mapstruct-plus.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 指定对象映射关系

在 `User` 或者 `UserDto` 上面增加注解 —— `@AutoMapper`，并设置 `targetType` 为对方类。

例如：

```java
@AutoMapper(target = UserDto.class)
public class User {
    // ...
}
```

### 测试

```java
@SpringBootTest
public class QuickStartTest {

    @Autowired
    private Converter converter;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("jack");
        user.setAge(23);
        user.setYoung(false);

        UserDto userDto = converter.convert(user, UserDto.class);
        System.out.println(userDto);    // UserDto{username='jack', age=23, young=false}

        assert user.getUsername().equals(userDto.getUsername());
        assert user.getAge() == userDto.getAge();
        assert user.isYoung() == userDto.isYoung();

        User newUser = converter.convert(userDto, User.class);

        System.out.println(newUser);    // User{username='jack', age=23, young=false}

        assert user.getUsername().equals(newUser.getUsername());
        assert user.getAge() == newUser.getAge();
        assert user.isYoung() == newUser.isYoung();
    }

}
```

### 小结

引入依赖后，使用 Mapstruct Plus 步骤非常简单。

1. 给需要转换的类添加 `AutoMapper` 注解
2. 获取 `Converter` 实例，调用 `convert` 方法即可

## 联系我

> 个人网站：[代码笔耕](https://easii.gitee.io)

> 微信交流群

![微信交流群](http://cos.easii.cn/wechat_20240315192612.jpg)

> vx : Clue8a796d01

<img src="https://gitee.com/easii/mapstruct-plus/raw/main/assets/contact-me.jpeg" alt="联系我" width="364" height="497" />

> 公众号：**代码笔耕**

![代码笔耕](https://img-1318183505.cos.ap-nanjing.myqcloud.com/qrcode_for_gh_c207b35e04b8_344.webp)
