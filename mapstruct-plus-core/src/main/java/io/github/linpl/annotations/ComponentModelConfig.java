package io.github.linpl.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.MappingConstants;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ComponentModelConfig {

    String componentModel() default "default";

}
