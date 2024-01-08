package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@AutoMapper(target = SDto.class)
public class SVO extends PVO {

    private Long id;

}
