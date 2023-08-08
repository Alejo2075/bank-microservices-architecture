package com.julieta.auth_service.validations;

import com.julieta.auth_service.annotations.ValidId;
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