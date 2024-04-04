package com.github.sfidencio.api.dto.validators.imp;

import com.github.sfidencio.api.dto.validators.FirstEmptyValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FirstEmptyValidator implements ConstraintValidator<FirstEmptyValidation, String> {
    @Override
    public void initialize(FirstEmptyValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty();
    }
}
