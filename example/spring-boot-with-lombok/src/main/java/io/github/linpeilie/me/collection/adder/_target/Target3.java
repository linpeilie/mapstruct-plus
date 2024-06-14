package io.github.linpeilie.me.collection.adder._target;

import java.util.ArrayList;
import java.util.List;

public class Target3 {

    private List<Long> pets;

    public List<Long> getPets() {
        return pets;
    }

    public void setPets(List<Long> pets) {
        this.pets = pets;
    }

    public void addCat(Long cat) {
        // dummy method to test selection mechanism
    }

    public void addDog(Long cat) {
        // dummy method to test selection mechanism
    }

    public void addPets(Long cat) {
        // dummy method to test selection mechanism
    }

    public Long addPet(Long pet) {
        AdderUsageObserver.setUsed( true );
        if ( pets == null ) {
            pets = new ArrayList<>();
        }
        pets.add( pet );
        return pet;
    }
}
