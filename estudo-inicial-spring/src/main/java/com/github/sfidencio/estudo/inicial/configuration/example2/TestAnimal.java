package com.github.sfidencio.estudo.inicial.configuration.example2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAnimal {

    @Autowired
    //@Cachorro
    @Cachorro
    private Animal animal;

    @Bean("executeAnimal")
    public CommandLineRunner execute() {
        return args -> {
            this.animal.fazerBarulho();
        };
    }
}
