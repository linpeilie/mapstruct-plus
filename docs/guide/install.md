---
title: 安装
order: 3
---


# 安装

由于其已经内嵌 Mapstruct，为了防止不同版本之间的差异，请不要再引入 Mapstruct 相关依赖


## 非 SpringBoot 环境

### Maven

```xml
<properties>
    <mapstruct-plus.version>1.1.1</mapstruct-plus.version>
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

### Gradle

```groovy
dependencies {
    implementation 'io.github.linpeilie:mapstruct-plus:1.1.1'
    
    annotationProcessor 'io.github.linpeilie：mapstruct-plus-processor:1.1.1'
}
```

## SpringBoot 环境

### Maven

```xml
<properties>
    <mapstruct-plus.version>1.1.1</mapstruct-plus.version>
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

### Gradle

```groovy
dependencies {
    implementation 'io.github.linpeilie:mapstruct-plus-spring-boot-starter:1.1.1'
    
    annotationProcessor 'io.github.linpeilie:mapstruct-plus-processor:1.1.1'
}
```