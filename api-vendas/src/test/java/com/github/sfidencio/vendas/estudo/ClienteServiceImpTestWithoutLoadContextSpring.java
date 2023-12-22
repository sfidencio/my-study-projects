package com.github.sfidencio.vendas.estudo;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.domain.entity.Cliente;
import com.github.sfidencio.vendas.domain.service.imp.ClienteServiceImp;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.relational.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImpTestWithoutLoadContextSpring {
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteServiceImp clienteServiceImp;

    @BeforeEach
    void setUp() {
    }

    @Test
    void deveria_salvar_cliente() throws NotFoundException {
        when(this.clienteRepository.save(any(Cliente.class))).thenReturn(new Cliente(1, "Fulano", "12345678901", "fulano@gmail.com", null));
        this.clienteServiceImp.salvar(new ClienteRequest(1, "Fulano", "12345678901", "fulano@gmail.com"));
        //verify(this.clienteRepository, times(1)).save(any(Cliente.class));
        //assertEquals(1, retorno.getId());
    }

}
