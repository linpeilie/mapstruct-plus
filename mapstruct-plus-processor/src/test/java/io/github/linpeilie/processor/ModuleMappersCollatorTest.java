package io.github.linpeilie.processor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.LinkedHashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * ModuleMappersCollator 增量合并逻辑（读旧 → 合并 → 写回）单元测试
 *
 * @author shanhongyu
 */
class ModuleMappersCollatorTest {

    @Test
    @DisplayName("旧条目保留并与新条目去重合并；kind 变更时旧行保留由运行期查重兜底")
    void mergesOldAndNewEntriesWithDeduplication() {
        LinkedHashSet<String> merged = ModuleMappersCollator.mergeRecords(
            Arrays.asList(
                "bean=com.test1.LibAToLibADtoMapperImpl",
                "map=com.test2.MapToMapModel2MapperImpl",
                "cycle=com.test2.OrderNodeToOrderNodeDtoMapperImpl"),
            Arrays.asList(
                "bean=com.test1.LibAToLibADtoMapperImpl",
                "bean=com.test2.OrderNodeToOrderNodeDtoMapperImpl"));

        assertThat(merged).containsExactlyInAnyOrder(
            "bean=com.test1.LibAToLibADtoMapperImpl",
            "map=com.test2.MapToMapModel2MapperImpl",
            "cycle=com.test2.OrderNodeToOrderNodeDtoMapperImpl",
            "bean=com.test2.OrderNodeToOrderNodeDtoMapperImpl");
    }

    @Test
    @DisplayName("格式非法的旧行与新行均被剔除")
    void filtersMalformedLines() {
        LinkedHashSet<String> merged = ModuleMappersCollator.mergeRecords(
            Arrays.asList(
                "malformed-line-without-separator",
                "unknown=com.test1.LibAToLibADtoMapperImpl",
                "  "),
            Arrays.asList(
                "bean=valid.but.NotCompiledYet",
                "bean=Illegal Class Name"));

        assertThat(merged).containsExactly("bean=valid.but.NotCompiledYet");
    }

    @Test
    @DisplayName("适配器条目按新集合整体替换：陈旧 __N 后缀条目被清除，本轮条目保留")
    void replacesStaleAdapterEntriesWithCurrentOnes() {
        LinkedHashSet<String> merged = ModuleMappersCollator.mergeRecords(
            Arrays.asList(
                "adapter=io.github.linpeilie.ConverterMapperAdapter__99",
                "adapter=io.github.linpeilie.MapConvertMapperAdapter__99",
                "bean=com.test1.LibAToLibADtoMapperImpl"),
            Arrays.asList(
                "adapter=io.github.linpeilie.ConverterMapperAdapter__100"));

        assertThat(merged).containsExactly(
            "bean=com.test1.LibAToLibADtoMapperImpl",
            "adapter=io.github.linpeilie.ConverterMapperAdapter__100");
    }

}
