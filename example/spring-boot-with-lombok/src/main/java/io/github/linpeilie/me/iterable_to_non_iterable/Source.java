/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.iterable_to_non_iterable;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.me.iterable_to_non_iterable.util.FirstElement;
import io.github.linpeilie.me.iterable_to_non_iterable.util.IterableNonIterableUtil;
import io.github.linpeilie.me.iterable_to_non_iterable.util.LastElement;
import java.util.List;
import lombok.Data;

@Data
@AutoMapper(target = Target.class, reverseConvertGenerate = false, uses = IterableNonIterableUtil.class, mapperNameSuffix = "$4")
public class Source {

    @AutoMapping(target = "myInteger", qualifiedBy = FirstElement.class)
    private List<Integer> myIntegers;

    @AutoMapping(target = "myString", qualifiedBy = LastElement.class)
    private List<String> myStrings;

}
