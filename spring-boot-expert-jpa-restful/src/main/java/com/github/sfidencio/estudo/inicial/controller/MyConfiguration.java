package com.github.sfidencio.estudo.inicial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    private String helloWorld = "Hello World in JAVA and SpringBoot!";

    @Bean(name = "helloWorld")
    public String getHelloWorld() {
        return helloWorld;
    }

    @Bean(name = "jsonProduto")
    public String getJsonProduto() {
        return "{\"id\": 1, \"nome\": \"Produto 1\"}";
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean(name = "applicationName")
    public String getApplicationName() {
        return applicationName;
    }
}
