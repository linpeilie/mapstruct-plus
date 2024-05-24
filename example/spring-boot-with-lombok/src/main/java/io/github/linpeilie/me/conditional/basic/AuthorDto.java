package io.github.linpeilie.me.conditional.basic;

import java.util.List;

public class AuthorDto {

    private List<BookDto> books;

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

}
