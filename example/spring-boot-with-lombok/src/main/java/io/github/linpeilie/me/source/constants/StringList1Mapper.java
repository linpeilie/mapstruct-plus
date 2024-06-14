package io.github.linpeilie.me.source.constants;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StringList1Mapper {

    public List<String> stringToStringList(String string) {
        return string == null ? null : Arrays.asList( string.split( "-" ) );
    }
}
