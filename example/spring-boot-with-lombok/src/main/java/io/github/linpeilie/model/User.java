package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@AutoMappers({
    @AutoMapper(target = UserDto.class),
    @AutoMapper(target = UserVO.class)
})
public class User {

    private String username;

    private int age;
    private boolean young;

    @AutoMapping(targetClass = UserDto.class, target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList;

    @AutoMappings({
        @AutoMapping(targetClass = UserDto.class, target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss"),
        @AutoMapping(targetClass = UserVO.class, target = "birthday", ignore = true)
    })
    private Date birthday;

    @AutoMapping(targetClass = UserDto.class, target = "assets", numberFormat = "$0.00")
    private double assets;

    @AutoMapping(target = "money", numberFormat = "$0.00")
    private double money;

    @AutoMappings({
        @AutoMapping(targetClass = UserVO.class, target = "voField")
    })
    private String voField;

}
