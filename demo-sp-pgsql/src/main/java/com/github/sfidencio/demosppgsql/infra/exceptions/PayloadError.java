package com.github.sfidencio.demosppgsql.infra.exceptions;

import java.time.LocalDateTime;

public record PayloadError(String message, String statusCode, LocalDateTime timestamp) {

}
