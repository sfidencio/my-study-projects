package com.gihub.sfidencio.mybusiness.domain.service;

import com.gihub.sfidencio.mybusiness.domain.model.Product;


import java.math.BigDecimal;


public class ProductService implements IProductService {
    @Override
    public void save(Product product) {
//        Assertions.assertTrue(product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) > 0,
//                "Price is required and must be greater than zero");
//

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price is required and must be greater than zero");
        }


        System.out.println("Product saved: " + product);
    }
}