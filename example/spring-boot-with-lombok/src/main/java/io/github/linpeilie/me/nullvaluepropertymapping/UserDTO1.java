/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nullvaluepropertymapping;

import java.util.List;

public class UserDTO1 {

    private HomeDTO1 homeDTO;
    private List<String> details;

    public HomeDTO1 getHomeDTO() {
        return homeDTO;
    }

    public void setHomeDTO(HomeDTO1 homeDTO) {
        this.homeDTO = homeDTO;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
