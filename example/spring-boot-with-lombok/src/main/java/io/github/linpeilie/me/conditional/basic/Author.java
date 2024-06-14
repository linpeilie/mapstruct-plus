package io.github.linpeilie.me.conditional.basic;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;

@AutoMapper(target = AuthorDto.class, reverseConvertGenerate = false,
    uses = ConditionalMethodForCollectionMapper.class)
public class Author {

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public boolean hasBooks() {
        return false;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
