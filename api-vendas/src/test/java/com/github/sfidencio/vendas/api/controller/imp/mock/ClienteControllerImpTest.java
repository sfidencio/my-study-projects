package com.github.sfidencio.vendas.api.controller.imp.mock;

import com.github.sfidencio.vendas.api.controller.ClienteController;
import com.github.sfidencio.vendas.api.controller.imp.ClienteControllerImp;
import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ClienteControllerImpTest {
    @Mock
    private ClienteService service;
    @InjectMocks
    private ClienteController controller = new ClienteControllerImp();

    @Test
    void deveria_cadastrar_cliente_com_sucesso() throws Exception {
        ClienteRequest clienteRequest = new ClienteRequest(null, "Joao Carlos.", "79681821076", "fulano@gmail.com");
        //when(this.service.salvar(clienteRequest)).
        this.controller.salvar(clienteRequest);
        verify(this.service, times(1)).salvar(clienteRequest);
    }

    @Test
    void deveria_retornar_cliente_com_sucesso() throws Exception {
        ClienteResponse clienteResponse = new ClienteResponse(1, "Joao Carlos.", "79681821076", "fulano@gmail.com", null);
        when(this.service.buscarClienteEPedidos(1)).thenReturn(clienteResponse);
        var retorno = this.controller.consultar(1);
        //ResponseEntity<ClienteResponse> response = ..
        //Poderia retornar um ResponseEntity ao inves do objeto ClienteResponse
        verify(this.service, times(1)).buscarClienteEPedidos(1);
        Assertions.assertEquals(1, retorno.id());
    }

}