package io.github.linpeilie;

import io.github.linpeilie.me.collection.defaultimplementation.Source;
import io.github.linpeilie.me.collection.defaultimplementation.SourceFoo;
import io.github.linpeilie.me.collection.defaultimplementation.Target;
import io.github.linpeilie.me.collection.defaultimplementation.TargetFoo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@SpringBootTest(classes = Application.class)
public class DefaultCollectionImplementationTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldUseDefaultImplementationForList() {
        List<TargetFoo> target = converter.convert(createSourceFooList(), TargetFoo.class);

        assertResultList(target);
    }

    @Test
    public void shouldUseDefaultImplementationForListWithoutSetter() {
        Source source = new Source();
        source.setFooList(createSourceFooList());
        Target target = converter.convert(source, Target.class);

        assertThat(target).isNotNull();
        assertThat(target.getFooListNoSetter()).containsExactly(new TargetFoo("Bob"), new TargetFoo("Alice"));
    }

    private void assertResultList(Iterable<TargetFoo> fooIterable) {
        assertThat(fooIterable).isNotNull();
        assertThat(fooIterable).containsOnly(new TargetFoo("Bob"), new TargetFoo("Alice"));
    }

    private List<SourceFoo> createSourceFooList() {
        return Arrays.asList(new SourceFoo("Bob"), new SourceFoo("Alice"));
    }

}
