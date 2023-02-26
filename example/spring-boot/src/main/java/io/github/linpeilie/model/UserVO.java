package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapping;
import java.util.Date;
import java.util.List;

public class UserVO {

    private String username;

    private int age;
    private boolean young;

    private List<String> educationList;

    private Date birthday;

    private double assets;

    private String voField;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(final String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserVO{" +
               "username='" + username + '\'' +
               ", age=" + age +
               ", young=" + young +
               ", educationList=" + educationList +
               ", birthday=" + birthday +
               ", assets=" + assets +
               ", voField='" + voField + '\'' +
               ", money='" + money + '\'' +
               '}';
    }

}
