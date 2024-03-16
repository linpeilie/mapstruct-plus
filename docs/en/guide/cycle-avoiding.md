---
title: cycle avoiding
order: 7
category:
- Guide
description: MapStructPlus MapStructPlus类循环嵌套 CycleAvoiding
---

## Background

Class loop nesting is when two classes reference each other. 
For example, both the source and target object structures contain bidirectional associations between parent and child objects. 
When this occurs, direct conversion results in a stack overflow error.


Example：

```java
@Data
public class TreeNode {
    private TreeNode parent;
    private List<TreeNode> children;
}

@Data
public class TreeNodeDto {
    private TreeNodeDto parent;
    private List<TreeNodeDto> children;
}
```

The `parent` attribute can be of other types, possibly spanning a nested loop formed by a longer chain of attributes.

To accommodate this situation, the **`AutoMapper`** annotation for MapStructPlus adds the **`cycleAvoiding`** attribute, 
which is used for identification and whether loop nesting needs to be avoided. 
The default is `false`, which needs to be set to `true` if loop nesting is to be avoided.


When configured to `true`, a `CycleAvoidingMappingContext` object is passed through the conversion link of the entire object, 
temporarily saving the conversion generated object, this type is returned directly to avoid stack overflow problems. 
So, when you configure this property to `true`, there is a bit of performance cost, and if there is no loop nesting, 
use the default configuration to avoid unnecessary performance cost.

## Example

Using the example above, in the `AutoMapper` annotation, configure the `cycleAvoiding` property to be `true`, as follows:

```java
@Data
@AutoMapper(target = TreeNodeDto.class, cycleAvoiding = true)
public class TreeNode {
    private TreeNode parent;
    private List<TreeNode> children;
}

@Data
@AutoMapper(target = TreeNode.class, cycleAvoiding = true)
public class TreeNodeDto {
    private TreeNodeDto parent;
    private List<TreeNodeDto> children;
}
```

The compile-generated transformation logic is as follows:

```java
public TreeNodeDto convert(TreeNode arg0, CycleAvoidingMappingContext arg1) {
    TreeNodeDto target = arg1.getMappedInstance(arg0, TreeNodeDto.class);
    if (target != null) {
        return target;
    }

    if (arg0 == null) {
        return null;
    }

    TreeNodeDto treeNodeDto = new TreeNodeDto();

    arg1.storeMappedInstance(arg0, treeNodeDto);

    treeNodeDto.setParent(demoConvertMapperAdapterForCycleAvoiding.iglm_TreeNodeToTreeNodeDto(arg0.getParent(), arg1));
    treeNodeDto.setChildren(
        demoConvertMapperAdapterForCycleAvoiding.iglm_TreeNodeToTreeNodeDto(arg0.getChildren(), arg1));

    return treeNodeDto;
}
```