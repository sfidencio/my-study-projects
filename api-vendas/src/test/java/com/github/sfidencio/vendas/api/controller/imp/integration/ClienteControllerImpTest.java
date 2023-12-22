package com.github.sfidencio.vendas.api.controller.imp.integration;


import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc
public class ClienteControllerImpTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;

    @Autowired
    private CacheManager cacheManager;


    private final String URL = "/v1/api/clientes";

    private ClienteResponse clienteResponse;
    private ClienteRequest clienteRequest;


    @BeforeEach
    void setUp() {
        this.clienteResponse = new ClienteResponse(1, "Joao Carlos", "79681821076", "fulano@gmail.com", null);
        this.clienteRequest = new ClienteRequest(null, "Joao Carlos", "79681821076", "fulano@gmail.com");
    }

    @Test
    @Order(1)
    void deveria_cadastrar_cliente_com_sucesso() throws Exception {
        this.mockMvc
                .perform(post(URL + "/salva")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Joao Carlos\",\"cpf\": \"79681821076\",\"email\":\"fulano@gmail.com\"}"))
                .andExpect(status().isCreated());

    }

    @Test
    @Order(2)
    void deveria_retornar_cliente_cadastrado_com_sucesso() throws Exception {
        when(this.clienteService.buscarClienteEPedidos(anyInt())).thenReturn(this.clienteResponse);
        this.mockMvc
                .perform(get(URL + "/consulta/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("fulano@gmail.com."));

    }
}
