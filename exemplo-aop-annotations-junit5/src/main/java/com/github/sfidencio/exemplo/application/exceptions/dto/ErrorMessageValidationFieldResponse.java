package com.github.sfidencio.exemplo.application.exceptions.dto;

public record ErrorMessageValidationFieldResponse(String field, Object valueRejected, String message) {
}
