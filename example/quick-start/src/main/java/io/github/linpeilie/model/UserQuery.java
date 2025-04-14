package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.ReverseAutoMapping;

/**
 * @author lipanre
 */
@AutoMapper(target = User.class)
@AutoMapper(target = UserDto.class)
public class UserQuery {

    @AutoMapping(targetClass = User.class, target = "username")
    @AutoMapping(targetClass = UserDto.class, target = "username")
    private String name;

    @ReverseAutoMapping(targetClass = User.class, source = "age")
    @ReverseAutoMapping(targetClass = UserDto.class, source = "age")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
