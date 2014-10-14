package nz.co.xingsoft.gwt.sample.shared.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// @Target({ ElementType.METHOD, ElementType.PARAMETER })
@Target({ ElementType.PARAMETER })
public @interface ValidatedParam {
    Class<?>[] groups() default {};
}
