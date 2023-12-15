package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@Data
@AutoMapper(target = MoveChange.class)
public class IndMoveChangeFormVo {
    /**
     * 工号
     */
    private String agentCode;
    /**
     * 姓名
     */
    private String name;
    /**
     * 管理机构
     */
    @AutoMapping(source = "manageCom", target = "oldManageCom")
    private String manageCom;
}
