package com.github.sfidencio.vendas.infra.config;

import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class ExceptionHandlerCustom {
    //https://blog.devgenius.io/how-to-handle-constraint-violation-exception-using-controlleradvice-in-spring-boot-2f61147d19de
    //https://reflectoring.io/bean-validation-with-spring-boot/
    //https://www.javadevjournal.com/spring-boot/spring-custom-validation-message-source/
    //https://www.baeldung.com/spring-valid-vs-validated
    //https://salithachathuranga94.medium.com/validation-and-exception-handling-in-spring-boot-51597b580ffd

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> handleExceptionGeneral(RuntimeException e) {
        return new HashMap<>(Map.of("error", e.getMessage()));
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> handleExceptionNotFound(NotFoundException e) {
        return new HashMap<>(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, List<String>> handleExceptionConstraintViolation(ConstraintViolationException e) {
        /*return e.getConstraintViolations().stream()
                .map(constraintViolation -> new ErrorResponseDefault(constraintViolation.getMessage() + " -> " + constraintViolation.getInvalidValue()))
                .toList();*/
    List<String> errors = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.toList());

        return getErrorsMap(errors);
    }


    private Map<String, List<String>> getErrorsMap(List<String> e) {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("errors", e);
        return errors;
    }


    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErrorResponseDefault> handleExceptionMethodArgumentNotValid(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> new ErrorResponseDefault(fieldError.getDefaultMessage()))
                .toList();

    }*/
}
