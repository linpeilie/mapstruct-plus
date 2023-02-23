package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.Objects;

@AutoMapper(target = UserDto.class)
public class User {

    private String username;
    private int age;
    private boolean young;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return getAge() == user.getAge() && isYoung() == user.isYoung() &&
               Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAge(), isYoung());
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", age=" + age +
               ", young=" + young +
               '}';
    }
}
