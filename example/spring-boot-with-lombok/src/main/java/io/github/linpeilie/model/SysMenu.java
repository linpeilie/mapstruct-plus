package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.List;
import lombok.Data;

@AutoMapper(target = SysMenuVo.class, reverseConvertGenerate = false)
@Data
public class SysMenu {

    @AutoMapping(target = "path", defaultValue = "/")
    private String path;

    private List<SysMenu> children;

}
