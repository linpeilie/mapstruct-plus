/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.iterabletononiterable;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.List;

/**
 *
 * @author Saheb Preet Singh
 */
@AutoMapper(target = FruitsMenu.class)
public class FruitSalad {

    private List<Fruit> fruits;

    public FruitSalad(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }
}
