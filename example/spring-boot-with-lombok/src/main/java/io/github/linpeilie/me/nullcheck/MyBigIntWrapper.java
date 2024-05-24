/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullcheck;

import java.math.BigInteger;

/**
 * @author Sjaak Derksen
 */
public class MyBigIntWrapper {

    private BigInteger myBigInt;

    public BigInteger getMyBigInt() {
        return myBigInt;
    }

    public void setMyBigInt(BigInteger myBigInt) {
        this.myBigInt = myBigInt;
    }
}
