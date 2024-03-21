package com.github.sfidencio.exemplo.application;

import com.github.sfidencio.exemplo.application.dto.ProductRequest;
import com.github.sfidencio.exemplo.application.dto.ProductResponse;
import com.github.sfidencio.exemplo.domain.entities.Product;
import com.github.sfidencio.exemplo.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody ProductRequest productRequest) {
        var createdProduct = this.productService.create(this.modelMapper.map(productRequest, Product.class));
        return this.modelMapper.map(createdProduct, ProductResponse.class);
    }
}
