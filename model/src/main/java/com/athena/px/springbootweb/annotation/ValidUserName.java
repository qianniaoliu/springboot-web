package com.athena.px.springbootweb.annotation;

import com.athena.px.springbootweb.validation.ValidUserNameConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/24 16:24
 */
@Target({  FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidUserNameConstraint.class})
public @interface ValidUserName {
    String message() default "{javax.validation.constraints.NotNull.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
