package io.github.linpeilie.me.annotation.ram;

import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMapping;

@ReverseAutoMapping(source = "createBy", target = "operatorUserId")
@AutoMapping(source = "operatorUserId", target = "createBy")
public @interface CreateMapper {
}
