package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@Data
@AutoMapper(target = User.class)
public class UserDto {

    private String username;
    private int age;
    private boolean young;

    @AutoMapping(target = "educationList")
    private String educations;

    private String birthday;

    private String assets;

    private String money;

}
