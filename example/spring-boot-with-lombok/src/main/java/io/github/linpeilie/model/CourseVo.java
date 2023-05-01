package io.github.linpeilie.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class CourseVo {


    /**
     * 主键Id。
     */
    private Long courseId;

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
     * CourseSection[courseId] 的一对多[COUNT]聚合计算对象。
     */
    private Integer sectionCount;

    /**
     * CourseSection 的一对多关联表数据对象。数据对应类型为CourseSectionVo
     */
    private List<Map<String, Object>> courseSectionList;

}
