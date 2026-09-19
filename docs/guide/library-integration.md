---
title: 类库 / Starter 集成指南
order: 7
category:
- 指南
description: MapStructPlus 类库与 starter 的自治注册机制（module-mappers 清单）及升级说明
---

## 背景

在此前的版本中，spring 线生成的 mapper 实现类依赖消费方应用的 `@ComponentScan` 扫描范围恰好覆盖 mapper 所在包才能注册为 Bean。这带来几个问题：

1. **类库/starter 无法自治**：starter 中 `@AutoMapper` 对象的注册依赖消费应用的扫描根；
2. **包名碰撞**：starter 包名与消费应用包名相同时，无法精确界定注册边界，容易误扫应用侧同包组件；
3. **静默失效**：`mapperPackage` 指到扫描范围之外时，编译与启动均正常，运行期才抛 `ConvertException: cannot find converter`。

新版本将 spring 线注册通道整体替换为**编译期清单注册**：

```
编译期  @AutoMapper → Impl/Adapter（无 @Component）+ META-INF/mapstruct-plus/module-mappers
                                    │
运行期                              ▼
  ModuleMapperRegistrar ──读全部清单──► 注册 BeanDefinition ──► Spring 实例化/注入
```

## 清单机制

- 编译期，注解处理器将本模块全部生成产物（mapper Impl 与 Adapter）写入
  `META-INF/mapstruct-plus/module-mappers`，每行一条 `<kind>=<Impl 完整类名>`，
  kind 取值：`bean` / `map` / `cycle` / `adapter`（枚举 Mapper 为静态方法接口，无需注册）；
  自定义 `@Mapper` 接口的实现类同样以 `bean` 记录入清单
  （要求沿用 MapStruct 默认实现命名——接口名 + `Impl`；自定义 `implementationName` 的极端场景不受支持）；
- 清单随编译单元隔离——**每个模块的 jar 只记录自己的产物**，物理上不存在互相波及；
- 运行期，starter 自动配置中的 `ModuleMapperRegistrar`（`ImportBeanDefinitionRegistrar`）
  枚举 classpath 上全部清单，逐条注册 BeanDefinition，Bean 命名沿用扫描约定（Impl 类名首字母小写）；
- **容错红线**：清单中任何异常条目（读取失败、行格式非法、类缺失——例如增量编译残留的陈旧条目）
  一律 WARN 并跳过，绝不阻断容器启动。

## 类库 / Starter 集成方式

作为类库或 starter 作者，**无需任何额外配置**：

1. 依赖与消费应用**同版本**的 `mapstruct-plus` / `mapstruct-plus-processor` / `mapstruct-plus-spring-boot-starter`；
2. 在类库的对象上正常使用 `@AutoMapper` 等注解；
3. 构建发布即可——jar 中自带的清单保证了无论消费应用的扫描根是什么，你的 mapper 都会被注册。

**注意**：请勿在类库中以 `@ComponentScan` 兜底注册 mapper——该方式依赖包路径约定，
会误扫消费应用中与类库同包的组件；清单机制已经精确解决了边界问题。

## 升级与兼容性

**注册只认清单**：框架侧（starter 与纯 Java 线）均只按 `module-mappers` 清单识别注册，
**classpath 上没有清单的产物不做任何兜底处理**——不再保留注解扫描的老注册方式。

| 产物 | 注册通道 |
| --- | --- |
| 新构建产物（无 `@Component`、有清单） | 清单（spring 线为 `ModuleMapperRegistrar`，纯 Java 线为 `DefaultConnectionFactory`） |
| 旧 jar（带 `@Component`、无清单） | **不处理**——需用同版本 core / processor / starter 全量重新构建 |

::: warning
本次为破坏性变更：所有含 mapstruct-plus 产物的模块必须统一升级到同版本并重新构建，
升级顺序不再自由。消费方应用自己的 `@ComponentScan` 照常注册自己的组件，
与清单注册互不干扰、互不重复。
:::

**已知差异**：

1. **条件装配可见性**：配置类中 `@ConditionalOnBean(XxxMapper.class)` 的求值早于 Registrar 注册
   （IBDR 晚于用户配置解析），不再命中，需改用其他条件（如 `@ConditionalOnClass`）；
2. **注册来源**：BeanDefinition 来自 Registrar 而非组件扫描，依赖"扫描"语义的工具/审计会观察到差异。

## 纯 Java 线

纯 Java 线的 `DefaultConverterFactory` 同样只按清单识别：读取全部清单并加载所列 mapper 类，
没有清单内容的产物不处理。新产物环境因此不再有全量扫描 classpath 的启动开销，
并修复 Spring Boot 嵌套式 fat jar 场景因仅支持 `file:` 协议导致的漏注册。

## 注意事项

### shade / assembly 合并打包

多模块各携带一份 `META-INF/mapstruct-plus/module-mappers`，maven-shade 等合并型打包默认
**重名资源只保留一份**，其余模块的清单会静默丢失。请配置资源追加转换（清单为行式文本，
重复条目会被注册端查重吸收）：

```xml
<transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
    <resource>META-INF/mapstruct-plus/module-mappers</resource>
</transformer>
```

Spring Boot 的嵌套式 fat jar（launcher 加载全部嵌套 jar）不受此影响。

### 非 Boot 的纯 Spring 应用

spring 线（含标准 `spring` 组件模型——其适配器同样无 `@Component`）注册依赖
`ModuleMapperRegistrar`，该类位于 `mapstruct-plus-spring-boot-starter` 产物中。
不启用自动装配的纯 Spring 应用需引入 starter 并手动注册：

```java
@Configuration
@Import(io.github.linpeilie.mapstruct.ModuleMapperRegistrar.class)
public class MapStructPlusConfig {
}
```

## solon 线

solon 线注册机制不变；清单契约已为其同理替换奠定基础，后续作为独立版本发布。
