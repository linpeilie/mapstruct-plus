/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection.adder;

/**
 * @author Sjaak Derksen
 */
public class CatException extends Exception {

    private static final long serialVersionUID = 1L;

    public CatException() {
    }

    public CatException(String msg) {
        super( msg );
    }
}
