package com.github.sfidencio.vendas;

import com.github.sfidencio.vendas.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    //@PersistenceContext
    //private EntityManager entityManager;

    //@Autowired
    //private ClienteService clienteService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            //var cliente = new Cliente(null, "Fidencio", "12345678901");
            //this.entityManager.persist(cliente);

            //var lista = this.entityManager.createQuery("from Cliente", Cliente.class).getResultList();
            //System.out.println(lista);


            //final var clienteResponse = this.clienteService.buscarPorId(1);
            //System.out.println(clienteResponse);


            //var cliente = this.entityManager.find(Cliente.class, 1);
            //cliente.setId(1);
            //cliente.setNome("Fidencio Silva");
            //cliente.setCpf("123456789044444");
            //cliente = this.entityManager.merge(cliente);
            //System.out.println(cliente);
        };
    }

}
