package io.github.linpeilie.me.annotation.ams;

import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;

@AutoMappings({
    @AutoMapping(source = "operatorUserId", target = "createBy")
})
public @interface CreateMapper {
}
