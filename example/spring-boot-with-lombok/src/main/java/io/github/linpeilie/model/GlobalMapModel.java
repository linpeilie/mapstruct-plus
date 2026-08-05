package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapMapper;
import lombok.Data;

/**
 * 未指定 use 属性的 @AutoMapMapper 模型，用于验证全局 @MapperConfig.mapObjectConverter 生效。
 *
 * @since 1.5.2
 */
@AutoMapMapper
@Data
public class GlobalMapModel {

    private String str;

    private Long value;
}
