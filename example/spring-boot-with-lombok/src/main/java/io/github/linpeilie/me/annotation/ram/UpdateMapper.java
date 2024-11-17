package io.github.linpeilie.me.annotation.ram;

import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMapping;

@ReverseAutoMapping(source = "updateBy", target = "operatorUserId")
@AutoMapping(source = "operatorUserId", target = "updateBy")
public @interface UpdateMapper {

}
