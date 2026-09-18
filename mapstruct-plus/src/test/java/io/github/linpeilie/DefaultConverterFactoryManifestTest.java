package io.github.linpeilie;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.linpeilie.teststub.Stubs;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mapstruct.factory.Mappers;

/**
 * DefaultConverterFactory 清单化：
 * 仅按 module-mappers 清单识别登记（Impl 类经递归泛型解析定位 mapper 接口），
 * 没有清单内容的产物不处理
 *
 * <p>注意：baseMappers / baseMapMappers 为静态共享，隔离场景下工厂必须在各自的
 * 类加载器中创建（经反射），避免跨类加载器的同名类副本互染
 *
 * @author shanhongyu
 */
class DefaultConverterFactoryManifestTest {

    @Test
    @DisplayName("清单驱动：Impl 类递归解析泛型签名后可正常转换（bean / map）")
    void loadsAndConvertsMappersFromManifest() {
        DefaultConverterFactory factory = new DefaultConverterFactory();

        BaseMapper<Stubs.StubUser, Stubs.StubUserDto> mapper =
            factory.getMapper(Stubs.StubUser.class, Stubs.StubUserDto.class);
        assertThat(mapper).isNotNull();
        assertThat(mapper.convert(new Stubs.StubUser("msp")).getName()).isEqualTo("msp");

        Map<String, Object> map = new HashMap<>();
        map.put("str", "hello");
        map.put("count", 7);
        BaseMapMapper<Stubs.StubMapModel> mapMapper = factory.getMapMapper(Stubs.StubMapModel.class);
        assertThat(mapMapper).isNotNull();
        Stubs.StubMapModel model = mapMapper.convert(map);
        assertThat(model.getStr()).isEqualTo("hello");
        assertThat(model.getCount()).isEqualTo(7);
    }

    @Test
    @DisplayName("只认清单所列：类存在但未列入清单的产物不登记")
    void registersOnlyEntriesListedInManifest(@TempDir Path tempDir) throws Exception {
        // 隔离 classpath：类齐全，清单只列 bean、不列 map
        copyClasses(Paths.get("target/classes"), tempDir);
        copyClasses(Paths.get("target/test-classes/io/github/linpeilie/teststub"),
            tempDir.resolve("io/github/linpeilie/teststub"));
        Path manifestDir = tempDir.resolve("META-INF/mapstruct-plus");
        Files.createDirectories(manifestDir);
        Files.write(manifestDir.resolve("module-mappers"),
            ("bean=" + Stubs.StubUserMapper.class.getName() + "Impl").getBytes(StandardCharsets.UTF_8));

        URL mapstructJar = Mappers.class.getProtectionDomain().getCodeSource().getLocation();
        URLClassLoader isolated = new URLClassLoader(
            new URL[] {tempDir.toUri().toURL(), mapstructJar}, null);
        try {
            // 工厂在隔离类加载器内创建，其静态注册表独立于测试类加载器
            Class<?> factoryClass = isolated.loadClass(DefaultConverterFactory.class.getName());
            Object factory = factoryClass.getConstructor().newInstance();
            Method getMapper = factoryClass.getMethod("getMapper", Class.class, Class.class);
            Method getMapMapper = factoryClass.getMethod("getMapMapper", Class.class);

            // 清单所列：可用
            Class<?> userClass = isolated.loadClass(Stubs.StubUser.class.getName());
            Object userMapper = getMapper.invoke(factory, userClass,
                isolated.loadClass(Stubs.StubUserDto.class.getName()));
            assertThat(userMapper).isNotNull();
            Object user = userClass.getConstructor(String.class).newInstance("u1");
            Object userDto = userMapper.getClass().getMethod("convert", userClass).invoke(userMapper, user);
            assertThat(isolated.loadClass(Stubs.StubUserDto.class.getName())
                .getMethod("getName").invoke(userDto)).isEqualTo("u1");

            // 未列入清单：类存在也不登记
            Object mapMapper = getMapMapper.invoke(factory, isolated.loadClass(Stubs.StubMapModel.class.getName()));
            assertThat(mapMapper).as("没有清单内容的产物不处理").isNull();
        } finally {
            isolated.close();
        }
    }

    private void copyClasses(Path sourceDir, Path targetDir) throws IOException {
        if (!Files.isDirectory(sourceDir)) {
            throw new IllegalStateException("classes not compiled yet: " + sourceDir.toAbsolutePath());
        }
        try (Stream<Path> files = Files.walk(sourceDir)) {
            for (Path file : (Iterable<Path>) files::iterator) {
                if (file.toString().endsWith(".class")) {
                    Path target = targetDir.resolve(sourceDir.relativize(file));
                    Files.createDirectories(target.getParent());
                    Files.copy(file, target);
                }
            }
        }
    }

}
