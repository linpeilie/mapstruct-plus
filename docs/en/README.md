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
    - { text: Quick Start, link: '/en/introduction/quick-start.html' }
    - { text: 'FAQ', link: '/en/guide/faq.html', type: 'plain' }
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
    <version>1.4.7</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.4.7'
```

## Change Log

### 1.4.7

- Fixed the issue where the generated conversion implementation class conflicted when using the default `Eclipse jdt.core` environment in Vscode;
- Repackaged `javapoet` to prevent conflicts with other projects.
- Upgraded spring-boot-autoconfigure version from 2.7.9 to 2.7.18. 

### 1.4.6

- Modify the `SpringContextUtils` class name to prevent conflicts with other project class names;
- fix [#108](https://github.com/linpeilie/mapstruct-plus/issues/108) :  SpringContextUtils#getBeanFactory method changes to static;
- [PR](https://github.com/linpeilie/mapstruct-plus/pull/114) : increase annotation batch `AutoMapping`、`AutoMappings`、`ReverseAutoMapping`、`ReverseAutoMappings` features.


### 1.4.5

- fix: fiexed an issue where the `ReverseAutoMapping` configuration did not take effect.

### 1.4.4

- fix: fixed a problem with missing classes in some Spring release version.

### 1.4.3

- **feat**: Added `spring-lazy` option to `ComponentModel` for lazy loading Spring Beans, resolving mutual dependency issues, and set this option as the default configuration.
- **fix**: Fixed the issue where the default configuration for `unmappedTargetPolicy` was not effective.
- **enhance**: Optimized IDEA local development build efficiency, reducing build time and metaspace usage to some extent.[Issue #89](https://github.com/linpeilie/mapstruct-plus/issues/89)

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
