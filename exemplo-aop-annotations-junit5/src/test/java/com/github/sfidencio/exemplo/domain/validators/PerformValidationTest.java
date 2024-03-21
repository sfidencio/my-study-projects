package com.github.sfidencio.exemplo.domain.validators;

import com.github.sfidencio.exemplo.domain.entities.Product;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PerformValidationTest {


    private List<ValidProduct> validProducts;

    @Mock
    private ProceedingJoinPoint joinPoint;
    @Mock
    private Signature signature;


    @InjectMocks
    private PerformValidation performValidation;

    @BeforeEach
    public void setUp() {
        this.validProducts = mock(List.class);
        performValidation = new PerformValidation(validProducts);
    }

    @Test
    @DisplayName("Should perform validation when product is valid")
    public void shouldPerformValidationWhenProductIsValid() {
        Product product = new Product();
        when(joinPoint.getArgs()).thenReturn(new Object[]{product});
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("createProduct");
        performValidation.execute(joinPoint);
        verify(validProducts, times(1)).forEach(any());
    }

    @Test
    @DisplayName("Should throw exception when object is null")
    public void shouldThrowExceptionWhenObjectIsNull() {
        when(joinPoint.getArgs()).thenReturn(new Object[]{null});
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("createProduct");
        assertThrows(IllegalArgumentException.class, () -> performValidation.execute(joinPoint));
        verify(validProducts, never()).forEach(any());
    }

    @Test
    @DisplayName("Should perform validation when object is not a product")
    public void shouldPerformValidationWhenObjectIsNotProduct() {
        Object object = new Object();
        when(joinPoint.getArgs()).thenReturn(new Object[]{object});
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("createProduct");
        performValidation.execute(joinPoint);

        verify(validProducts, never()).forEach(any());
    }
}