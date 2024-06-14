/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.adder._target;

import io.github.linpeilie.me.collection.adder.source.Foo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sjaak Derksen
 */
public class Target2 {

    private List<Foo> attributes = new ArrayList<Foo>();

    public Foo addAttribute( Foo foo ) {
        attributes.add( foo );
        return foo;
    }

    public List<Foo> getAttributes() {
        return attributes;
    }

    public void setAttributes( List<Foo> attributes ) {
        this.attributes = attributes;
    }

}
