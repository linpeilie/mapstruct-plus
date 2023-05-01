package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@AutoMapper(target = CourseVo.class)
public class Course {

    /**
     * 主键Id。
     */
    private Long courseId;

    /**
     * 租户Id。
     */
    private Long tenantId;

    /**
     * 课程名称。
     */
    private String courseName;

    /**
     * 课程价格。
     */
    private BigDecimal price;

    /**
     * 课程描述。
     */
    private String description;

    /**
     * 主讲老师。
     */
    private Long teacherId;

    /**
     * 课程难度。
     */
    private Integer difficulty;

    /**
     * 是否上架。
     */
    private Boolean online;

    /**
     * 所属年级。
     */
    private Integer gradeId;

    /**
     * 所属学科。
     */
    private Integer subjectId;

    /**
     * 课程分类。
     */
    private Integer categoryId;

    /**
     * 课时数量。
     */
    private Integer classHour;

    /**
     * 课程图片。
     */
    private String pictureUrl;

    /**
     * 所属校区。
     */
    private Long schoolId;

    /**
     * 创建用户Id。
     */
    private Long createUserId;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 最后修改时间。
     */
    private Date updateTime;

    /**
     * 章节数量 (一对多聚合计算字段)。
     */
    private Integer sectionCount = 0;

    /**
     * CourseSection 的一对多关联表数据对象。
     * 通常在一对多的关联中，我们基于从表数据过滤主表数据，此时需要先对从表数据进行嵌套子查询过滤，并将从表过滤数据列表集成到该字段。
     */
    private List<CourseSection> courseSectionList;
}
