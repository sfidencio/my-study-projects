package com.github.sfidencio.vendas;

import com.github.sfidencio.vendas.api.dto.ClienteRequestValidationForJdbcTemplate;
import com.github.sfidencio.vendas.domain.entity.Cliente;
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

    @Autowired
    private ClienteService clienteService;

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


            //JDBC Template
            //Inserir
            this.clienteService.salvarJdbcTemplate(new ClienteRequestValidationForJdbcTemplate(null, "Fulano", "12345678901","fulano@gmail.com"));
            this.clienteService.salvarJdbcTemplate(new ClienteRequestValidationForJdbcTemplate(null, "Beltrano", "12345678901","beltrano@gmail.com"));
            this.clienteService.salvarJdbcTemplate(new ClienteRequestValidationForJdbcTemplate(null, "Bacana", "12345678901","beltrano@gmail.com"));
            this.clienteService.salvarJdbcTemplate(new ClienteRequestValidationForJdbcTemplate(null, "Alvaro", "12345678901","beltrano@gmail.com"));
            this.clienteService.salvarJdbcTemplate(new ClienteRequestValidationForJdbcTemplate(null, "Alisson", "12345678901","beltrano@gmail.com"));
            //Listar
            this.clienteService.listarPorNome("B").forEach(System.out::println);
            //Alterar
            this.clienteService.alterar(new Cliente(1, "Fulanoo Alterado", "12345678901", "fulano@gmail.com", null));
            //Listar
            this.clienteService.listar().forEach(System.out::println);
            //Excluir
            this.clienteService.excluir(1);
            //Listar
            this.clienteService.listar().forEach(System.out::println);
        };
    }

}
