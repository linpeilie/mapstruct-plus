package io.github.linpeilie;

import io.github.linpeilie.me.collection.defaultimplementation.NoSetterSource;
import io.github.linpeilie.me.collection.defaultimplementation.NoSetterTarget;
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
public class NoSetterCollectionMappingTest {

    @Autowired
    private Converter converter;

    @Test
    public void compilesAndMapsCorrectly() {
        NoSetterSource source = new NoSetterSource();
        source.setListValues(Arrays.asList("foo", "bar"));
        HashMap<String, String> mapValues = new HashMap<>();
        mapValues.put("fooKey", "fooVal");
        mapValues.put("barKey", "barVal");

        source.setMapValues(mapValues);
        NoSetterTarget target = converter.convert(source, NoSetterTarget.class);

        assertThat(target.getListValues()).containsExactly("foo", "bar");
        assertThat(target.getMapValues()).contains(entry("fooKey", "fooVal"), entry("barKey", "barVal"));

        // now test existing instances

        NoSetterSource source2 = new NoSetterSource();
        source2.setListValues(Arrays.asList("baz"));
        List<String> originalCollectionInstance = target.getListValues();
        Map<String, String> originalMapInstance = target.getMapValues();

        NoSetterTarget target2 = converter.convert(source2, target);

        assertThat(target2.getListValues()).isSameAs(originalCollectionInstance);
        assertThat(target2.getListValues()).containsExactly("baz");
        assertThat(target2.getMapValues()).isSameAs(originalMapInstance);
        // source2 mapvalues is empty, so the map is not cleared
        //assertThat( target2.getMapValues() ).contains( entry( "fooKey", "fooVal" ), entry( "barKey", "barVal" ) );

    }

}
