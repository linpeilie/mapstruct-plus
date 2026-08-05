package io.github.linpeilie;

import io.github.linpeilie.model.CustomMapModel;
import io.github.linpeilie.model.GlobalMapModel;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class CustomConverterTest {

    @Autowired
    private Converter converter;

    @Test
    @DisplayName("优先级 2：全局 @MapperConfig 配置生效，未指定 use 的模型使用 CustomConverter")
    void globalConfig_mapToObject() {
        Map<String, Object> map = new HashMap<>();
        // Integer → String，触发 CustomConverter 的 [custom] 前缀
        map.put("str", 67890);
        map.put("value", 999L);

        GlobalMapModel result = converter.convert(map, GlobalMapModel.class);

        assertThat(result).isNotNull();
        // 全局 CustomConverter 对非 String 输入添加 [custom] 前缀
        assertThat(result.getStr()).isEqualTo("[custom]67890");
        assertThat(result.getValue()).isEqualTo(999L);
    }

    @Test
    @DisplayName("优先级 1：类级 use 覆盖全局配置，CustomMapModel 使用 HutoolMapObjectConverter")
    void classLevelUse_overridesGlobal() {
        Map<String, Object> map = new HashMap<>();
        map.put("str", 12345);
        map.put("amount", "99.99");
        map.put("count", "42");

        CustomMapModel result = converter.convert(map, CustomMapModel.class);

        assertThat(result).isNotNull();
        // 类级 use = HutoolMapObjectConverter.class 覆盖了全局 CustomConverter，不加前缀
        assertThat(result.getStr()).isEqualTo("12345");
        assertThat(result.getAmount()).isEqualByComparingTo("99.99");
        assertThat(result.getCount()).isEqualTo(42);
    }
}
