package com.github.sfidencio.exemplo.domain.validators.imp;

import com.github.sfidencio.exemplo.domain.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckIfStockInsufficientTest {

    private CheckIfStockInsufficient checkIfStockInsufficient;

    @BeforeEach
    public void setUp() {
        checkIfStockInsufficient = new CheckIfStockInsufficient();
    }

    @Test
    @DisplayName("Should throw exception when product stock is negative")
    public void shouldThrowExceptionWhenProductStockIsNegative() {
        Product product = new Product();
        product.setStock(new BigDecimal(-200));

        assertThrows(IllegalArgumentException.class, () -> checkIfStockInsufficient.execute(product));
    }

    @Test
    @DisplayName("Should throw exception when product stock is zero")
    public void shouldThrowExceptionWhenProductStockIsZero() {
        Product product = new Product();
        product.setStock(BigDecimal.ZERO);

        assertThrows(IllegalArgumentException.class, () -> checkIfStockInsufficient.execute(product));
    }

    @Test
    @DisplayName("Should not throw exception when product stock is positive")
    public void shouldNotThrowExceptionWhenProductStockIsPositive() {
        Product product = new Product();
        product.setStock(new BigDecimal(200));

        checkIfStockInsufficient.execute(product);
    }
}