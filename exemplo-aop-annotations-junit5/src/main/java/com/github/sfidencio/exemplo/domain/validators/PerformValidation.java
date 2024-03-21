package com.github.sfidencio.exemplo.domain.validators;


import com.github.sfidencio.exemplo.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
@RequiredArgsConstructor
public class PerformValidationAOP {
    @Qualifier("validProducts")
    private final List<ValidProduct> validProducts;

    @Before("@annotation(EnableBusinessValidation)")
    public void performValidation(JoinPoint joinPoint) {
        Object object = joinPoint.getArgs()[0];
        if (object == null)
            throw new IllegalArgumentException("Object cannot be null");
        if (object instanceof Product)
            this.validProducts.forEach(v -> v.execute((Product) object));
    }

}
