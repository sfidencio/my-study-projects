package com.github.sfidencio.estudo.inicial.configuration.example1.test.v1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class TestProfileProduction {
    @Bean("executeProductionV1")
    public CommandLineRunner executeV1() {
        return args -> {
            System.out.println("Rodando em ambiente de producao");
        };
    }
}
