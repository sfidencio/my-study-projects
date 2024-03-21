package com.github.sfidencio.exemplo.application.dto;

import java.math.BigDecimal;

public record ProdutoRequest(String description, BigDecimal price) {
}
