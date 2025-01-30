package com.github.sfidencio.controller;

import com.github.sfidencio.domain.model.Product;
import com.github.sfidencio.domain.service.IProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final IProdutoService produtoService;

    @PostMapping()
    public Mono<ResponseEntity<Product>> createProduct(@RequestBody Product product) {
        log.info("Creating product: {}", product);
        return this.produtoService.save(product)
                .map(savedProduct-> ResponseEntity.status(HttpStatus.CREATED).body(savedProduct))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

}
