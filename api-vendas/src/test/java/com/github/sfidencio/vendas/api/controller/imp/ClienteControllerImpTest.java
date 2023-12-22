package com.github.sfidencio.vendas.api.controller.imp;

import com.github.sfidencio.vendas.domain.service.ClienteService;
import com.github.sfidencio.vendas.infra.repository.relational.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClienteControllerImp.class)
public class ClienteControllerImpTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private ClienteRepository clienteRepository;
    private final String URL = "base/v1/api/clientes";

    @Test
    void deveria_cadastrar_cliente_com_sucesso() throws Exception {
        //ClienteRequest clienteRequest = new ClienteRequest(null,"Joao Carlos.", "79681821076", "fulano@gmail.com");
        //when(this.clienteService.salvar(clienteRequest)).thenReturn(null);
        this.mockMvc
                .perform(post(URL + "/salvar"))
                .andExpect(status().isCreated());
    }
}