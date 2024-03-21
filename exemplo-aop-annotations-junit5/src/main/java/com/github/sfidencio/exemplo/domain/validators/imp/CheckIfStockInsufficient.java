package com.github.sfidencio.exemplo.domain.validators.imp;

import com.github.sfidencio.exemplo.domain.entities.Product;
import com.github.sfidencio.exemplo.domain.validators.ValidProduct;

public class CheckIfStockInsufficient implements ValidProduct {
    @Override
    public void execute(Product product) {
        if (product.getStock().intValue() <=0)
            throw new IllegalArgumentException("Stock cannot be negative");
    }
}
