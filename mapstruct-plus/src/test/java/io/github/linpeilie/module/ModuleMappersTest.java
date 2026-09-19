package io.github.linpeilie.module;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * module-mappers 清单解析工具单元测试：行解析、忽略与非法行、classpath 加载
 *
 * @author shanhongyu
 */
class ModuleMappersTest {

    @Test
    @DisplayName("parseLine：四种 kind 的合法行均可解析")
    void parseLineParsesAllKinds() {
        assertParsed("bean=a.b.CMapperImpl", MapperKind.BEAN);
        assertParsed("map=a.b.CMapperImpl", MapperKind.MAP);
        assertParsed("cycle=a.b.CMapperImpl", MapperKind.CYCLE);
        assertParsed("adapter=a.b.Adapter__1", MapperKind.ADAPTER);
        assertThat(ModuleMappers.parseLine("bean=a.b.CMapperImpl").get().getMapperClassName())
            .isEqualTo("a.b.CMapperImpl");
    }

    @Test
    @DisplayName("parseLine：空行/注释被忽略，格式非法的行返回 empty")
    void parseLineSkipsIgnorableAndMalformedLines() {
        // 空行与注释
        assertThat(ModuleMappers.parseLine(null)).isEmpty();
        assertThat(ModuleMappers.parseLine("   ")).isEmpty();
        assertThat(ModuleMappers.parseLine("# comment")).isEmpty();
        // 格式非法
        assertThat(ModuleMappers.parseLine("no-separator")).isEmpty();
        assertThat(ModuleMappers.parseLine("=a.b.C")).isEmpty();
        assertThat(ModuleMappers.parseLine("bean=")).isEmpty();
        assertThat(ModuleMappers.parseLine("unknown=a.b.C")).isEmpty();
        assertThat(ModuleMappers.parseLine("bean=Illegal Class")).isEmpty();
        assertThat(ModuleMappers.parseLine("bean=NoDotInName")).isEmpty();
        assertThat(ModuleMappers.parseLine("bean=a=b")).isEmpty();
    }

    @Test
    @DisplayName("load：读取 classpath 清单，非法行剔除、格式合法的 adapter 行保留")
    void loadReadsClasspathManifestAndSkipsMalformedLines() {
        List<ModuleMapperRecord> records = ModuleMappers.load(ModuleMappersTest.class.getClassLoader());

        assertThat(records).contains(
            new ModuleMapperRecord(MapperKind.BEAN, "io.github.linpeilie.teststub.Stubs$StubUserMapperImpl"),
            new ModuleMapperRecord(MapperKind.MAP, "io.github.linpeilie.teststub.Stubs$StubMapModelMapperImpl"),
            new ModuleMapperRecord(MapperKind.ADAPTER, "io.github.linpeilie.teststub.NotExistAdapter"));
        assertThat(records)
            .as("非法行与未知 kind 不应进入解析结果")
            .noneMatch(record -> record.getMapperClassName().contains("bad-line"));
    }

    private void assertParsed(String line, MapperKind expectedKind) {
        Optional<ModuleMapperRecord> record = ModuleMappers.parseLine(line);
        assertThat(record).as("line should parse: %s", line).isPresent();
        assertThat(record.get().getKind()).isEqualTo(expectedKind);
    }

}
