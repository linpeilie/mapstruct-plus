package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@AutoMapper(target = SVO.class)
public class SDto extends PDto {

    private Long id;

}
