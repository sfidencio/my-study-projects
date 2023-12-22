package com.github.sfidencio.vendas.domain.service.imp.integration;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

//So com essa anotação funcionou 100%, carregando os recursos do springboot
@SpringBootTest

//Essas configuracoes funcionaria se nao tivessemos mongodb, redis e outrs
//@DataJpaTest
//@TestPropertySource(locations = "classpath:application-test.yaml")

//Essa anotação é para nao carregar o banco em memoria, mas sim o banco de dados real
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@Rollback(false)
@ActiveProfiles("test")
class ClienteServiceImpTest {

    //@Autowired
    //private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;


    @Test
    @Order(0)
    void deveria_cadastrar_cliente_com_sucesso() throws NotFoundException {
        this.clienteService.salvar(new ClienteRequest(null, "Joao Carlos.", "79681821076", "fulano@gmail.com"));
    }


    @Test
    @Order(1)
    void deveria_alterar_cliente_cadastrado() throws NotFoundException {
        /*var cliente = this.clienteRepository.findById(1);
        cliente.ifPresent(c -> {
            c.setNome("Joao Carlos.");
            this.clienteRepository.save(c);
        });
        Assertions.assertEquals("Joao Carlos.", cliente.get().getNome());*/
        this.clienteService.alterar(new ClienteRequest(1, "Joao Pedro", "79681821076", "fulano@gmail.com"), 1);
        var cliente = this.clienteService.buscarClienteEPedidos(1);
        Assertions.assertEquals("Joao Pedro", cliente.nome());
    }


}