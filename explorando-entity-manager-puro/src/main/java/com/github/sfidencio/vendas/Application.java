package com.github.sfidencio.vendas;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    //@PersistenceContext
    //private EntityManager entityManager;

    //@Autowired
    private final ClienteService clienteService;

    public Application(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

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


            this.clienteService.salvar(new ClienteRequest(null, "Fulano", "45515935080", "fulano@gmail.com"));
            this.clienteService.salvar(new ClienteRequest(null, "Beltrano", "45515935080", "fulano@gmail.com"));
            this.clienteService.salvar(new ClienteRequest(null, "Solano", "45515935080", "fulano@gmail.com"));
            this.clienteService.salvar(new ClienteRequest(null, "Gilson", "45515935080", "fulano@gmail.com"));
            this.clienteService.salvar(new ClienteRequest(null, "Wilson", "45515935080", "fulano@gmail.com"));
            var cliente = this.clienteService.buscarPorId(1);
            System.out.println(cliente);
            //var cliente2 = this.clienteService.buscarPorId(2);
            //System.out.println(cliente2);
            this.clienteService.alterar(new ClienteRequest(1, "Ciclano", "45515935080", "ciclano@gmail.com"));
            var cliente3 = this.clienteService.buscarPorId(1);
            System.out.println(cliente3);

            //Buscar por nome
            this.clienteService.buscarPorNome("son").forEach(System.out::println);
        };
    }

}
