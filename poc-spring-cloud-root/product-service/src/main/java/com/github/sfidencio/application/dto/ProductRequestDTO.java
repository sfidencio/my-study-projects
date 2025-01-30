package com.github.sfidencio.application.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String description, BigDecimal price, BigDecimal currentStock) {
}
