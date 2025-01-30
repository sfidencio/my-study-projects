package com.gihub.sfidencio.mybusiness.domain.service;

import com.gihub.sfidencio.mybusiness.domain.model.Product;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class ProductServiceTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Given product with price zero when save then throw illegal argument exception")
    void givenProductWithPriceZeroWhenSaveThenThrowIllegalArgumentException() {
        // Given
        var product = new Product(1L, "Product 1", BigDecimal.ZERO, BigDecimal.ONE);

        var productService = new ProductService();

        // When
        Assertions.assertThrows(IllegalArgumentException.class, () -> productService.save(product));
    }

    @Test
    @DisplayName("Given product with price null when save then throw illegal argument exception")
    void givenProductWithPriceNullWhenSaveThenThrowIllegalArgumentException() {
        // Given
        var product = new Product(1L, "Product 1", null, BigDecimal.ONE);

        var productService = new ProductService();

        // When
        Assertions.assertThrows(IllegalArgumentException.class, () -> productService.save(product));
    }

    @Test
    @DisplayName("Given product with price greater than zero when save then print product saved")
    void givenProductWithPriceGreaterThanZeroWhenSaveThenPrintProductSaved() {
        // Given
        var product = new Product(1L, "Product 1", BigDecimal.ONE, BigDecimal.ONE);

        var productService = new ProductService();

        // When
        productService.save(product);
    }

}