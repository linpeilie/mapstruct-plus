package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;
import lombok.Data;

@Data
@AutoMapper(target = TreeNode.class, cycleAvoiding = true)
public class TreeNodeDto {

    private TreeNodeDto parent;

    private List<TreeNodeDto> children;

}
