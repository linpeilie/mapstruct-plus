package io.github.linpeilie;

import io.github.linpeilie.me.collection.adder.DogException;
import io.github.linpeilie.me.collection.adder._target.AdderUsageObserver;
import io.github.linpeilie.me.collection.adder._target.Target;
import io.github.linpeilie.me.collection.adder._target.Target2;
import io.github.linpeilie.me.collection.adder._target.Target3;
import io.github.linpeilie.me.collection.adder._target.TargetDali;
import io.github.linpeilie.me.collection.adder._target.TargetHuman;
import io.github.linpeilie.me.collection.adder._target.TargetOnlyGetter;
import io.github.linpeilie.me.collection.adder._target.TargetViaTargetType;
import io.github.linpeilie.me.collection.adder._target.TargetWithAnimals;
import io.github.linpeilie.me.collection.adder._target.TargetWithoutSetter;
import io.github.linpeilie.me.collection.adder.source.Foo;
import io.github.linpeilie.me.collection.adder.source.SingleElementSource;
import io.github.linpeilie.me.collection.adder.source.Source;
import io.github.linpeilie.me.collection.adder.source.Source2;
import io.github.linpeilie.me.collection.adder.source.Source3;
import io.github.linpeilie.me.collection.adder.source.SourceTeeth;
import io.github.linpeilie.me.collection.adder.source.SourceWithPets;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class)
public class AdderTest {

    @Autowired
    private Converter converter;

    @Test
    public void testAdd() throws DogException {
        AdderUsageObserver.setUsed(false);

        Source source = new Source();
        source.setPets(Arrays.asList("mouse"));

        Target target = converter.convert(source, Target.class);
        assertThat(target).isNotNull();
        assertThat(target.getPets().size()).isEqualTo(1);
        assertThat(target.getPets().get(0)).isEqualTo(2L);
        assertTrue(AdderUsageObserver.isUsed());
    }

    @Test
    public void testAddWithExceptionNotInThrowsClause() throws DogException {
        AdderUsageObserver.setUsed(false);

        Source source = new Source();
        source.setPets(Arrays.asList("cat"));

        assertThatThrownBy(() -> converter.convert(source, Target.class))
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testAddWithExistingTarget() {
        AdderUsageObserver.setUsed(false);

        Source source = new Source();
        source.setPets(Arrays.asList("mouse"));

        Target target = new Target();
        target.setPets(new ArrayList<>(Arrays.asList(1L)));

        converter.convert(source, target);
        assertThat(target).isNotNull();
        assertThat(target.getPets().size()).isEqualTo(2);
        assertThat(target.getPets().get(0)).isEqualTo(1L);
        assertThat(target.getPets().get(1)).isEqualTo(2L);
        assertTrue(AdderUsageObserver.isUsed());
    }

    @Test
    public void testShouldUseDefaultStrategy() throws DogException {
        AdderUsageObserver.setUsed(false);

        Source3 source = new Source3();
        source.setPets(Arrays.asList("mouse"));

        Target3 target = converter.convert(source, Target3.class);
        assertThat(target).isNotNull();
        assertThat(target.getPets().size()).isEqualTo(1);
        assertThat(target.getPets().get(0)).isEqualTo(2L);
        assertFalse(AdderUsageObserver.isUsed());
    }

    @Test
    public void testShouldPreferSetterStrategyButThereIsNone() throws DogException {
        AdderUsageObserver.setUsed(false);

        Source source = new Source();
        source.setPets(Arrays.asList("mouse"));

        TargetWithoutSetter target = converter.convert(source, TargetWithoutSetter.class);
        assertThat(target).isNotNull();
        assertThat(target.getPets().size()).isEqualTo(1);
        assertThat(target.getPets().get(0)).isEqualTo(2L);
        assertTrue(AdderUsageObserver.isUsed());
    }

    @Test
    public void testShouldPreferHumanSingular() {

        AdderUsageObserver.setUsed(false);

        SourceTeeth source = new SourceTeeth();
        source.setTeeth(Arrays.asList("moler"));

        TargetHuman target = converter.convert(source, TargetHuman.class);
        assertThat(target).isNotNull();
        assertThat(target.getTeeth().size()).isEqualTo(1);
        assertThat(target.getTeeth().get(0)).isEqualTo(3);
        assertTrue(AdderUsageObserver.isUsed());
    }

    @Test
    public void testShouldFallBackToDaliSingularInAbsenceOfHumanSingular() {
        AdderUsageObserver.setUsed(false);

        SourceTeeth source = new SourceTeeth();
        source.setTeeth(Arrays.asList("moler"));

        TargetDali target = converter.convert(source, TargetDali.class);
        assertThat(target).isNotNull();
        assertThat(target.getTeeth().size()).isEqualTo(1);
        assertThat(target.getTeeth().get(0)).isEqualTo(3);
        assertTrue(AdderUsageObserver.isUsed());
    }

    @Test
    public void testAddReverse() {
        AdderUsageObserver.setUsed(false);

        Target source = new Target();
        source.setPets(Arrays.asList(3L));

        Source target = converter.convert(source, Source.class);
        assertThat(target).isNotNull();
        assertThat(target.getPets().size()).isEqualTo(1);
        assertThat(target.getPets().get(0)).isEqualTo("cat");
    }

    @Test
    public void testAddOnlyGetter() throws DogException {
        AdderUsageObserver.setUsed(false);

        Source source = new Source();
        source.setPets(Arrays.asList("mouse"));

        TargetOnlyGetter target = converter.convert(source, TargetOnlyGetter.class);
        assertThat(target).isNotNull();
        assertThat(target.getPets().size()).isEqualTo(1);
        assertThat(target.getPets().get(0)).isEqualTo(2L);
        assertTrue(AdderUsageObserver.isUsed());
    }

    @Test
    public void testAddViaTargetType() {
        AdderUsageObserver.setUsed(false);

        Source source = new Source();
        source.setPets(Arrays.asList("mouse"));

        TargetViaTargetType target = converter.convert(source, TargetViaTargetType.class);
        assertThat(target).isNotNull();
        assertThat(target.getPets().size()).isEqualTo(1);
        assertThat(target.getPets().get(0)).isNotNull();
        assertThat(target.getPets().get(0).getValue()).isEqualTo(2L);
        assertTrue(AdderUsageObserver.isUsed());
    }

    @Test
    public void testSingleElementSource() {
        AdderUsageObserver.setUsed(false);

        SingleElementSource source = new SingleElementSource();
        source.setPet("mouse");

        Target target = converter.convert(source, Target.class);
        assertThat(target).isNotNull();
        assertThat(target.getPets().size()).isEqualTo(1);
        assertThat(target.getPets().get(0)).isEqualTo(2L);
        assertTrue(AdderUsageObserver.isUsed());
    }

    @Test
    public void testMissingImport() {
        Source2 source = new Source2();
        source.setAttributes(Arrays.asList(new Foo()));

        Target2 target = converter.convert(source, Target2.class);
        assertThat(target).isNotNull();
        assertThat(target.getAttributes().size()).isEqualTo(1);
    }

    @Test
    public void useIterationNameFromSource() {
        SourceWithPets source = new SourceWithPets();
        source.setPets(Arrays.asList("dog", "cat"));

        TargetWithAnimals target = converter.convert(source, TargetWithAnimals.class);

        assertThat(target.getAnimals()).containsExactly("dog", "cat");
    }

}
