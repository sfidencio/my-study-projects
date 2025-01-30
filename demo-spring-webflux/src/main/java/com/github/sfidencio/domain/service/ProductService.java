package com.github.sfidencio.domain.service;

import com.github.sfidencio.domain.model.Product;
import com.github.sfidencio.domain.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProdutoService {
    private final IProductRepository productRepository;


    @Override
    public Mono<Product> save(Product product) {
        return this.productRepository.save(product);
    }
}
