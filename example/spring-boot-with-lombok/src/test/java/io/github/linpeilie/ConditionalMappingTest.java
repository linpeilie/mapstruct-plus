/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.conditional.basic.Author;
import io.github.linpeilie.me.conditional.basic.AuthorDto;
import io.github.linpeilie.me.conditional.basic.BasicEmployee;
import io.github.linpeilie.me.conditional.basic.BasicEmployee1;
import io.github.linpeilie.me.conditional.basic.BasicEmployee2;
import io.github.linpeilie.me.conditional.basic.BasicEmployee3;
import io.github.linpeilie.me.conditional.basic.BasicEmployeeDto;
import io.github.linpeilie.me.conditional.basic.Book;
import io.github.linpeilie.me.conditional.basic.BookDto;
import io.github.linpeilie.me.conditional.basic.EmployeeDto;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Filip Hrisafov
 */
@SpringBootTest(classes = Application.class)
public class ConditionalMappingTest {

    @Autowired
    private Converter converter;

    @Test
    public void conditionalMethodInMapper() {
        BasicEmployee employee = converter.convert(new BasicEmployeeDto("Tester"), BasicEmployee.class);
        assertThat(employee.getName()).isEqualTo("Tester");

        employee = converter.convert(new BasicEmployeeDto(""), BasicEmployee.class);
        assertThat(employee.getName()).isNull();

        employee = converter.convert(new BasicEmployeeDto("    "), BasicEmployee.class);
        assertThat(employee.getName()).isNull();
    }

    @Test
    public void conditionalMethodAndBeanPresenceCheckMapper() {
        BasicEmployee employee = converter.convert(new EmployeeDto("Tester"), BasicEmployee.class);
        assertThat(employee.getName()).isEqualTo("Tester");

        employee = converter.convert(new EmployeeDto(""), BasicEmployee.class);
        assertThat(employee.getName()).isNull();

        employee = converter.convert(new EmployeeDto("    "), BasicEmployee.class);
        assertThat(employee.getName()).isNull();
    }

    @Test
    public void conditionalMethodWithSourceParameter() {
        BasicEmployee3 employee = converter.convert(new BasicEmployeeDto("Tester"), BasicEmployee3.class);
        assertThat(employee.getName()).isNull();

        employee = converter.convert(new BasicEmployeeDto("Tester", "map"), BasicEmployee3.class);
        assertThat(employee.getName()).isEqualTo("Tester");
    }

    @Test
    public void conditionalMethodWithSourceParameterAndValue() {
        BasicEmployee2 employee = converter.convert(new BasicEmployeeDto("    ", "empty"), BasicEmployee2.class);
        assertThat(employee.getName()).isEqualTo("    ");

        employee = converter.convert(new BasicEmployeeDto("    ", "blank"), BasicEmployee2.class);
        assertThat(employee.getName()).isNull();

        employee = converter.convert(new BasicEmployeeDto("Tester", "blank"), BasicEmployee2.class);
        assertThat(employee.getName()).isEqualTo("Tester");
    }

    @Test
    public void conditionalMethodForCollection() {
        Author author = new Author();
        AuthorDto dto = converter.convert(author, AuthorDto.class);

        assertThat(dto.getBooks()).isNull();

        author.setBooks(Collections.emptyList());
        dto = converter.convert(author, AuthorDto.class);

        assertThat(dto.getBooks()).isNull();

        author.setBooks(Arrays.asList(
            new Book("Test"),
            new Book("Test Vol. 2")
        ));
        dto = converter.convert(author, AuthorDto.class);

        assertThat(dto.getBooks())
            .extracting(BookDto::getName)
            .containsExactly("Test", "Test Vol. 2");
    }

    @Test
    public void conditionalMethodWithMappingTarget() {
        BasicEmployee1 targetEmployee = new BasicEmployee1();
        targetEmployee.setName("CurrentName");
        converter.convert(new BasicEmployeeDto("ReplacementName"), targetEmployee);

        assertThat(targetEmployee.getName()).isEqualTo("CurrentName");
    }
}
