package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AutoMapper(target = UserDto.class)
@AutoMapper(target = UserVO.class)
public class User {

    private String username;

    private int age;
    private boolean young;

    @AutoMapping(targetClass = UserDto.class, target = "educations", expression = "java(java.lang.String.join(\",\", source.getEducationList()))")
    private List<String> educationList = new ArrayList<>();

    @AutoMapping(targetClass = UserDto.class, target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @AutoMapping(targetClass = UserVO.class, target = "birthday", ignore = true)
    @AutoMapping(targetClass = UserVO.class, target = "birthday2")
    private Date birthday;

    @AutoMapping(targetClass = UserDto.class, target = "assets", numberFormat = "$0.00")
    private double assets;

    @AutoMapping(target = "money", numberFormat = "$0.00")
    private double money;

    @AutoMappings({
        @AutoMapping(targetClass = UserVO.class, target = "voField")
    })
    private String voField;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    public double getAssets() {
        return assets;
    }

    public void setAssets(final double assets) {
        this.assets = assets;
    }

    public String getVoField() {
        return voField;
    }

    public void setVoField(final String voField) {
        this.voField = voField;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(final double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", age=" + age +
               ", young=" + young +
               ", educationList=" + educationList +
               ", birthday=" + birthday +
               ", assets=" + assets +
               ", money=" + money +
               ", voField='" + voField + '\'' +
               '}';
    }
}
