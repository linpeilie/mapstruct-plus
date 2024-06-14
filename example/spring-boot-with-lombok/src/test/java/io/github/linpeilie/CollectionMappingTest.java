package io.github.linpeilie;

import com.google.common.collect.ImmutableMap;
import io.github.linpeilie.me.collection.forged.Source;
import io.github.linpeilie.me.collection.forged.Source1;
import io.github.linpeilie.me.collection.forged.Target;
import io.github.linpeilie.me.collection.forged.Target1;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class CollectionMappingTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldForgeNewMapMappingMethod() {

        Map<String, Long> sourceMap = ImmutableMap.<String, Long>builder().put("rabbit", 1L).build();
        Source source = new Source();
        source.setBarMap(sourceMap);
        source.publicBarMap = ImmutableMap.<String, Long>builder().put("fox", 2L).build();

        Target target = converter.convert(source, Target.class);
        assertThat(target).isNotNull();
        Map<String, String> targetMap = ImmutableMap.<String, String>builder().put("rabbit", "1").build();
        Map<String, String> targetMap2 = ImmutableMap.<String, String>builder().put("fox", "2").build();
        assertThat(target.getBarMap()).isEqualTo(targetMap);
        assertThat(target.getPublicBarMap()).isEqualTo(targetMap2);

        Source source2 = converter.convert(target, Source.class);
        assertThat(source2).isNotNull();
        assertThat(source2.getBarMap()).isEqualTo(sourceMap);
        assertThat(source2.publicBarMap).isEqualTo(source.publicBarMap);
    }

    @Test
    public void shouldForgeNewIterableMappingMethodReturnNullOnNullSource() {

        Source source = new Source();
        source.setFooSet(null);
        source.publicFooSet = null;

        Target target = converter.convert(source, Target.class);
        assertThat(target).isNotNull();
        assertThat(target.getFooSet()).isNull();
        assertThat(target.getPublicFooSet()).isNull();

        Source source2 = converter.convert(target, Source.class);
        assertThat(source2).isNotNull();
        assertThat(source2.getFooSet()).isNull();
        assertThat(source2.publicFooSet).isNull();
    }

    @Test
    public void shouldForgeNewMapMappingMethodReturnNullOnNullSource() {

        Source source = new Source();
        source.setBarMap(null);
        source.publicBarMap = null;

        Target target = converter.convert(source, Target.class);
        assertThat(target).isNotNull();
        assertThat(target.getBarMap()).isNull();
        assertThat(target.getPublicBarMap()).isNull();

        Source source2 = converter.convert(target, Source.class);
        assertThat(source2).isNotNull();
        assertThat(source2.getBarMap()).isNull();
        assertThat(source2.publicBarMap).isNull();
    }

    @Test
    public void shouldForgeNewIterableMappingMethodReturnEmptyOnNullSource() {

        Source1 source = new Source1();
        source.setFooSet(null);
        source.publicFooSet = null;

        Target1 target = converter.convert(source, Target1.class);
        assertThat(target).isNotNull();
        assertThat(target.getFooSet()).isEmpty();
        assertThat(target.getPublicFooSet()).isEmpty();

        target.setPublicBarMap(null);

        Source1 source2 = converter.convert(target, Source1.class);
        assertThat(source2).isNotNull();
        assertThat(source2.getFooSet()).isEmpty();
        assertThat(source2.publicBarMap).isEmpty();
    }

    @Test
    public void shouldForgeNewMapMappingMethodReturnEmptyOnNullSource() {

        Source1 source = new Source1();
        source.setBarMap(null);
        source.publicBarMap = null;

        Target1 target = converter.convert(source, Target1.class);
        assertThat(target).isNotNull();
        assertThat(target.getBarMap()).isEmpty();
        assertThat(target.getPublicBarMap()).isEmpty();

        target.setPublicBarMap(null);

        Source1 source2 = converter.convert(target, Source1.class);
        assertThat(source2).isNotNull();
        assertThat(source2.getBarMap()).isEmpty();
        assertThat(source2.publicBarMap).isEmpty();
    }
}
