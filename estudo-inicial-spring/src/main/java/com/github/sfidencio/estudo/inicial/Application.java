package com.github.sfidencio.estudo.inicial;

//Apartir do pacote com.github.sfidencio.estudo.inicial, o SpringBoot irá procurar por classes anotadas com @SpringBootApplication
//e irá procurar por classes anotadas com @Controller, @Service, @Repository, @Component, @Configuration, @Bean
//Caso tenhamos outros pacotes diferentes do pacote base acima, teriamos que anotar a classe Application com @ComponentScan(basePackages = {"pacote1", "pacote2"})

import com.github.sfidencio.estudo.inicial.configuration.exemple3.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.github.sfidencio.estudo.inicial.controller", "com.github.sfidencio.outropacote.nadahaver"})
public class Application {

    @Autowired
    private ProdutoService produtoService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean("executeProduto")
    public CommandLineRunner execute() {
        return args -> {
            this.produtoService.salvar();
        };
    }

}
