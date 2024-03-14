package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AutoMapper(target = ProductPropertyDto.class, cycleAvoiding = true)
public class ProductProperty {

    private Long id;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;

}
