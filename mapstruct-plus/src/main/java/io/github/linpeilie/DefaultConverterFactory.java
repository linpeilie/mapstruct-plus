package io.github.linpeilie;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import org.mapstruct.factory.Mappers;

public class DefaultConverterFactory extends AbstractCachedConverterFactory {

    private static final Map<String, Class<?>> baseMappers = new HashMap<>();

    private static final Map<String, Class<?>> baseMapMappers = new HashMap<>();

    public DefaultConverterFactory() {
        loadMappers();
    }

    private void loadMappers() {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final Enumeration<URL> resources;
        try {
            resources = classLoader.getResources("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Class<?>> classes = new ArrayList<>();
        while (resources.hasMoreElements()) {
            final URL url = resources.nextElement();
            if (url.getProtocol().equals("file")) {
                loadClasses(null, url.getPath(), classes, classLoader);
            }
        }

        classes.forEach(clazz -> {
            // BaseMapper
            boolean isBaseMapperImplemented = isInterfaceImplemented(clazz, BaseMapper.class);
            if (isBaseMapperImplemented) {
                List<Type> baseMapperGenericTypes = getInterfaceGenericType(clazz, BaseMapper.class);
                if (baseMapperGenericTypes.size() == 2) {
                    Type source = baseMapperGenericTypes.get(0);
                    Type target = baseMapperGenericTypes.get(1);

                    baseMappers.put(getMapperName(source.getTypeName(), target.getTypeName()), clazz);
                }
            }
            // BaseMapMapper
            boolean isBaseMapMapperImplemented = isInterfaceImplemented(clazz, BaseMapMapper.class);
            if (isBaseMapMapperImplemented) {
                List<Type> baseMapMapperGenericTypes = getInterfaceGenericType(clazz, BaseMapMapper.class);
                if (baseMapMapperGenericTypes.size() == 1) {
                    Type source = baseMapMapperGenericTypes.get(0);
                    baseMapMappers.put(source.getTypeName(), clazz);
                }
            }
        });
    }

    private String getMapperName(String sourceQualifiedName, String targetQualifiedName) {
        return sourceQualifiedName.replaceAll("\\.", "_") + "To_" + targetQualifiedName.replaceAll("\\.", "_");
    }

    private boolean isInterfaceImplemented(Class<?> clazz, Class<?> interfaceClass) {
        List<Class<?>> interfaces = Arrays.asList(clazz.getInterfaces());
        return interfaces.contains(interfaceClass);
    }

    private List<Type> getInterfaceGenericType(Class<?> clazz, Class<?> interfaceClass) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                if (parameterizedType.getRawType().equals(interfaceClass)) {
                    return Arrays.asList(parameterizedType.getActualTypeArguments());
                }
            }
        }
        return Collections.emptyList();
    }

    private void loadClasses(String root,
        final String path,
        final List<Class<?>> classes,
        final ClassLoader classLoader) {
        final File file = new File(path);
        if (root == null) {
            root = file.getPath();
        }
        if (file.isFile()) {
            if (!file.getName().endsWith(".class")) {
                return;
            }
            try {
                String classPath = file.getPath();
                String className = classPath.substring(root.length() + 1, classPath.length() - 6)
                    .replaceAll(Matcher.quoteReplacement(File.separator), ".");
                classes.add(classLoader.loadClass(className));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            final File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                loadClasses(root, f.getPath(), classes, classLoader);
            }
        }
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
    protected <S> BaseMapMapper findMapMapper(final Class<?> source) {
        Class<?> mapMapperClass = baseMapMappers.get(source.getName());
        if (mapMapperClass == null) {
            return null;
        }
        return (BaseMapMapper) Mappers.getMapper(mapMapperClass);
    }
}
