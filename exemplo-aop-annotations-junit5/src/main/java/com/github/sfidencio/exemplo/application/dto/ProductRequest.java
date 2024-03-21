package com.github.sfidencio.exemplo.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotEmpty(message = "Description is mandatory")
    private String description;

    @DecimalMin(value = "3.99", message = "Price must be less than or equal to 3.99")
    @DecimalMax(value = "9.99", message = "Price must be greater than or equal to 9.99")
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;
    private BigDecimal stock;
}
