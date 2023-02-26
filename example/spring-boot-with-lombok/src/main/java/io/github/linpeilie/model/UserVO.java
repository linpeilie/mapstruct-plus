package io.github.linpeilie.model;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class UserVO {

    private String username;

    private int age;
    private boolean young;

    private List<String> educationList;

    private Date birthday;

    private double assets;

    private String voField;

    private String money;

}
