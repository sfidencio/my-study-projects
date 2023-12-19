package com.github.sfidencio.vendas.domain.service.imp.mock;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import com.github.sfidencio.vendas.domain.service.imp.ClienteServiceImp;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.ClienteRepository;
import com.github.sfidencio.vendas.infra.repository.ClienteVIPRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServiceImpTest {

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ClienteVIPRespository clienteVIPRespository;

    @InjectMocks
    @Autowired
    private ClienteServiceImp clienteService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Order(1)
    void deveria_cadastrar_cliente_com_sucesso() throws NotFoundException {
        var cliente = new Cliente(1, "Fulano", "79681821076", "fulano@gmail.com",null);
        var clienteRequest = new ClienteRequest(1, "Fulano.", "79681821076", "fulano@gmail.com");
        when(this.clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        clienteService.salvar(clienteRequest);
        //verify(this.clienteRepository).save(any(Cliente.class));
    }

    @Test
    @Order(2)
    void deveria_retornar_cliente_cadastrado() throws NotFoundException {
        when(this.clienteRepository.findById(any(Integer.class))).thenReturn(Optional.of(new Cliente(1, "Fulano", "12345678901", "fulano@gmail.com", null)));
        var resultado = this.clienteService.buscarClienteEPedidos(any());
        Assertions.assertEquals(1, resultado.id());
    }


    /*@Test
    void salvar() throws NotFoundException {
        when(this.clienteRepository.save(any(Cliente.class))).thenReturn(new Cliente(1, "Fulano", "12345678901", "fulano@gmail.com", null));
        this.clienteService.salvar(new ClienteRequest(1, "Fulano", "12345678901", "fulano@gmail.com"));
    }*/
}