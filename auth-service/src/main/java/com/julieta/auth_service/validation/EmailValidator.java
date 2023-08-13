package com.julieta.auth_service.validation;

import com.julieta.auth_service.annotation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
            }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {return id.matches("^[A-Za-z0-9+_.-]+@(.+)$");}
}