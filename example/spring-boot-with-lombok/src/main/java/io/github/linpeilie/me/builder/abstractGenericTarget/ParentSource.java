/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.builder.abstractGenericTarget;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;

@AutoMappers({
    @AutoMapper(target = ImmutableParent.class),
    @AutoMapper(target = MutableParent.class)
})
public class ParentSource {
    private int count;
    private ChildSource child;
    private ChildSource nonGenericChild;

    public ChildSource getNonGenericChild() {
        return nonGenericChild;
    }

    public void setNonGenericChild(ChildSource nonGenericChild) {
        this.nonGenericChild = nonGenericChild;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ChildSource getChild() {
        return child;
    }

    public void setChild(ChildSource child) {
        this.child = child;
    }
}
