package io.github.linpeilie.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * module-mappers 清单读取与解析工具（框架中立）
 *
 * <p>清单位于 {@code META-INF/mapstruct-plus/module-mappers}，由注解处理器在编译期写入，
 * 每行一条 {@code <kind>=<实现类完整类名>}，kind 见 {@link MapperKind}。
 * 清单随编译单元隔离：每个模块的产物只记录在自身的清单中。
 *
 * <p>解析对脏数据保持容忍：空行与 {@code #} 开头的注释行被忽略，格式非法的行返回
 * {@link Optional#empty()}，由调用方决定告警策略。
 *
 * @see ModuleMapperRecord
 * @author shanhongyu
 */
public final class ModuleMappers {

    /**
     * 清单在 classpath 上的资源路径
     */
    public static final String LOCATION = "META-INF/mapstruct-plus/module-mappers";

    private static final char SEPARATOR = '=';

    private static final Pattern CLASS_NAME_PATTERN = Pattern.compile("[A-Za-z0-9_$.]+");

    private ModuleMappers() {
    }

    /**
     * 枚举 classpath 上全部 module-mappers 清单资源；classLoader 为空返回空枚举，
     * 枚举失败向上抛出 IOException，由调用方按各自容错策略记录并跳过
     */
    public static Enumeration<URL> resources(ClassLoader classLoader) throws IOException {
        if (classLoader == null) {
            return Collections.emptyEnumeration();
        }
        return classLoader.getResources(LOCATION);
    }

    /**
     * 读取单个清单的全部有效行（UTF-8，剔除空行与注释行）；读取失败向上抛出由调用方处理
     */
    public static List<String> readLines(URL manifestUrl) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(manifestUrl.openStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!isIgnorableLine(line)) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    /**
     * 空行或 {@code #} 注释行
     */
    public static boolean isIgnorableLine(String line) {
        if (line == null) {
            return true;
        }
        String trimmed = line.trim();
        return trimmed.isEmpty() || trimmed.startsWith("#");
    }

    /**
     * 解析单行记录；格式非法（无分隔符、未知 kind、非法类名）返回 {@link Optional#empty()}
     */
    public static Optional<ModuleMapperRecord> parseLine(String line) {
        if (isIgnorableLine(line)) {
            return Optional.empty();
        }
        int separatorIndex = line.indexOf(SEPARATOR);
        if (separatorIndex <= 0 || separatorIndex == line.length() - 1) {
            return Optional.empty();
        }
        String kindValue = line.substring(0, separatorIndex).trim();
        String className = line.substring(separatorIndex + 1).trim();
        MapperKind kind = MapperKind.fromManifestValue(kindValue);
        if (kind == null || !isValidClassName(className)) {
            return Optional.empty();
        }
        return Optional.of(new ModuleMapperRecord(kind, className));
    }

    /**
     * 加载 classpath 上全部清单的全部记录；单个清单读取失败仅打印告警（含来源），
     * 不中断其余清单的加载；格式非法的行被跳过
     */
    public static List<ModuleMapperRecord> load(ClassLoader classLoader) {
        List<ModuleMapperRecord> records = new ArrayList<>();
        Enumeration<URL> manifestUrls;
        try {
            manifestUrls = resources(classLoader);
        } catch (IOException e) {
            System.err.println("[mapstruct-plus] Failed to enumerate module mappers manifests at " + LOCATION);
            e.printStackTrace();
            return records;
        }
        while (manifestUrls.hasMoreElements()) {
            URL manifestUrl = manifestUrls.nextElement();
            try {
                for (String line : readLines(manifestUrl)) {
                    parseLine(line).ifPresent(records::add);
                }
            } catch (IOException e) {
                System.err.println("[mapstruct-plus] Failed to read module mappers manifest " + manifestUrl);
                e.printStackTrace();
            }
        }
        return records;
    }

    private static boolean isValidClassName(String className) {
        return className.indexOf('.') >= 0 && CLASS_NAME_PATTERN.matcher(className).matches();
    }

}
