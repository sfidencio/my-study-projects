package com.github.sfidencio.domain.service;

import com.github.sfidencio.domain.model.Product;
import reactor.core.publisher.Mono;

public interface IProdutoService {
    Mono<Product> save(Product product);
}
