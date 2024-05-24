/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.nullcheck.NullObject;
import io.github.linpeilie.me.nullcheck.Source;
import io.github.linpeilie.me.nullcheck.Target;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = Application.class)
public class NullCheckTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldThrowNullptrWhenCustomMapperIsInvoked() {

        Source source = new Source();
        source.setNumber("5");
        source.setSomeInteger(7);
        source.setSomeLong(2L);

        assertThatThrownBy(() -> converter.convert(source, Target.class))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldSurroundTypeConversionWithNullCheck() {

        Source source = new Source();
        source.setSomeObject(new NullObject());
        source.setSomeInteger(7);
        source.setSomeLong(2L);

        Target target = converter.convert(source, Target.class);

        assertThat(target.getNumber()).isNull();

    }

    @Test
    public void shouldSurroundArrayListConstructionWithNullCheck() {

        Source source = new Source();
        source.setSomeObject(new NullObject());
        source.setSomeInteger(7);
        source.setSomeLong(2L);

        Target target = converter.convert(source, Target.class);

        assertThat(target.getSomeList()).isNull();
    }

    @Test
    public void shouldSurroundConversionPassedToMappingMethodWithNullCheck() {

        Source source = new Source();
        source.setSomeObject(new NullObject());
        source.setSomeLong(2L);

        Target target = converter.convert(source, Target.class);

        assertThat(target.getSomeList()).isNull();
        assertThat(target.getSomeInteger()).isNull();
    }

    @Test
    public void shouldSurroundConversionFromWrappedPassedToMappingMethodWithPrimitiveArgWithNullCheck() {

        Source source = new Source();
        source.setSomeObject(new NullObject());
        source.setSomeInteger(7);

        Target target = converter.convert(source, Target.class);

        assertThat(target.getSomeList()).isNull();
        assertThat(target.getSomeLong()).isNull();
    }
}
