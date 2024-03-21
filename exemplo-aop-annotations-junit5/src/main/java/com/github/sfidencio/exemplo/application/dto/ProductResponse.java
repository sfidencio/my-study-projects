package com.github.sfidencio.exemplo.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductResponse(@NotEmpty(message = "Description is mandatory") String description,
                              @DecimalMax(value = "9.99", message = "Price must be less than or equal to 1.99")
                             @DecimalMin(value = "1.99", message = "Price must be greater than or equal to 3.99")
                             @NotNull(message = "Price is mandatory") BigDecimal price) {
}
