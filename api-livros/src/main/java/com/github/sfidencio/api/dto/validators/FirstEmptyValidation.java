package com.github.sfidencio.api.dto.validators;

import com.github.sfidencio.api.dto.validators.imp.FirstEmptyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FirstEmptyValidator.class})
@Documented
public @interface FirstEmptyValidation {
    String message() default "Default validation error message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int order() default 1;
}
