---
home: true
modules:
  - BannerBrand
  - Features
  - MdContent
  - Footer
bannerBrand:
  bgImage: '/bg.svg'
  title: MapStructPlus
  description: 可能是最简单最强大的Java Bean转换工具
  tagline: Mapstruct Plus 是 Mapstruct 的增强工具，在 Mapstruct 的基础上，实现了自动生成 Mapper 接口的功能，并强化了部分功能，使 Java 类型转换更加便捷、优雅。
  buttons:
    - { text: 快速开始, link: '/introduction/quick-start' }
    - { text: '常见问题', link: '/guide/faq', type: 'plain' }
  socialLinks:
    - { icon: 'LogoGithub', link: 'https://github.com/vuepress-reco/vuepress-theme-reco' }
isShowTitleInHome: true
actionText: About
actionLink: /views/other/about
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
footer:
  startYear: 2023
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
    <version>1.4.0-R1</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.4.0-R1'
```

## 更新日志

### 1.4.0

- **优化复杂对象转换逻辑，占用元空间更小！性能更快！**
- 去除 hutool 等依赖，目前项目中只依赖了 MapStruct
- 适配对象循环嵌套场景
- [feature#63](https://github.com/linpeilie/mapstruct-plus/pull/63)`AutoMapping`、`ReverseAutoMapping` 支持 `qualifiedByName`、`conditionQualifiedByName` 和 `dependsOn` 属性
- [issue#I93Z2Z](https://gitee.com/easii/mapstruct-plus/issues/I93Z2Z)`AutoMappings` 支持配置在方法上面

### 1.3.6

- 兼容内部类转换
- feature : AutoMapping 注解中的 targetClass 支持配置父类
- [issue#I8QPRO](https://gitee.com/easii/mapstruct-plus/issues/I8QPRO) : 框架自动生成的 AutoMapperConfig 和 AutoMapMapper 包和类名支持配置
- [issue#I8T7EF](https://gitee.com/easii/mapstruct-plus/issues/I8T7EF) : 支持在父类中配置的 AutoMapping 注解


### 1.3.5

- AutoMapping、ReverseAutoMapping 支持配置在方法上面；
- AutoMapping、ReverseAutoMapping 支持 defaultExpression 和 conditionExpression 属性

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


