package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.List;
import java.util.Objects;

@AutoMapper(target = UserDto.class)
public class User {

    private String username;
    private int age;
    private boolean young;

    @AutoMapping(target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public boolean isYoung() {
        return young;
    }

    public void setYoung(final boolean young) {
        this.young = young;
    }

    public List<String> getEducationList() {
        return educationList;
    }

    public void setEducationList(final List<String> educationList) {
        this.educationList = educationList;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", age=" + age +
               ", young=" + young +
               ", educationList=" + educationList +
               '}';
    }
}
