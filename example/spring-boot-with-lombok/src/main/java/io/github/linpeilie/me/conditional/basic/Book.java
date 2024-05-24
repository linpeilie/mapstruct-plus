package io.github.linpeilie.me.conditional.basic;

import io.github.linpeilie.annotations.AutoMapper;

@AutoMapper(target = BookDto.class, reverseConvertGenerate = false)
public class Book {

    private final String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
