package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapMapper;
import io.github.linpeilie.map.HutoolMapObjectConverter;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 通过类级 use 显式指定 HutoolMapObjectConverter，覆盖全局 CustomConverter 配置，
 * 验证优先级 1（类级覆盖）> 优先级 2（全局配置）。
 *
 * @since 1.5.2
 */
@AutoMapMapper(use = HutoolMapObjectConverter.class)
@Data
public class CustomMapModel {

    private String str;

    private BigDecimal amount;

    private Integer count;
}
