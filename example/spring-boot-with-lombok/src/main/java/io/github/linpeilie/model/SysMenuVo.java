package io.github.linpeilie.model;

import java.util.List;
import lombok.Data;

@Data
public class SysMenuVo {

    private String path;

    private List<SysMenuVo> children;

}
