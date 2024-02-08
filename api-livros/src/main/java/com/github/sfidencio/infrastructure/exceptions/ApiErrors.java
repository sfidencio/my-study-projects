package com.github.sfidencio.infrastructure.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

import lombok.Getter;

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
        this.errors = Arrays.asList(ex.getMessage());
    }
}
