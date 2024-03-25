package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.Set;
import lombok.Data;

@Data
@AutoMapper(target = Dept.class)
public class DeptDTO {

    private Long id;

    private String name;

    private Set<DeptDTO> children;

}
