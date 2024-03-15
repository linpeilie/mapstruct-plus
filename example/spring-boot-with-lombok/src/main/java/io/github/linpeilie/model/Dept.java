package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.Set;
import lombok.Data;

@Data
@AutoMapper(target = DeptDTO.class)
public class Dept {

    private String code;

    private String name;

    private Long parentId;

    private Set<Dept> children;

}
