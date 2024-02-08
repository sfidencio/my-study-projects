package br.com.sfidencio.minhaprimeiraapirestfull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class MinhaConfiguracao2 {

    @Value("${app.nome}")
    private String nomeApp;

    @Bean(name = "nomeApp2")
    public String getAppNome(){
        return nomeApp;
    }

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Executando configuracao - desenvolvimento.");
        };
    }
}
