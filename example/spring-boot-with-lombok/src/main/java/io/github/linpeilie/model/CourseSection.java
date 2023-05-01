package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapMapper;
import java.util.Date;
import lombok.Data;

@Data
@AutoMapMapper
public class CourseSection {

    /**
     * 主键Id。
     */
    private Long sectionId;

    /**
     * 租户Id。
     */
    private Long tenantId;

    /**
     * 章节名称。
     */
    private String sectionName;

    /**
     * 显示顺序。
     */
    private Integer showOrder;

    /**
     * 所属课程。
     */
    private Long courseId;

    /**
     * 课时数量。
     */
    private Integer classHour;

    /**
     * 附件。
     */
    private String attachmentUrl;

    /**
     * 用户Id。
     */
    private Long createUserId;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 更新时间。
     */
    private Date updateTime;

}
