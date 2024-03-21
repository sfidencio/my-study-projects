package com.github.sfidencio.exemplo.application.exceptions.dto;

public record ErrorMessageResponse(String field, Object valueRejected, String message) {
}
