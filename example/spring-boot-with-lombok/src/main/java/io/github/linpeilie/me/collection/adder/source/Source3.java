package io.github.linpeilie.me.collection.adder.source;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.me.collection.adder.PetMapper;
import io.github.linpeilie.me.collection.adder._target.Target3;
import java.util.List;

@AutoMapper(target = Target3.class, uses = PetMapper.class)
public class Source3 {

    private List<String> pets;

    public List<String> getPets() {
        return pets;
    }

    public void setPets(List<String> pets) {
        this.pets = pets;
    }
}
