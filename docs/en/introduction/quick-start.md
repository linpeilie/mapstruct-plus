---
title: Quick Start
order: 3
category:
- Introduction
description: MapStructPlus 快速开始教程 quick start
---

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

## Non-SpringBoot environment

### add dependencies

Introducing `mapstruct-plus` dependencies:

#### Maven

```xml
<properties>
    <mapstruct-plus.version>latest version</mapstruct-plus.version>
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

#### Gradle

```groovy
dependencies {
    implementation group: 'io.github.linpeilie', name: 'mapstruct-plus', version: ${mapstruct-plus.version}
    
    annotationProcessor group: 'io.github.linpeilie', name: 'mapstruct-plus-processor', version: ${mapstruct-plus.version}
}
```

### add configuration class

In the module package where the Bean object resides, add a comment on any class: `@ComponentModelConfig(componentModel = "default")`

eg：

```java
@ComponentModelConfig(componentModel = "default")
public class MapperConfiguration {
}
```

### Specifies the object mapping relationship

Add a annotation - `@AutoMapper` above `User` or `UserDto` and set `targetType` to the opposite class.

eg：

```java
@AutoMapper(target = UserDto.class)
public class User {
    // ...
}
```

### Test

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

## SpringBoot Environment

### Add dependencies

Introducing `mapstruct-plus-spring-boot-starter` dependencies:

#### Maven

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

#### Gradle

```groovy
dependencies {
    implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: ${mapstruct-plus.version}
    
    annotationProcessor group: 'io.github.linpeilie', name: 'mapstruct-plus-processor', version: ${mapstruct-plus.version}
}
```

### Specifies the object mapping relationship

Same as non-SpringBoot environment

### Test

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

## Solon

:::info
since `1.2.5`
:::

### Add dependencies

Introducing `mapstruct-plu-solon-plugin` dependencies:

> `mapstruct-plus-solon-plugin` has been added to `solon-parent` dependency management, and the latest version can be viewed in [solon-plugins](https://gitee.com/dromara/solon-plugins)

```xml
    <dependencies>
        <dependency>
            <groupId>org.dromara.solon-plugins</groupId>
            <artifactId>mapstruct-plus-solon-plugins</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>io.github.linpeilie</groupId>
                            <artifactId>mapstruct-plus-processor</artifactId>
                            <version>${mapstruct-plus.version}</version>
                        </path>
                    </annotationProcessorPaths>
                    <compilerArgs>
                        <arg>
                            -Amapstruct.defaultComponentModel=solon
                        </arg>
                    </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

### Specifies the object mapping relationship

Same as SpringBoot environment

### Test

```java
@SolonTest(DemoApp.class)
@ExtendWith(SolonJUnit5Extension.class)
public class QuickStartTest {

    @Inject
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