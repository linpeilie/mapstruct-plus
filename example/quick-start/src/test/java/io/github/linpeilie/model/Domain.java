package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;

/**
 * 测试一对多映射类
 *
 * @author lipanre
 */
@AutoMapper(target = DomainVO.class)
@AutoMapper(target = DomainDTO.class)
public class Domain {

    @AutoMapping(target = "voValue", targetClass = DomainVO.class)
    @AutoMapping(target = "dtoValue", targetClass = DomainDTO.class)
    private String value;

    public Domain(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
