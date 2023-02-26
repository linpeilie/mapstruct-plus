package io.github.linpeilie;

import java.util.Map;

public interface BaseMapMapper<T> {

    T convert(Map<String, Object> map);

    default T convertByObj(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return convert((Map<String, Object>) obj);
        }
        return null;
    }

}
