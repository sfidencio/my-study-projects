package com.github.sfidencio.exemplo.domain.service;

import com.github.sfidencio.exemplo.domain.entities.Product;
import com.github.sfidencio.exemplo.domain.validators.EnableBusinessValidation;
import com.github.sfidencio.exemplo.infraestructure.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.openMocks(this);
        //this.productService = new ProductService(productRepository, Collections.singletonList(validProduct));
        this.productService = new ProductService(productRepository);
    }

    @Test
    @DisplayName("Should not create product when stock less than zero")
    @EnableBusinessValidation
    public void shouldNotCreateProductWhenStockLessThanZero() {
        Product product = new Product();
        product.setStock(new BigDecimal(-200));
        //doThrow(new IllegalArgumentException()).when(validProduct).execute(any(Product.class));
        when(productRepository.save(any())).thenThrow(new IllegalArgumentException());

        Assertions.assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(IllegalArgumentException.class);

        //verify(validProduct, times(1)).execute(any(Product.class));
        //verify(productRepository, never()).save(any(Product.class));
    }
}