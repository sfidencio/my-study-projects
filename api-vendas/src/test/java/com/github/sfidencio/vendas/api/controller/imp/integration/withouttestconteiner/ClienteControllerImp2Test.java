package com.github.sfidencio.vendas.api.controller.imp.integration.withouttestconteiner;


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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test1")
//@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc
//@DataRedisTest
public class ClienteControllerImp2Test {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;

    private final String URL = "/v1/api/clientes";

    private ClienteResponse clienteResponse;
    private ClienteRequest clienteRequest;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedisOperations() {
        // Armazenar dados no Redis
        redisTemplate.opsForValue().set("key", "value");
        // Recuperar dados do Redis
        String retrievedValue = redisTemplate.opsForValue().get("key");
        assertEquals("value", retrievedValue);
    }


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
        //As chamadas ao redis estao sendo simuladas pelo embedded redis, que sobe um servidor redis em memoria para
        //fins de testes

        when(this.clienteService.buscarClienteEPedidos(anyInt())).thenReturn(this.clienteResponse);
        this.mockMvc
                .perform(get(URL + "/consulta/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("fulano@gmail.com"));

    }


}
