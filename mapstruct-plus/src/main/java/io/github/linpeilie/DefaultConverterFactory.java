package io.github.linpeilie;

import io.github.linpeilie.module.MapperKind;
import io.github.linpeilie.module.ModuleMapperRecord;
import io.github.linpeilie.module.ModuleMappers;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mapstruct.factory.Mappers;

/**
 * 纯 Java 线默认转换器工厂：仅按 module-mappers 清单识别登记 mapper，
 * classpath 上没有清单内容的产物不处理。
 *
 * <p>注意：注册表为静态共享（沿用既有语义），按单例方式使用（如 {@code new Converter()} 默认实例）；
 * 隔离场景需将本类连同产物装入独立的类加载器
 */
public class DefaultConverterFactory extends AbstractCachedConverterFactory {

    private static final Map<String, Class<?>> baseMappers = new HashMap<>();

    private static final Map<String, Class<?>> baseMapMappers = new HashMap<>();

    public DefaultConverterFactory() {
        this(DefaultConverterFactory.class.getClassLoader());
    }

    /**
     * 指定类加载器（包内可见，便于测试隔离场景）
     */
    DefaultConverterFactory(ClassLoader classLoader) {
        loadMappers(classLoader);
    }

    /**
     * 仅按 module-mappers 清单识别登记；classpath 上没有清单的产物不处理
     */
    private void loadMappers(ClassLoader classLoader) {
        loadMappersFromManifest(ModuleMappers.load(classLoader), classLoader);
    }

    /**
     * 按清单登记：逐条加载所列产物类并解析泛型签名；适配器条目跳过，
     * 类加载失败或重复条目仅告警跳过，不中断加载
     */
    private void loadMappersFromManifest(List<ModuleMapperRecord> records, ClassLoader classLoader) {
        Set<String> loaded = new HashSet<>();
        for (ModuleMapperRecord record : records) {
            if (record.getKind() == MapperKind.ADAPTER) {
                // 适配器仅服务于 IoC 容器装配，纯 Java 线无需处理
                continue;
            }
            String mapperClassName = record.getMapperClassName();
            if (!loaded.add(mapperClassName)) {
                continue;
            }
            try {
                registerMapper(classLoader.loadClass(mapperClassName));
            } catch (Exception | LinkageError e) {
                // LinkageError 对应陈旧条目：Impl 类存在但其依赖（如 mapper 接口）已被清理
                System.err.println("[mapstruct-plus] Failed to register module mapper " + mapperClassName
                    + " listed in manifest, skipped");
                e.printStackTrace();
            }
        }
    }

    /**
     * 登记单个 mapper 类（接口或 Impl 类均可）：
     * 递归解析继承闭包上的 BaseMapper / BaseMapMapper 泛型签名，
     * 清单中的 Impl 类经此可定位到其声明的 mapper 接口
     */
    private void registerMapper(final Class<?> clazz) {
        GenericBinding mapperBinding = findGenericBinding(clazz, BaseMapper.class);
        if (mapperBinding != null && mapperBinding.arguments.size() == 2) {
            Type source = mapperBinding.arguments.get(0);
            Type target = mapperBinding.arguments.get(1);
            baseMappers.put(getMapperName(source.getTypeName(), target.getTypeName()),
                mapperBinding.declaringType);
        }
        GenericBinding mapBinding = findGenericBinding(clazz, BaseMapMapper.class);
        if (mapBinding != null && mapBinding.arguments.size() == 1) {
            Type target = mapBinding.arguments.get(0);
            baseMapMappers.put(target.getTypeName(), mapBinding.declaringType);
        }
    }

    /**
     * 在继承闭包中查找直接以 targetInterface 为泛型父接口的声明者及其实参
     */
    private GenericBinding findGenericBinding(final Class<?> clazz, final Class<?> targetInterface) {
        Deque<Class<?>> queue = new ArrayDeque<>();
        queue.add(clazz);
        Set<Class<?>> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Class<?> current = queue.poll();
            if (current == null || !visited.add(current)) {
                continue;
            }
            for (Type genericInterface : current.getGenericInterfaces()) {
                if (genericInterface instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                    if (isRawTypeOf(parameterizedType, targetInterface)) {
                        return new GenericBinding(current,
                            Arrays.asList(parameterizedType.getActualTypeArguments()));
                    }
                }
            }
            if (current.getSuperclass() != null) {
                queue.add(current.getSuperclass());
            }
            Collections.addAll(queue, current.getInterfaces());
        }
        return null;
    }

    private String getMapperName(String sourceQualifiedName, String targetQualifiedName) {
        return sourceQualifiedName.replaceAll("\\.", "_") + "To_" + targetQualifiedName.replaceAll("\\.", "_");
    }

    /**
     * 按类名比较父接口，避免隔离类加载器场景下同名类不同副本导致身份比较失效
     */
    private boolean isRawTypeOf(ParameterizedType parameterizedType, Class<?> targetInterface) {
        return parameterizedType.getRawType() instanceof Class
               && targetInterface.getName().equals(((Class<?>) parameterizedType.getRawType()).getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> BaseMapper<S, T> findMapper(final Class<S> sourceType, final Class<T> targetType) {
        String mapperClassName = getMapperName(sourceType.getName(), targetType.getName());
        Class<?> baseMapperClass = baseMappers.get(mapperClassName);
        if (baseMapperClass == null) {
            return null;
        }
        return (BaseMapper<S, T>) Mappers.getMapper(baseMapperClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <S> BaseMapMapper findMapMapper(final Class<?> source) {
        Class<?> mapMapperClass = baseMapMappers.get(source.getName());
        if (mapMapperClass == null) {
            return null;
        }
        return (BaseMapMapper) Mappers.getMapper(mapMapperClass);
    }

    private static final class GenericBinding {

        private final Class<?> declaringType;

        private final List<Type> arguments;

        private GenericBinding(Class<?> declaringType, List<Type> arguments) {
            this.declaringType = declaringType;
            this.arguments = arguments;
        }

    }

}
