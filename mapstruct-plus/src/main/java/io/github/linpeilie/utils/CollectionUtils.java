package io.github.linpeilie.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static <T> List<T> newArrayList(T... values) {
        if (values == null || values.length == 0) {
            return new ArrayList<>();
        }
        List<T> arrayList = new ArrayList<>(values.length);
        Collections.addAll(arrayList, values);
        return arrayList;
    }

    public static <T> String join(List<T> list, CharSequence delimiter, String prefix, String suffix) {
        if (list == null) {
            return null;
        }
        return list.stream().map(str -> prefix + str + suffix).collect(Collectors.joining(delimiter));
    }

}
