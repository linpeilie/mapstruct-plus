package io.github.linpeilie.processor;

import io.github.linpeilie.module.MapperKind;
import io.github.linpeilie.module.ModuleMappers;
import io.github.linpeilie.processor.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * module-mappers 清单校对器
 *
 * <p>与 {@link BuildCollator} 相同的增量合并策略（读旧 → 合并 → 写回），兼容 IDEA 部分构建。
 * 差异在于清单行带 kind 前缀（{@code <kind>=<实现类完整类名>}），且新条目对应的 Impl 类
 * 由 MapStruct 在本回合稍后才生成，无法以 TypeElement 校验，故仅对旧行做格式过滤——
 * 陈旧条目（源码已删除而增量编译残留）由运行期注册器容错跳过。
 *
 * <p>适配器类名带全局递增后缀（{@code __N}），旧条目必为陈旧产物，写回时按本轮
 * 生成集合整体替换，避免陈旧条目无限累积。
 *
 * @see ModuleMappers
 * @author shanhongyu
 */
public class ModuleMappersCollator {

    private final File manifestFile;

    private final List<String> records = new ArrayList<>();

    /**
     * 读取既有清单内容（仅保留格式合法的行），等待与本轮新条目合并
     *
     * @param processingEnv 注解处理环境，用于定位 CLASS_OUTPUT 下的清单文件
     */
    public ModuleMappersCollator(ProcessingEnvironment processingEnv) {
        Filer filer = processingEnv.getFiler();
        try {
            FileObject fileObject = filer.getResource(StandardLocation.CLASS_OUTPUT, "",
                ModuleMappers.LOCATION);
            this.manifestFile = new File(fileObject.getName());
            if (manifestFile.exists()) {
                FileUtils.readUtf8Lines(manifestFile).stream()
                    .filter(line -> ModuleMappers.parseLine(line).isPresent())
                    .forEach(records::add);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 合并本轮新生成的条目并写回清单
     *
     * @param newEntries 形如 {@code bean=a.b.CMapperImpl} 的条目集合
     */
    public void mergeAndWrite(Collection<String> newEntries) {
        LinkedHashSet<String> merged = mergeRecords(records, newEntries);
        FileUtils.mkParentDirs(manifestFile);
        FileUtils.writeUtf8Lines(merged, manifestFile);
    }

    /**
     * 仅追加新条目（保留全部格式合法的旧行，含适配器），
     * 供不走生成流程的轻量路径使用——本轮没有重新生成适配器，不得触发适配器旧行清理
     */
    public void appendEntries(Collection<String> newEntries) {
        LinkedHashSet<String> merged = new LinkedHashSet<>();
        records.stream().filter(line -> ModuleMappers.parseLine(line).isPresent()).forEach(merged::add);
        newEntries.stream().filter(line -> ModuleMappers.parseLine(line).isPresent()).forEach(merged::add);
        FileUtils.mkParentDirs(manifestFile);
        FileUtils.writeUtf8Lines(merged, manifestFile);
    }

    /**
     * 读旧 → 合并 → 写回 的纯合并逻辑：
     * 保留格式合法的旧 mapper 条目（含本回合尚未生成的 Impl），去重合并新条目；
     * 适配器条目按新集合整体替换（{@code __N} 递增后缀使旧条目必为陈旧产物）
     */
    static LinkedHashSet<String> mergeRecords(Collection<String> oldLines, Collection<String> newEntries) {
        LinkedHashSet<String> merged = new LinkedHashSet<>();
        for (String oldLine : oldLines) {
            if (isAdapterEntry(oldLine)) {
                continue;
            }
            if (ModuleMappers.parseLine(oldLine).isPresent()) {
                merged.add(oldLine);
            }
        }
        newEntries.stream().filter(line -> ModuleMappers.parseLine(line).isPresent()).forEach(merged::add);
        return merged;
    }

    private static boolean isAdapterEntry(String line) {
        return line != null && ModuleMappers.parseLine(line)
            .map(record -> record.getKind() == MapperKind.ADAPTER)
            .orElse(false);
    }

}
