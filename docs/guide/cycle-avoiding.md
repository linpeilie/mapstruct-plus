---
title: 类循环嵌套
order: 7
category:
- 指南
description: MapStructPlus MapStructPlus类循环嵌套 CycleAvoiding
---

## 背景

类循环嵌套是指两个类互相引用，例如，源对象和目标对象结构都包含父对象和子对象之间的双向关联。
当存在这种情况时，直接进行转换时，会导致栈溢出的问题（stack overflow error）。

示例：

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

`parent` 属性可以是其他类型的，可能跨越一个更长的属性链形成的嵌套循环。

为了适配这种情况，MapStructPlus 的 **`AutoMapper`** 注解中增加了 **`cycleAvoiding`** 属性，该属性用于标识，是否需要避免循环嵌套的问题。
默认为 `false`，如果需要避免循环嵌套，需要将该属性设置为 `true`。

当配置为 `true` 时，在整个对象的转换过程链路中，会传递一个 `CycleAvoidingMappingContext` 对象，临时保存转换生成的对象，
在转换链路中，如果发现需要生成的对象已经存在，会直接返回该类型，从而避免栈溢出问题。
所以，配置该属性为 `true` 时，会有一点的性能消耗，如果没有循环嵌套的情况，使用默认配置即可，避免不必要的性能消耗。

## 使用示例

以上面的示例为例，在 `AutoMapper` 注解中，配置 `cycleAvoiding` 属性为 `true`，如下所示：

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

编译生成的转换逻辑如下：

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