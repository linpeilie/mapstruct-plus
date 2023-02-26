package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;
import lombok.Data;

@AutoMapper(target = SysMenuVo.class, reverseConvertGenerate = false)
@Data
public class SysMenu {

    private String path;

    private List<SysMenu> children;

}
