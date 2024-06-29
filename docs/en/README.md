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
    <version>1.4.3</version>
</dependency>
```

- gradle

```groovy
implementation group: 'io.github.linpeilie', name: 'mapstruct-plus-spring-boot-starter', version: '1.4.3'
```

## Change Log

### 1.4.3

- **feat**: Added `spring-lazy` option to `ComponentModel` for lazy loading Spring Beans, resolving mutual dependency issues, and set this option as the default configuration.
- **fix**: Fixed the issue where the default configuration for `unmappedTargetPolicy` was not effective.
- **enhance**: Optimized IDEA local development build efficiency, reducing build time and metaspace usage to some extent.[Issue #89](https://github.com/linpeilie/mapstruct-plus/issues/89)

### 1.4.2

- **feat**: Added the `mapperNameSuffix` attribute to the `AutoMapper` annotation. This supports adding a suffix to the generated conversion interface name, and the reverse conversion interface will be effective under the default rules.
- **feat**: Adapted the `Mapper` annotation to support the following attributes: `unmappedSourcePolicy`, `unmappedTargetPolicy`, `typeConversionPolicy`, `collectionMappingStrategy`, `nullValueMappingStrategy`, `nullValueIterableMappingStrategy`, `nullValuePropertyMappingStrategy`, `nullValueCheckStrategy`, and `mappingControl`.
- **feat**: Adapted the `Mapping` annotation to support the following attributes: `constant`, `qualifiedBy`, `nullValueCheckStrategy`, `nullValuePropertyMappingStrategy`, and `mappingControl`.
- **feat**: Adapted MapStruct configuration to support the following attributes: `typeConversionPolicy`, `collectionMappingStrategy`, `nullValueIterableMappingStrategy`, `nullValueMapMappingStrategy`, `nullValueCheckStrategy`, `mappingControl`, `unexpectedValueMappingException`, and `suppressTimestampInGenerated`.
- **fix**: Resolved the issue of class name conflicts generated in different packages within the same module.
- **feat**: Added the `reverseConvertGenerate` attribute to the `AutoMapping` annotation to control whether to generate reverse conversion logic, adapting to more complex application scenarios.
- **fix**: Fixed the issue of conversion rule conflicts when both parent and child classes are configured in `targetClass`.
- **fix**: Resolved class name conflicts of configuration classes and proxy classes in different modules.
- **feat**: Added the `useEnums` attribute to `AutoMapper`, supporting manual configuration of required enums for conversion, solving the issue of automatic conversion of enums across modules.
- Optimized the logic for generating conversion interfaces.

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
