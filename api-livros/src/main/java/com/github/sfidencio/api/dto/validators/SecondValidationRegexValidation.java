package com.github.sfidencio.api.dto.validators;

import com.github.sfidencio.api.dto.validators.imp.SecondValidationRegexValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SecondValidationRegexValidator.class})
@Documented
public @interface SecondValidationRegexValidation {
    String message() default "Default validation error message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int order() default 2;
    String regex();
}
