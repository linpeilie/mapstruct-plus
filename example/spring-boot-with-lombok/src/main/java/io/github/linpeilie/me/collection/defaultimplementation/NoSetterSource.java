/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.defaultimplementation;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import java.util.List;
import java.util.Map;

/**
 * @author Andreas Gudian
 *
 */
@AutoMappers({
    @AutoMapper(target = NoSetterTarget.class)
})
public class NoSetterSource {
    private List<String> listValues;
    private Map<String, String> mapValues;

    public List<String> getListValues() {
        return listValues;
    }

    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    public Map<String, String> getMapValues() {
        return mapValues;
    }

    public void setMapValues(Map<String, String> mapValues) {
        this.mapValues = mapValues;
    }
}
