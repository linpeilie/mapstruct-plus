package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AutoMapper(target = ProductDto.class, cycleAvoiding = true)
public class Product {

    private Long id;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ProductProperty> productPropertyList;

}
