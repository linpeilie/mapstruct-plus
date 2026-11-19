package io.github.linpeilie.processor;

import static org.assertj.core.api.Assertions.assertThat;

import cn.easii.tutelary.deps.com.squareup.javapoet.ClassName;
import io.github.linpeilie.module.ModuleMappers;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardLocation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Verifies output isolation when a filer exposes a project-relative display name,
 * as Eclipse JDT does, while its URI identifies the actual module output.
 *
 * @author hb0730
 */
class CollatorOutputLocationTest {

    @TempDir
    Path tempDir;

    private Path displayOutput;

    @BeforeEach
    void configureRelativeDisplayOutput() {
        // Keep any output from the unfixed implementation isolated and removable.
        displayOutput = Paths.get("target", "collator-uri-" + tempDir.getFileName());
    }

    @AfterEach
    void removeMisplacedOutput() throws IOException {
        if (Files.exists(displayOutput)) {
            try (Stream<Path> paths = Files.walk(displayOutput)) {
                paths.sorted(Comparator.reverseOrder()).forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
            }
        }
    }

    @Test
    void mergesModuleManifestsInTheirOwnOutputDirectories() throws IOException {
        // Both modules expose the same relative name; their absolute URIs differ.
        Path firstOutput = tempDir.resolve("first module 中文").resolve("classes");
        Path secondOutput = tempDir.resolve("second module").resolve("classes");
        Path firstManifest = writeManifest(firstOutput,
            "bean=example.FirstMapperImpl", "adapter=example.FirstAdapter__1");
        Path secondManifest = writeManifest(secondOutput, "bean=example.SecondMapperImpl");

        new ModuleMappersCollator(processingEnvironment(firstOutput)).mergeAndWrite(
            Arrays.asList("bean=example.NewFirstMapperImpl", "adapter=example.FirstAdapter__2"));
        new ModuleMappersCollator(processingEnvironment(secondOutput)).mergeAndWrite(
            Collections.singleton("bean=example.NewSecondMapperImpl"));

        assertThat(Files.readAllLines(firstManifest, StandardCharsets.UTF_8)).containsExactly(
            "bean=example.FirstMapperImpl",
            "bean=example.NewFirstMapperImpl",
            "adapter=example.FirstAdapter__2");
        assertThat(Files.readAllLines(secondManifest, StandardCharsets.UTF_8)).containsExactly(
            "bean=example.SecondMapperImpl", "bean=example.NewSecondMapperImpl");
        assertThat(Files.exists(displayOutput)).isFalse();
    }

    @Test
    void appendsCustomMapperEntriesToTheManifestIdentifiedByItsUri() throws IOException {
        Path output = tempDir.resolve("custom module").resolve("test-classes");
        Path manifest = writeManifest(output,
            "bean=example.ExistingMapperImpl", "adapter=example.ExistingAdapter__1");

        new ModuleMappersCollator(processingEnvironment(output)).appendEntries(
            Collections.singleton("bean=example.CustomMapperImpl"));

        assertThat(Files.readAllLines(manifest, StandardCharsets.UTF_8)).containsExactly(
            "bean=example.ExistingMapperImpl",
            "adapter=example.ExistingAdapter__1",
            "bean=example.CustomMapperImpl");
        assertThat(Files.exists(displayOutput)).isFalse();
    }

    @Test
    void writesBuildCollatorRecordsToTheResourceUri() throws IOException {
        Path output = tempDir.resolve("build module 中文").resolve("test-classes");
        BuildCollator collator = new BuildCollator(processingEnvironment(output), "autoMapper");

        collator.appendNonexistentTypes(Collections.singleton(ClassName.get("example", "Source")));

        Path records = output.resolve(ContextConstants.MetaInf.folder + "autoMapper");
        assertThat(Files.exists(records)).isTrue();
        assertThat(Files.readAllLines(records, StandardCharsets.UTF_8))
            .containsExactly("example.Source");
        assertThat(Files.exists(displayOutput)).isFalse();
    }

    private Path writeManifest(Path output, String... entries) throws IOException {
        Path manifest = output.resolve(ModuleMappers.LOCATION);
        Files.createDirectories(manifest.getParent());
        Files.write(manifest, Arrays.asList(entries), StandardCharsets.UTF_8);
        return manifest;
    }

    private ProcessingEnvironment processingEnvironment(Path output) {
        Filer filer = (Filer) Proxy.newProxyInstance(Filer.class.getClassLoader(),
            new Class<?>[] {Filer.class}, (proxy, method, args) -> {
                if ("getResource".equals(method.getName())) {
                    assertThat(args[0]).isEqualTo(StandardLocation.CLASS_OUTPUT);
                    assertThat(args[1]).isEqualTo("");
                    String relativeName = args[2].toString();
                    return new SimpleJavaFileObject(output.resolve(relativeName).toUri(),
                        javax.tools.JavaFileObject.Kind.OTHER) {
                        @Override
                        public String getName() {
                            return displayOutput.resolve(relativeName).toString();
                        }
                    };
                }
                throw new UnsupportedOperationException(method.getName());
            });
        // The BuildCollator path test adds a class name directly, without resolving
        // historical TypeElements. No other language-model methods are used.
        Elements elements = (Elements) Proxy.newProxyInstance(Elements.class.getClassLoader(),
            new Class<?>[] {Elements.class}, (proxy, method, args) -> {
                if ("getTypeElement".equals(method.getName())) {
                    return null;
                }
                throw new UnsupportedOperationException(method.getName());
            });
        return (ProcessingEnvironment) Proxy.newProxyInstance(
            ProcessingEnvironment.class.getClassLoader(),
            new Class<?>[] {ProcessingEnvironment.class}, (proxy, method, args) -> {
                if ("getFiler".equals(method.getName())) {
                    return filer;
                }
                if ("getElementUtils".equals(method.getName())) {
                    return elements;
                }
                throw new UnsupportedOperationException(method.getName());
            });
    }
}
