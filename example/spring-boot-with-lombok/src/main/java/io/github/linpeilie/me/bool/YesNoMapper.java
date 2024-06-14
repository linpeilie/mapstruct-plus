/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.bool;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Andreas Gudian
 *
 */
@Component
public class YesNoMapper {
    public String toString(YesNo yesNo) {
        if ( null != yesNo && yesNo.isYes() ) {
            return "yes";
        }

        return "no";
    }

    public boolean toBool(YesNo yesNo) {
        return ( null != yesNo && yesNo.isYes() );
    }
}
