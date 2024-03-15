package io.github.linpeilie.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayUtil {

    public static <T> boolean isNotEmpty(T[] arr) {
        return arr != null && arr.length > 0;
    }

    public static <T> String join(T[] arr, CharSequence delimiter) {
        return join(arr, delimiter, "", "");
    }

    public static <T> String join(T[] arr, CharSequence delimiter, String prefix, String suffix) {
        if (arr == null) {
            return null;
        }
        return Arrays.stream(arr).map(str -> prefix + str + suffix).collect(Collectors.joining(delimiter));
    }

}
