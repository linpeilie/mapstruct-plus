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
  description: Probably the simplest and most powerful Java Bean transformation tool
  tagline: MapStructPlus is an enhancement tool of MapStruct. On the basis of MapStruct, it realizes the function of automatically generating Mapper interface, and strengthens some functions, making Java type conversion more convenient and elegant.
  buttons:
    - { text: Quick Start, link: 'en/introduction/quick-start' }
    - { text: 'FAQ', link: 'en/guide/faq', type: 'plain' }
  socialLinks:
    - { icon: 'LogoGithub', link: 'https://github.com/vuepress-reco/vuepress-theme-reco' }
isShowTitleInHome: true
actionText: About
actionLink: /views/other/about
features:
  - title: Quick
    icon: launch
    details:  The conversion between Java classes requires only additional annotation, reducing the tedious and error-prone development task

  - title: Efficiency
    icon: light
    details: Based on the annotation processors, all of the generation is done at compile time

  - title: Conversion
    icon: change
    details: Property conversions are based on getter/setter methods in the class

  - title: Compatibility
    icon: any
    details: Support JDK8~17、SpringBoot2~3

  - title: Multi-class conversion
    icon: structure
    details: Support for a single class to configure multiple type conversions

  - title: Conversion between Map and Object
    icon: type
    details: More powerful map-to-object functionality
fotter:
  startYear: 2023
---

## Other Open Source Project

- **EasyRelation**：[GitHub](https://github.com/linpeilie/easy-relation) | [Gitee](https://gitee.com/easii/easy-relation) | [Document](https://easy-relation.easii.cn)

## Latest Version

- maven

```xml
<dependency>
    <groupId>io.github.linpeilie</groupId>
    <artifactId>mapstruct-plus-spring-boot-starter</artifactId>
    <version>1.4.0</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.4.0'
```

## Change Log

### 1.4.0

- **Optimize complex object conversion logic, take up less meta-space! and faster!**
- Get rid of dependencies such as hutool, which currently only rely on MapStruct in the project.
- The adaptation object loop nesting scenario
- [feature#63](https://github.com/linpeilie/mapstruct-plus/pull/63) `AutoMapping`、`ReverseAutoMapping` supports `qualifiedByName`,`conditionQualifiedByName`,and `dependsOn` properties.
- [issue#I93Z2Z](https://gitee.com/easii/mapstruct-plus/issues/I93Z2Z) `AutoMappings` supports configuration on methods.

> Points to note for upgrading 1.4.0
> - 1.4.0 and later versions, complex object comparisons reply on `ConvertMapperAdapter` generated in the project,
    which may cause [`NoSuchMethodError`](/guide/faq.html) exceptions under multiple modules because the Class Loading mechanism 
    will load only one, of course, this problem has been around before, and the odds are probably lower,
    so be sure to configure the `adapterPackage` to avoid this problem with multiple modules.
> - Map to object conversions still rely on class conversions in hutool, and additional `hutool-core` dependencies need to be introduced if this 
    functionality is required.

### 1.3.6

- Compatible with internal class conversion.
- The targetClass in the AutoMapping annotation supports configuring the parent class.
- AutoMapperConfig and AutoMapMapperConfig package and class name generated automatically by the framework support configuration.
- Supports AutoMapping annotations configured in the parent class.

### 1.3.5

- `@AutoMapping`、`@ReversedAutoMapping` support is configured on top of methods.
- `@AutoMapping`、`@ReverseAutoMapping` support the defaultExpression and conditionExpression properties

……

## Code Warehouse

- [Github](https://github.com/linpeilie/mapstruct-plus)
- [Gitee](https://gitee.com/linpeilie/mapstruct-plus)

## Participate and contribute

- Contribution Code: You are welcome to submit an issue or pull requests
- Maintain Documents: Docs directory under the project directory, welcome to participate in the translation and revision

## Links

- [RuoYi-Vue-Plus](https://gitee.com/JavaLionLi/RuoYi-Vue-Plus/wikis/pages)
- [RuoYi-Cloud-Plus](https://gitee.com/JavaLionLi/RuoYi-Cloud-Plus/wikis/pages)
