package io.github.linpeilie;

import io.github.linpeilie.me.source.nullvaluecheckstrategy.RockFestivalSource;
import io.github.linpeilie.me.source.nullvaluecheckstrategy.RockFestivalTarget;
import io.github.linpeilie.me.source.nullvaluecheckstrategy.RockFestivalTarget1;
import io.github.linpeilie.me.source.nullvaluecheckstrategy.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = Application.class)
public class PresenceCheckTest {

    @Autowired
    private Converter converter;

    @Test
    public void testCallingMappingMethodWithNullSource() {

        RockFestivalSource source = new RockFestivalSource();
        RockFestivalTarget target = converter.convert(source, RockFestivalTarget.class);
        assertThat(target.getStage()).isNull();

        source.setArtistName("New Order");
        target = converter.convert(source, RockFestivalTarget.class);
        assertThat(target.getStage()).isEqualTo(Stage.THE_BARN);

    }

    @Test
    public void testCallingMappingMethodWithNullSourceOveridingConfig() {

        RockFestivalSource source = new RockFestivalSource();
        assertThatThrownBy(() -> converter.convert(source, RockFestivalTarget1.class)).isInstanceOf(
            IllegalArgumentException.class);
    }
}
