/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.adder;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author Sjaak Derksen
 */
@Component
public class TeethMapper {

    private static final Map<String, Integer> TEETH = ImmutableMap.<String, Integer>builder()
        .put( "incisor", 1 )
        .put( "canine", 2 )
        .put( "moler", 3 ).build();

    public Integer toTooth(String tooth) {
        return TEETH.get( tooth );
    }
}
