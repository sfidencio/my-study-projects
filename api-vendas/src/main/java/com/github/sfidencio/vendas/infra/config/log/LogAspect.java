package com.github.sfidencio.vendas.infra.config.log;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
@EnableAspectJAutoProxy
//Essa anotacao e para o Spring Boot conseguir fazer a injecao de dependencia, e o ideal e que ela esteja na classe Application.java
public class LogAspect {

    @Before(value = "execution(* com.github.sfidencio.vendas.api.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Antes da execução do método {} ", joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.github.sfidencio.vendas.api.controller.*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("Depois da execução do método {} com o resultado {}", joinPoint.getSignature(), result);
    }


}
