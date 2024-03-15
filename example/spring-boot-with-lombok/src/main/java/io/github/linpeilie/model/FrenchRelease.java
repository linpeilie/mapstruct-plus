package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.mapper.Titles;
import lombok.Data;

@Data
@AutoMapper(target = EnglishRelease.class, uses = Titles.class)
public class FrenchRelease {

    @AutoMapping(qualifiedByName = "FrenchToEnglish")
    private String title;

}
