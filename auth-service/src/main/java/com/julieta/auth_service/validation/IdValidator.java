package com.julieta.auth_service.validation;

import com.julieta.auth_service.annotation.ValidId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdValidator implements ConstraintValidator<ValidId, String> {
    @Override
    public void initialize(ValidId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        return id.matches("^\\d{10,11}$");
    }
}