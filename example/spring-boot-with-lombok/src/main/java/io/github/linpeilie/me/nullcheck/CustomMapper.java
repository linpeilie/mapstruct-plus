/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullcheck;

import java.math.BigInteger;
import org.springframework.stereotype.Component;

@Component
public class CustomMapper {

    public MyBigIntWrapper toMyBigIntWrapper(BigInteger bigInteger) {
        MyBigIntWrapper wrapper = new MyBigIntWrapper();
        wrapper.setMyBigInt( bigInteger );
        return wrapper;
    }

    public MyLongWrapper toMyBigIntWrapperViaPrimitive(long primitive) {
        MyLongWrapper wrapper = new MyLongWrapper();
        wrapper.setMyLong( primitive );
        return wrapper;
    }
}
