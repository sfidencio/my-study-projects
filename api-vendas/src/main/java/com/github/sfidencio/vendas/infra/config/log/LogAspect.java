package com.github.sfidencio.vendas.infra.config.log;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogAspect {

    @Before("execution(* com.github.sfidencio.vendas.api.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint, Object result) {
        log.info("Antes da execução do método {} com o resultado {}", joinPoint.getSignature(), result);
    }

    @AfterReturning(value = "execution(* com.github.sfidencio.vendas.api.controller.*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("Depois da execução do método {} com o resultado {}", joinPoint.getSignature(), result);
    }


}
