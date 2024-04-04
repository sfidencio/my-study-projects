package com.github.sfidencio.api.dto.validators.imp;

import com.github.sfidencio.api.dto.validators.SecondValidationRegexValidation;
import jakarta.validation.ConstraintValidator;

public class SecondValidationRegexValidator implements ConstraintValidator<SecondValidationRegexValidation, String> {
    private SecondValidationRegexValidation secondValidationRegexValidation;

    @Override
    public void initialize(SecondValidationRegexValidation constraintAnnotation) {
        this.secondValidationRegexValidation = constraintAnnotation;
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(this.secondValidationRegexValidation.regex());
    }
}
