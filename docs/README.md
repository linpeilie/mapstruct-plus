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
    <version>1.5.3</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.5.3'
```

## 更新日志

### 1.5.3

- [pr178](https://github.com/linpeilie/mapstruct-plus/pull/178) : **Breaking Change**：重做 Spring 环境下转换器的注册方式——不再依赖应用的包扫描（`@ComponentScan`），改由框架在编译期自动记录、启动时自动注册，mapper 所在包是否被扫描覆盖不再有影响；
  - 升级注意：**所有使用 mapstruct-plus 的模块必须统一升级到 1.5.3 并重新构建**，旧版本编译产出的转换器将无法被注册；
  - 配置类中通过 `@ConditionalOnBean(XxxMapper.class)` 判断转换器是否存在的写法不再生效，需改用 `@ConditionalOnClass` 等条件；
  - 使用 shade 等方式合并打包、或非 Spring Boot 的纯 Spring 应用，需要少量额外配置，详见 [类库 / Starter 集成指南](/guide/library-integration.md)；
- 基于 mapstruct-plus 开发的类库 / starter 现在开箱即用：其内置转换器在任何消费应用中都能正确注册，彻底解决因包扫描范围未覆盖、运行期才报"找不到转换器"的问题；
- 支持嵌套类（内部类）作为映射对象；
- 纯 Java 方式（非 Spring）使用时启动更快，并修复了应用打成 Spring Boot fat jar 后部分转换器找不到的问题；
- 健壮性增强：个别转换器注册信息异常时仅告警跳过，不影响应用正常启动；

### 1.5.2

- 将 `MapObjectConvert` 静态工具类重构为 `MapObjectConverter` 接口 + `HutoolMapObjectConverter` 默认实现，使类型转换器可自定义；
- `@AutoMapMapper` 新增 `use` 属性，支持类级指定转换器实现；
- `@MapperConfig` 新增 `mapObjectConverter` 属性，支持全局配置转换器实现；
- 新增编译参数 `-Amapstruct.plus.mapObjectConverter` 等价入口；
- **Breaking Change**：删除 `MapObjectConvert` 类，迁移方式 `MapObjectConverter.getInstance(HutoolMapObjectConverter.class).objToString(value)`。

### 1.5.1

- fix [#172](https://github.com/linpeilie/mapstruct-plus/issues/172) : 修复 record 中配置 `@AutoMapping`、`@ReverseAutoMapping` 时，重复生成相同目标属性映射导致 MapStruct 编译失败的问题；

### 1.5.0

- 升级 MapStruct 版本为 1.6.3
  - 升级注意事项：MapStruct 1.6 增加了一个 Breaking Changes，升级后可能会导致有不兼容，详情可见 MapStruct [升级日志](https://github.com/mapstruct/mapstruct/releases)
    > 在 MapStruct 1.6 中，增加了对源参数（source parameters）进行存在性检查的支持。
    这意味着，即使你只想直接将一个源参数映射到某个目标属性，也需要使用新的注解 `@SourceParameterCondition` 或 `@Condition(appliesTo = ConditionStrategy.SOURCE_PARAMETERS)` 来定义存在性检查方法。
- Converter 支持 Consumer 函数接口
- [pr133](https://github.com/linpeilie/mapstruct-plus/pull/133) : AutoMapper、AutoMapping、ReverseAutoMapping 支持重复配置
- [pr141](https://github.com/linpeilie/mapstruct-plus/pull/141) : MapperConfig 添加 uses 属性以配置全局共享的自定义转换类 

### 1.4.8

- 修复在 Vscode 软件中，默认使用 Eclipse jdt.core 环境下，生成的转换实现类冲突的问题；
- 重新打包 javapoet，防止与其他项目冲突；
- 升级 `spring-boot-autoconfigure` 版本号 `2.7.9` ---> `2.7.18`

### 1.4.6

- 修改 `SpringContextUtils` 类名，防止与其他项目类名冲突；
- fix [#108](https://github.com/linpeilie/mapstruct-plus/issues/108) :  SpringContextUtils#getBeanFactory 方法改为静态；
- [pr](https://github.com/linpeilie/mapstruct-plus/pull/114) : 增加注解批量增加 `AutoMapping`、`AutoMappings`、`ReverseAutoMapping`、`ReverseAutoMappings` 特性；


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
