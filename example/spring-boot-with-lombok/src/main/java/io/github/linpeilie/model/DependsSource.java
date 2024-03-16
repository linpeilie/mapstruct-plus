package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

@Data
@AutoMapper(target = DependsTarget.class)
public class DependsSource {

    private String firstName;
    private String lastName;
    @AutoMapping(dependsOn = {"firstName", "lastName"})
    private String fullName;

}
