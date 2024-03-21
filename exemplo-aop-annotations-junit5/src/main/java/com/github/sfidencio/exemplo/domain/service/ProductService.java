package com.github.sfidencio.exemplo.domain.service;

import com.github.sfidencio.exemplo.domain.entities.Product;
import com.github.sfidencio.exemplo.domain.validators.EnableBusinessValidation;
import com.github.sfidencio.exemplo.infraestructure.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    @EnableBusinessValidation
    public Product create(Product product) {
        return this.productRepository.save(product);
    }
}
