package io.github.linpeilie.model;

import io.github.linpeilie.StringToListStringConverter;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.List;
import java.util.Objects;

@AutoMapper(target = User.class, uses = StringToListStringConverter.class)
public class UserDto {

    private String username;
    private int age;
    private boolean young;

    @AutoMapping(target = "educationList")
    private String educations;

    private String birthday;

    private String assets;

    private String money;

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

    public String getEducations() {
        return educations;
    }

    public void setEducations(final String educations) {
        this.educations = educations;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(final String assets) {
        this.assets = assets;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(final String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserDto{" +
               "username='" + username + '\'' +
               ", age=" + age +
               ", young=" + young +
               ", educations='" + educations + '\'' +
               ", birthday='" + birthday + '\'' +
               ", assets='" + assets + '\'' +
               ", money='" + money + '\'' +
               '}';
    }
}
