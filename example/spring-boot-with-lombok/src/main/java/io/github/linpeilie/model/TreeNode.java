package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.mapper.TreeNodeAwareMapper;
import java.util.List;
import lombok.Data;

@Data
@AutoMapper(target = TreeNodeDto.class, cycleAvoiding = true, uses = TreeNodeAwareMapper.class)
public class TreeNode {

    private TreeNode parent;

    @AutoMapping(conditionQualifiedByName = "limitLeastTwo")
    private List<TreeNode> children;

}
