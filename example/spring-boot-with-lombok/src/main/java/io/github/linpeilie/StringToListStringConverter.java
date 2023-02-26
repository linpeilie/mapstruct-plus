package io.github.linpeilie;

import java.util.Arrays;
import java.util.List;

public class StringToListStringConverter {

    public static List<String> stringToListString(String str) {
        return Arrays.asList(str.split(","));
    }

}
