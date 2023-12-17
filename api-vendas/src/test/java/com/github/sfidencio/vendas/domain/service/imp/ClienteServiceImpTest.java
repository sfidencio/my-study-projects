package com.github.sfidencio.vendas.domain.service.imp;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.ClienteRepository;
import com.github.sfidencio.vendas.infra.repository.ClienteVIPRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
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
    void testFindCliente() throws NotFoundException {
        when(this.clienteRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(new Cliente(1, "Fulano", "12345678901", "fulano@gmail.com", null)));
        var resultado = this.clienteService.buscarClienteEPedidos(1);
        Assertions.assertEquals(1, resultado.id());
    }


    /*@Test
    void salvar() throws NotFoundException {
        when(this.clienteRepository.save(any(Cliente.class))).thenReturn(new Cliente(1, "Fulano", "12345678901", "fulano@gmail.com", null));
        this.clienteService.salvar(new ClienteRequest(1, "Fulano", "12345678901", "fulano@gmail.com"));
    }*/
}