package io.github.linpeilie.model;

import java.util.Collection;
import lombok.Data;

@Data
public class LoginUser {

    private Long userId;

    private String username;

    private Collection<? extends GrantedAuthority> authorities;
}
