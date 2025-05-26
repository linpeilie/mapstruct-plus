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
    - { text: 快速开始, link: '/introduction/quick-start.html' }
    - { text: '常见问题', link: '/guide/faq.html', type: 'plain' }
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

## 友情链接

<links />

## 其他开源项目
- **EasyRelation**：[GitHub](https://github.com/linpeilie/easy-relation) | [Gitee](https://gitee.com/easii/easy-relation) | [Document](https://easy-relation.easii.cn)

## 最新版本

- maven

```xml
<dependency>
    <groupId>io.github.linpeilie</groupId>
    <artifactId>mapstruct-plus-spring-boot-starter</artifactId>
    <version>1.4.8</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.4.8'
```

## 更新日志

### 1.4.8

- 修复在 Vscode 软件中，默认使用 Eclipse jdt.core 环境下，生成的转换实现类冲突的问题；
- 重新打包 javapoet，防止与其他项目冲突；
- 升级 `spring-boot-autoconfigure` 版本号 `2.7.9` ---> `2.7.18`

### 1.4.6

- 修改 `SpringContextUtils` 类名，防止与其他项目类名冲突；
- fix [#108](https://github.com/linpeilie/mapstruct-plus/issues/108) :  SpringContextUtils#getBeanFactory 方法改为静态；
- [pr](https://github.com/linpeilie/mapstruct-plus/pull/114) : 增加注解批量增加 `AutoMapping`、`AutoMappings`、`ReverseAutoMapping`、`ReverseAutoMappings` 特性；

### 1.4.5

- fix: 修复 `ReverseAutoMappings` 配置不生效的问题

### 1.4.4

- fix: 修复部分Spring版本下找不到类的问题

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