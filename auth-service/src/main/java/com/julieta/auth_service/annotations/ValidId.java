package com.julieta.auth_service.annotations;

import com.julieta.auth_service.validations.IdValidator;
import com.julieta.auth_service.validations.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = IdValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidId {
    String message() default "Invalid Identification Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
