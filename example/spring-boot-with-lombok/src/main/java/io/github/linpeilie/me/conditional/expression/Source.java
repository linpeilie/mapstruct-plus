package io.github.linpeilie.me.conditional.expression;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;

@AutoMapper(target = Target.class, reverseConvertGenerate = false)
public class Source {

    @AutoMapping(conditionExpression = "java(source.getValue() < 100)")
    private final int value;

    public Source(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
