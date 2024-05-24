package io.github.linpeilie;

import io.github.linpeilie.annotations.MapperConfig;
import io.github.linpeilie.utils.MapperUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import org.mapstruct.factory.Mappers;

public class DefaultConverterFactory extends AbstractCachedConverterFactory {

    private String basePackage;

    public DefaultConverterFactory() {
        this("");
    }

    public DefaultConverterFactory(final String basePackage) {
        this.basePackage = basePackage;
        // load mapper package
        if (basePackage == null || basePackage.isEmpty()) {
            loadBasePackage();
        }
    }

    private void loadBasePackage() {
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

        classes.stream().filter(clazz -> clazz.isAnnotationPresent(MapperConfig.class)).findFirst().ifPresent(clazz -> {
            final MapperConfig mapperConfig = clazz.getAnnotation(MapperConfig.class);
            basePackage = mapperConfig.mapperPackage();
        });
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

    private String getMapperClassName(Class<?> source, Class<?> target) {
        return MapperUtils.getMapperClassName(source.getName(), target.getName());
    }

    private String getMapMapperClassName(Class<?> source) {
        return "MapTo" + source.getSimpleName() + "Mapper";
    }

    private String getMapperPackage(Class<?> sourceType) {
        return basePackage != null && !basePackage.isEmpty() ? basePackage
                                                             : sourceType.getPackage().getName();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> BaseMapper<S, T> findMapper(final Class<S> sourceType, final Class<T> targetType) {
        final String mapperClassName = getMapperClassName(sourceType, targetType);
        try {
            final Class<?> mapperClass = Class.forName(getMapperPackage(sourceType) + "." + mapperClassName);
            return (BaseMapper<S, T>) Mappers.getMapper(mapperClass);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    protected <S> BaseMapMapper findMapMapper(final Class<?> source) {
        final String mapperClassName = getMapMapperClassName(source);
        try {
            final Class<?> mapperClass = Class.forName(getMapperPackage(source) + "." + mapperClassName);
            return (BaseMapMapper) Mappers.getMapper(mapperClass);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
