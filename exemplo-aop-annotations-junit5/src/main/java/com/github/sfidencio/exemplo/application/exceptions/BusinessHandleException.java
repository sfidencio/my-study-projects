package com.github.sfidencio.exemplo.application.exceptions;

import com.github.sfidencio.exemplo.application.exceptions.dto.ErrorMessageResponse;
import com.github.sfidencio.exemplo.application.exceptions.dto.ErrorMessageValidationFieldResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class BusinessHandleException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Object handle(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, List<ErrorMessageValidationFieldResponse>>();
        var errorMessages = new ArrayList<ErrorMessageValidationFieldResponse>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessages.add(new ErrorMessageValidationFieldResponse(error.getField(), error.getRejectedValue(), error.getDefaultMessage()));
        });
        errors.put("errors", errorMessages);
        return errors;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handle(Exception exception) {
        return new ErrorMessageResponse(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(IllegalArgumentException exception) {
        return new ErrorMessageResponse(exception.getMessage());
    }

}
