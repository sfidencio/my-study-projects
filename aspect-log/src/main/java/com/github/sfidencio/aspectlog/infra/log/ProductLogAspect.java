package com.github.sfidencio.aspectlog.infra.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class ProductLogAspect {

    @Before("execution(* com.github.sfidencio.aspectlog.application.controller.ProductController.getProducts(..))")
    public void beforeGetProducts(JoinPoint joinPoint) {
        log.info("Metodo {}: Parametro de entrada: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }


    @AfterReturning(pointcut = "execution(* com.github.sfidencio.aspectlog.application.controller.ProductController.getProducts(..))", returning = "result")
    public void afterGetProducts(JoinPoint joinPoint, Object result) {
        log.info("Metodo {}: Retorno: {}", joinPoint.getSignature().getName(), result);
    }
}
