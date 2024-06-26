/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.adder;

import com.google.common.collect.ImmutableMap;
import io.github.linpeilie.me.collection.adder._target.IndoorPet;
import io.github.linpeilie.me.collection.adder._target.OutdoorPet;
import io.github.linpeilie.me.collection.adder._target.Pet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

/**
 * @author Sjaak Derksen
 */
@Component
public class PetMapper {

    private static final Map<String, Long> PETS_TO_TARGET = ImmutableMap.<String, Long>builder()
        .put( "rabbit", 1L )
        .put( "mouse", 2L ).build();

    private static final Map<Long, String> PETS_TO_SOURCE = ImmutableMap.<Long, String>builder()
        .put( 1L, "rabbit" )
        .put( 2L, "mouse" )
        .put( 3L, "cat" )
        .put( 4L, "dog" ).build();

    /**
     * method to be used when using an adder
     *
     * @param pet
     *
     * @return
     *
     * @throws CatException
     * @throws DogException
     */
    public Long toPet(String pet) throws CatException, DogException {
        if ( "cat".equals( pet ) ) {
            throw new CatException();
        }
        else if ( "dog".equals( pet ) ) {
            throw new DogException();
        }
        return PETS_TO_TARGET.get( pet );
    }

    /**
     * Method to be used when not using an adder
     *
     * @param pets
     *
     * @return
     *
     * @throws CatException
     * @throws DogException
     */
    public List<Long> toPets(List<String> pets) throws CatException, DogException {
        List<Long> result = new ArrayList<>();
        for ( String pet : pets ) {
            result.add( toPet( pet ) );
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T extends Pet> T toPet(String pet, @TargetType Class<T> clazz) throws CatException, DogException {
        if ( clazz == IndoorPet.class ) {
            return (T) new IndoorPet( toPet( pet ) );
        }
        if ( clazz == OutdoorPet.class ) {
            return (T) new OutdoorPet( toPet( pet ) );
        }
        return null;
    }

    public List<String> toSourcePets(List<Long> pets) throws CatException, DogException {
        List<String> result = new ArrayList<>();
        for ( Long pet : pets ) {
            result.add( PETS_TO_SOURCE.get( pet ) );
        }
        return result;
    }
}
