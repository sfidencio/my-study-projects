package com.github.sfidencio.exemplo.domain.validators;

import com.github.sfidencio.exemplo.domain.entities.Product;

public interface ValidProduct {
    void execute(Product product);
}
