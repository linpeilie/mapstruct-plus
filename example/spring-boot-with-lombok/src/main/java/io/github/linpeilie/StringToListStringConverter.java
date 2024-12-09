package io.github.linpeilie;

import io.github.linpeilie.model.GoodsStateEnum;
import java.util.Arrays;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StringToListStringConverter {

    default List<String> stringToListString(String str) {
        return Arrays.asList(str.split(","));
    }

    default String listStringToString(List<String> list) {
        return String.join(",", list);
    }

}
