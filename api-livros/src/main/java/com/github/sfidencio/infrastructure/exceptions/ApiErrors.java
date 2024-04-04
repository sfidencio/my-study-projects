package com.github.sfidencio.infrastructure.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class ApiErrors {
    private List<String> errors;
    public ApiErrors(BindingResult bindResult) {
        this.errors = new ArrayList<>(0);
        bindResult.getAllErrors()
                .forEach(error -> {
                    this.errors.add(error.getDefaultMessage());
                });
    }
    public ApiErrors(BusinessException ex) {
        this.errors = Collections.singletonList(ex.getMessage());
    }
}
