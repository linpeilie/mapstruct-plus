---
title: 快速开始
order: 3
category:
- 介绍
tag:
- 快速开始
description: MapStructPlus 快速开始教程 quick start
---

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

## 非 SpringBoot 环境

### 添加依赖

引入 `mapstruct-plus` 依赖：

```xml
<properties>
    <mapstruct-plus.version>最新版本</mapstruct-plus.version>
</properties>
<dependencies>
    <dependency>
        <groupId>io.github.linpeilie</groupId>
        <artifactId>mapstruct-plus</artifactId>
        <version>{mapstruct-plus.version}</version>
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

### 添加配置类

在 Bean 对象所在模块包中，任意类上增加注解：`@ComponentModelConfig(componentModel = "default")`

例如：

```java
@ComponentModelConfig(componentModel = "default")
public class MapperConfiguration {
}
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
public class QuickStart {

    private static Converter converter = new Converter();

    public static void main(String[] args) {
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

## SpringBoot 环境

### 添加依赖

引入 `mapstruct-plus-spring-boot-starter` 依赖：

```xml
<properties>
    <mapstruct-plus.version>最新版本</mapstruct-plus.version>
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

同非 SpringBoot 环境

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

## 小结

引入依赖后，使用 Mapstruct Plus 步骤非常简单。

1. 给需要转换的类添加 `AutoMapper` 注解
2. 获取 `Converter` 实例，调用 `convert` 方法即可
