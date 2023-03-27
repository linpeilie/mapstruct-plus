package io.github.linpeilie;

import io.github.linpeilie.model.GoodsStateEnum;
import java.util.Arrays;
import java.util.List;

public class StringToListStringConverter {

    public static List<String> stringToListString(String str) {
        return Arrays.asList(str.split(","));
    }

    public static String listStringToString(List<String> list) {
        return String.join(",", list);
    }

}
