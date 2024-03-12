package io.github.linpeilie;

import java.util.Map;

public interface BaseMapMapper<T> {

    T convert(Map<String, Object> map);

}
