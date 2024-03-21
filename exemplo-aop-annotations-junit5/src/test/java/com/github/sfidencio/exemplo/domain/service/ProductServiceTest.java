package com.github.sfidencio.exemplo.domain.service;

import com.github.sfidencio.exemplo.domain.entities.Product;
import com.github.sfidencio.exemplo.infraestructure.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        this.productService = new ProductService(productRepository);
    }

    @Test
    @DisplayName("Should create product")
    public void shouldCreateProduct() {
        Product product = new Product();
        product.setStock(new BigDecimal(200));
        when(productRepository.save(any())).thenReturn(product);

        Product createdProduct = productService.create(product);

        Assertions.assertThat(createdProduct).isNotNull();
        Assertions.assertThat(createdProduct.getStock()).isEqualTo(new BigDecimal(200));
    }

    @Test
    @DisplayName("Should not create product when stock less than zero")
    public void shouldNotCreateProductWhenStockLessThanZero() {
        Product product = new Product();
        product.setStock(new BigDecimal(-200));
        when(productRepository.save(any())).thenThrow(new IllegalArgumentException());

        Assertions.assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(IllegalArgumentException.class);
    }
}