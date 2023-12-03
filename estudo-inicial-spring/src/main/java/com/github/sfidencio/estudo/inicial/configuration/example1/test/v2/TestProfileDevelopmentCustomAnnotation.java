package com.github.sfidencio.estudo.inicial.configuration.example1.test.v2;

import com.github.sfidencio.estudo.inicial.configuration.example1.custom.MyConfigurationForDevelopment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@MyConfigurationForDevelopment
public class TestProfileDevelopmentCustomAnnotation {
    @Bean("executeDevelopmentV2")
    public CommandLineRunner execute() {
        return args -> {
            System.out.println("Rodando em ambiente de desenvolvimento com anotacao customizada");
        };
    }
}
