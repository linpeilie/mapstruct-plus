/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.collection.iterabletononiterable.Fruit;
import io.github.linpeilie.me.collection.iterabletononiterable.FruitSalad;
import io.github.linpeilie.me.collection.iterabletononiterable.FruitsMenu;
import io.github.linpeilie.me.collection.iterabletononiterable.Source;
import io.github.linpeilie.me.collection.iterabletononiterable.Target;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class IterableToNonIterableMappingTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldMapStringListToStringUsingCustomMapper() {
        Source source = new Source();
        source.setNames(Arrays.asList("Alice", "Bob", "Jim"));
        source.publicNames = Arrays.asList("Alice", "Bob", "Jim");
        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getNames()).isEqualTo("Alice-Bob-Jim");
        assertThat(target.publicNames).isEqualTo("Alice-Bob-Jim");
    }

    @Test
    public void shouldReverseMapStringListToStringUsingCustomMapper() {
        Target target = new Target();
        target.setNames("Alice-Bob-Jim");
        target.publicNames = "Alice-Bob-Jim";

        Source source = converter.convert(target, Source.class);

        assertThat(source).isNotNull();
        assertThat(source.getNames()).isEqualTo(Arrays.asList("Alice", "Bob", "Jim"));
        assertThat(source.publicNames).isEqualTo(Arrays.asList("Alice", "Bob", "Jim"));
    }

    @Test
    public void shouldMapIterableToNonIterable() {
        List<Fruit> fruits = Arrays.asList(new Fruit("mango"), new Fruit("apple"),
            new Fruit("banana"));
        FruitsMenu menu = new FruitsMenu(fruits);
        FruitSalad salad = converter.convert(menu, FruitSalad.class);
        assertThat(salad.getFruits().get(0).getType()).isEqualTo("mango");
        assertThat(salad.getFruits().get(1).getType()).isEqualTo("apple");
        assertThat(salad.getFruits().get(2).getType()).isEqualTo("banana");
    }

    @Test
    public void shouldMapNonIterableToIterable() {
        List<Fruit> fruits = Arrays.asList(new Fruit("mango"), new Fruit("apple"),
            new Fruit("banana"));
        FruitSalad salad = new FruitSalad(fruits);
        FruitsMenu menu = converter.convert(salad, FruitsMenu.class);
        assertThat(salad.getFruits()).extracting(Fruit::getType).containsExactly("mango", "apple", "banana");
    }
}
