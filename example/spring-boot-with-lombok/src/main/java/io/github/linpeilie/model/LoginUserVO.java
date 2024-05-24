package io.github.linpeilie.model;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.Collection;
import lombok.Data;
import org.mapstruct.CollectionMappingStrategy;

@Data
@AutoMapper(target = LoginUser.class, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public class LoginUserVO {

    private Long userId;

    private String username;

    private Collection<? extends GrantedAuthority> authorities;

}
