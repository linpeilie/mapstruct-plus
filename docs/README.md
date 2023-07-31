---
home: true
icon: home
title: 主页
description: MapStructPlus MapStruct Home 首页
heroText: MapstructPlus
tagline: 可能是最简单最强大的Java Bean转换工具
actions:
  - text: 快速开始 💡
    link: /introduction/quick-start
    type: primary
  - text: 常见问题
    link: /guide/faq

features:
  - title: 快速
    icon: launch
    details:  Java 类之间的转换，仅需要增加一个注解，减少了乏味且容易出错的开发任务

  - title: 效率
    icon: light
    details: 基于注解处理器，所有生成工作都在编译期完成

  - title: 转换
    icon: change
    details: 属性转换基于类中的 getter/setter 方法

  - title: 兼容
    icon: any
    details: 支持JDK8~17、SpringBoot2~3

  - title: 多类转换
    icon: structure
    details: 支持单个类配置多个类型转换

  - title: Map转对象
    icon: type
    details: 更强大的Map转对象功能

copyright: false
---

## 国内站点

- https://easii.gitee.io/mapstruct-plus/

## 其他开源项目

- **EasyRelation**：[GitHub](https://github.com/linpeilie/easy-relation) | [Gitee](https://gitee.com/easii/easy-relation) | [Document](https://easy-relation.easii.cn)

## 最新版本

- maven

```xml
<dependency>
    <groupId>io.github.linpeilie</groupId>
    <artifactId>mapstruct-plus-spring-boot-starter</artifactId>
    <version>1.3.4</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.3.4'
```

## 更新日志

### 1.3.4

……什么都没更新，腾讯云maven源同步的jar有问题，只能重新发个新包

### 1.3.3

- fixbug: 修复 win JDK8 编译报错问题

### 1.3.2

- 不可变对象支持，可以使用任意包下的 `Immutable` 标注类型为不可变类
- 全面适配 IDEA 部分编译问题，使用更加流畅丝滑

……

## 代码仓库

- [Github](https://github.com/linpeilie/mapstruct-plus)
- [Gitee](https://gitee.com/linpeilie/mapstruct-plus)

## 参与贡献

- 贡献代码：欢迎提交 Issue 或 Pull Requests
- 维护文档：项目目录下的 docs 目录，欢迎参与翻译与修订

## 推荐文章

- [彻底干掉 BeanUtils，最优雅的 Mapstruct 增强工具全新出炉](https://juejin.cn/post/7204307381688909882)

## 介绍视频

- [mapstruct-plus-深度拷贝一集精通](https://www.bilibili.com/video/BV1KM4y1E7GJ/)

## 友情链接

- [RuoYi-Vue-Plus](https://gitee.com/JavaLionLi/RuoYi-Vue-Plus/wikis/pages)
- [RuoYi-Cloud-Plus](https://gitee.com/JavaLionLi/RuoYi-Cloud-Plus/wikis/pages)

## 联系我

- 个人网站：[代码笔耕](https://easii.gitee.io)    
- vx : **Clue8a796d01**    
  ![Clue8a796d01](http://cos.easii.cn/20230609091707.webp)
- 公众号：**代码笔耕**    
  ![代码笔耕](http://cos.easii.cn/qrcode_for_gh_c207b35e04b8_344.webp)


