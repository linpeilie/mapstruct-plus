/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.collection.immutabletarget.CupboardDto;
import io.github.linpeilie.me.collection.immutabletarget.CupboardEntity;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sjaak Derksen
 */
@SpringBootTest(classes = Application.class)
public class ImmutableProductTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldHandleImmutableTarget() {

        CupboardDto in = new CupboardDto();
        in.setContent(Arrays.asList("cups", "soucers"));
        CupboardEntity out = new CupboardEntity();
        out.setContent(Collections.emptyList());

        converter.convert(in, out);

        assertThat(out.getContent()).isNotNull();
        assertThat(out.getContent()).containsExactly("cups", "soucers");
    }

}
