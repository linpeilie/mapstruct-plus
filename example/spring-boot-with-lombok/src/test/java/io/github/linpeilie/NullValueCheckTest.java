/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.nullcheck.strategy.HouseDto;
import io.github.linpeilie.me.nullcheck.strategy.HouseEntity;
import io.github.linpeilie.me.nullcheck.strategy.HouseEntity1;
import io.github.linpeilie.me.nullcheck.strategy.HouseEntity2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class NullValueCheckTest {

    @Autowired
    private Converter converter;

    @Test
    public void testDefinedOnMapper() {

        HouseEntity entity = converter.convert(new HouseDto(), HouseEntity.class);

        assertThat(entity).isNotNull();
        assertThat(entity.ownerSet()).isFalse();
        assertThat(entity.numberSet()).isFalse();

    }

    @Test
    public void testDefinedOnBean() {

        HouseEntity1 entity = converter.convert(new HouseDto(), HouseEntity1.class);

        assertThat(entity).isNotNull();
        assertThat(entity.ownerSet()).isTrue();
        assertThat(entity.numberSet()).isTrue();

    }

    @Test
    public void testDefinedOnMapping() {

        HouseEntity2 entity = converter.convert(new HouseDto(), HouseEntity2.class);

        assertThat(entity).isNotNull();
        assertThat(entity.ownerSet()).isTrue();
        assertThat(entity.numberSet()).isFalse();

    }

}
