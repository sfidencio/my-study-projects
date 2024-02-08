package br.com.sfidencio.minhaprimeiraapirestfull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@Profile("production")
public class MinhaConfiguracao {

    @Value("${app.nome}")
    private String nomeApp;

    @Bean(name = "nomeApp1")
    public String getAppNome(){
        return nomeApp;
    }

}
