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
    <version>1.4.4</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.4.4'
```

## 更新日志

### 1.4.4

- fix: 修复部分Spring版本下找不到类的问题

### 1.4.3

- feat: `ComponentModel` 增加 `spring-lazy` 可选项，懒加载 Spring Bean，解决互相依赖的问题，并将默认配置改为该选项；
- fix: 解决 `unmappedTargetPolicy` 默认配置不生效的问题；
- enhance: 优化 IDEA 本地开发构建效率，一定程度上缩短构建时间、减小元空间占用；[Issue #89](https://github.com/linpeilie/mapstruct-plus/issues/89)

### 1.4.2

- feat: `AutoMapper` 注解增加 `mapperNameSuffix` 属性，支持配置生成的转换接口名称增加后缀，默认规则下生成的反向转换接口同时生效；
- feat : 适配 `Mapper` 注解的 `unmappedSourcePolicy`、`unmappedTargetPolicy`、`typeConversionPolicy`、`collectionMappingStrategy`、`nullValueMappingStrategy`、`nullValueIterableMappingStrategy`、`nullValuePropertyMappingStrategy`、`nullValueCheckStrategy`、`mappingControl` 属性；
- feat : 适配 `Mapping` 注解的 `constant`、`qualifiedBy`、`nullValueCheckStrategy`、`nullValuePropertyMappingStrategy`、`mappingControl`；
- feat : 适配 MapStruct 配置的 `typeConversionPolicy`、`collectionMappingStrategy`、`nullValueIterableMappingStrategy`、`nullValueMapMappingStrategy`、`nullValueCheckStrategy`、`mappingControl`、`unexpectedValueMappingException`、`suppressTimestampInGenerated` 属性；
- fix : 适配同一个模块中同类不同包生成类名冲突的问题；
- feat : `AutoMapping` 注解增加 `reverseConvertGenerate`，控制是否生成反向转换逻辑，适配更加复杂的应用场景；
- fix : 修复 `targetClass` 同时配置父类和子类时，转换规则冲突的问题；
- fix : 修复不同模块配置类、代理类类名冲突的问题；
- feat : `AutoMapper` 增加 `useEnums` 属性，支持手动配置转换时需要的枚举，解决跨模块枚举无法自动转换的问题；
- 优化转换接口生成逻辑；

### 1.4.0

- **优化复杂对象转换逻辑，占用元空间更小！性能更快！**
- 去除 hutool 等依赖，目前项目中只依赖了 MapStruct
- 适配对象循环嵌套场景
- [feature#63](https://github.com/linpeilie/mapstruct-plus/pull/63)`AutoMapping`、`ReverseAutoMapping` 支持 `qualifiedByName`、`conditionQualifiedByName` 和 `dependsOn` 属性
- [issue#I93Z2Z](https://gitee.com/easii/mapstruct-plus/issues/I93Z2Z)`AutoMappings` 支持配置在方法上面

> 升级 1.4.0 注意事项：    
> - 1.4.0 及以后的版本，复杂对象比较依赖项目中生成的 `ConvertMapperAdapter`，
> 在多模块下，由于类加载机制只会加载一个的原因，可能会导致 [`NoSuchMethodError`](/guide/faq.html) 的异常，
> 当然，这个问题在之前也会有，几率可能低一些，所以多模块下，务必配置 `adapterPackage` 来避免该问题。
> - Map 与对象的转换，还是依赖 hutool 中的类转换实现，如果需要该功能，需要额外引入 `hutool-core` 依赖包。

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

## 联系我

> 个人网站：[代码笔耕](https://easii.gitee.io)

> 微信交流群

![微信交流群](http://cos.easii.cn/wechat_20240315192612.jpg)

> vx : Clue8a796d01

![Clue8a796d01](http://cos.easii.cn/20230609091707.webp)

> 公众号：**代码笔耕**

![代码笔耕](http://cos.easii.cn/qrcode_for_gh_c207b35e04b8_344.webp)