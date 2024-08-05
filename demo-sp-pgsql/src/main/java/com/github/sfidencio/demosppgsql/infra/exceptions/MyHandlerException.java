package com.github.sfidencio.demosppgsql.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyHandlerException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new PayloadError(e.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
