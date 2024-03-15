package io.github.linpeilie.mapper;

import io.github.linpeilie.model.TreeNode;
import java.util.List;
import org.mapstruct.Condition;
import org.mapstruct.Named;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Component;

@Component
public class TreeNodeAwareMapper {

    @Condition
    @Named("limitLeastTwo")
    public boolean limitLeastTwo(List<TreeNode> treeNodeList) {
        return treeNodeList != null && treeNodeList.size() >= 2;
    }

}
