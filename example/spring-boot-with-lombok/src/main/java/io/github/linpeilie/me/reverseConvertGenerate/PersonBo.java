package io.github.linpeilie.me.reverseConvertGenerate;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMappings;
import lombok.Data;

@AutoMapper(target = PersonVo.class)
@Data
public class PersonBo {

    @ReverseAutoMappings({
        @ReverseAutoMapping(source = "name")
    })
    private String n;
    private Integer age;

}
