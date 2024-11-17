package io.github.linpeilie.me.annotation.rams;

import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import io.github.linpeilie.annotations.ReverseAutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMappings;

@ReverseAutoMappings({
    @ReverseAutoMapping(source = "updateBy", target = "operatorUserId")})
@AutoMappings({
    @AutoMapping(source = "operatorUserId", target = "updateBy")})
public @interface UpdateMapper {

}
