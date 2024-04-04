package com.github.sfidencio.application.dto;

import java.math.BigDecimal;

public record CustomerResponse(int id, String name, String email, BigDecimal creditLimit) {
}
