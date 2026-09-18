package io.github.linpeilie.mapstruct;

import io.github.linpeilie.module.ModuleMapperRecord;
import io.github.linpeilie.module.ModuleMappers;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * 基于 META-INF/mapstruct-plus/module-mappers 清单的 mapper 注册器（spring 线唯一注册通道）
 *
 * <p>清单在编译期确定注册目标集。本注册器枚举 classpath 上全部清单，
 * 逐条注册 Impl / Adapter 类本身的 BeanDefinition——由 Spring 实例化并走完整生命周期，
 * 保住嵌套/自定义 mapper 的依赖注入语义；Bean 命名沿用扫描约定（Impl 类名首字母小写）。
 *
 * <p>容错红线：任何清单异常（读取失败、行格式非法、类缺失——增量编译可能残留
 * 处理器无法感知源码删除的陈旧条目）一律 WARN 并跳过该条目，绝不阻断容器启动。
 *
 * @see ModuleMappers
 * @author shanhongyu
 */
public class ModuleMapperRegistrar implements ImportBeanDefinitionRegistrar {

    private static final Logger log = LoggerFactory.getLogger(ModuleMapperRegistrar.class);

    /**
     * 直接实例化而非引用 {@code AnnotationBeanNameGenerator.INSTANCE}，
     * 后者 Spring 5.2 才引入，避免对消费方 Spring 版本引入额外门槛
     */
    private final BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    /**
     * 注册入口：由 starter 自动配置 {@code @Import} 激活，
     * 以线程上下文类加载器（缺省回落到本类类加载器）枚举并注册全部清单
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registerAll(registry, ClassUtils.getDefaultClassLoader());
    }

    /**
     * 读取全部清单并注册（包内可见，便于单测）
     */
    void registerAll(BeanDefinitionRegistry registry, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = ModuleMapperRegistrar.class.getClassLoader();
        }
        Enumeration<URL> manifestUrls;
        try {
            manifestUrls = ModuleMappers.resources(classLoader);
        } catch (IOException e) {
            log.warn("Failed to enumerate module mappers manifests, no module mapper will be registered", e);
            return;
        }
        while (manifestUrls.hasMoreElements()) {
            registerManifest(manifestUrls.nextElement(), registry, classLoader);
        }
    }

    /**
     * 注册单个清单中的全部条目（包内可见，便于单测）
     */
    void registerManifest(URL manifestUrl, BeanDefinitionRegistry registry, ClassLoader classLoader) {
        List<String> lines;
        try {
            lines = ModuleMappers.readLines(manifestUrl);
        } catch (IOException e) {
            log.warn("Failed to read module mappers manifest [{}], all entries in it are skipped",
                manifestUrl, e);
            return;
        }
        for (String line : lines) {
            Optional<ModuleMapperRecord> record = ModuleMappers.parseLine(line);
            if (!record.isPresent()) {
                log.warn("Malformed module mapper entry [{}] in manifest [{}], skipped", line, manifestUrl);
                continue;
            }
            registerMapper(record.get(), manifestUrl, registry, classLoader);
        }
    }

    /**
     * 注册单条清单记录：加载产物类并以 BeanDefinition 形式登记，
     * Bean 命名沿用组件扫描约定（Impl 类名首字母小写），注册前查重
     */
    private void registerMapper(ModuleMapperRecord record, URL manifestUrl,
        BeanDefinitionRegistry registry, ClassLoader classLoader) {
        String mapperClassName = record.getMapperClassName();
        Class<?> mapperClass;
        try {
            mapperClass = ClassUtils.forName(mapperClassName, classLoader);
        } catch (ClassNotFoundException | LinkageError e) {
            log.warn("Module mapper class [{}] listed in manifest [{}] cannot be loaded"
                + " (maybe a stale entry left by incremental compilation), skipped",
                mapperClassName, manifestUrl, e);
            return;
        }

        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(mapperClass).getBeanDefinition();
        String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
        // 注册前查重，防止同一产物经多个清单或多次注册重复登记
        if (registry.containsBeanDefinition(beanName)) {
            logDuplicateIfNeeded(beanName, mapperClassName, registry, manifestUrl);
            return;
        }
        registry.registerBeanDefinition(beanName, beanDefinition);
        if (log.isDebugEnabled()) {
            log.debug("Registered module mapper bean [{}] of kind [{}] from manifest [{}]",
                beanName, record.getKind(), manifestUrl);
        }
    }

    private void logDuplicateIfNeeded(String beanName, String mapperClassName,
        BeanDefinitionRegistry registry, URL manifestUrl) {
        BeanDefinition existing = registry.getBeanDefinition(beanName);
        if (!mapperClassName.equals(existing.getBeanClassName())) {
            log.warn("Module mapper [{}] from manifest [{}] conflicts with an existing bean definition"
                + " of [{}] under the same bean name [{}], the module mapper is skipped",
                mapperClassName, manifestUrl, existing.getBeanClassName(), beanName);
            return;
        }
        log.debug("Module mapper bean [{}] has already been registered, skipped", beanName);
    }

}
