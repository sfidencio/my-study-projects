package com.github.sfidencio.vendas.domain.service.imp.integration;

import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.infra.repository.relational.ClienteRepository;
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

    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    @Order(1)
    void deveria_cadastrar_cliente_com_sucesso() {
        this.clienteRepository.save(new Cliente(null, "Joao Carlos.", "79681821076", "fulano@gmail.com", null));
    }

    @Test
    @Order(2)
    void deveria_obter_cliente_cadastro_com_sucesso() {
        var cliente = this.clienteRepository.findById(1);
        Assertions.assertEquals("Joao Carlos.", cliente.get().getNome());
    }

    @Test
    @Order(3)
    void deveria_alterar_cliente_cadastrado() {
        var cliente = this.clienteRepository.findById(1);
        cliente.ifPresent(c -> {
            c.setNome("Joao Carlos.");
            this.clienteRepository.save(c);
        });
        Assertions.assertEquals("Joao Carlos.", cliente.get().getNome());
    }


}