package io.github.linpeilie.model;

import lombok.Data;

@Data
public class MoveChange {
    /**
     * 主键
     */
    private Long id;
    /**
     * 工号
     */
    private String agentCode;
    /**
     * 调整前机构
     */
    private String oldManageCom;
}