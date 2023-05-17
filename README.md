# mapstruct-plus

## 这是什么？

Mapstruct Plus 是对 Mapstruct 框架的一个增强，只做增强，不做修改，可以通过一个注解，自动生成两个类之间的转换操作，省略了 Mapstruct 定义接口的操作，使 Java 类型转换更加便捷、优雅。

目标：做最简单、最强大的类型转换工具

**如果该项目帮助了您，希望能点个 Star 鼓励一下！**

## 链接

- [Document](https://mapstruct.plus)
- [Mapstruct](https://mapstruct.org)
- [Gitee](https://gitee.com/linpeilie/mapstruct-plus)
- [Github](https://github.com/linpeilie/mapstruct-plus)
- [彻底干掉 BeanUtils，最优雅的 Mapstruct 增强工具全新出炉](https://juejin.cn/post/7204307381688909882)

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
    <mapstruct-plus.version>1.2.4</mapstruct-plus.version>
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

> vx : Clue8a796d01

<img src="https://raw.githubusercontent.com/linpeilie/mapstruct-plus/main/assets/contact-me.jpeg" alt="联系我" width="364" height="497" />