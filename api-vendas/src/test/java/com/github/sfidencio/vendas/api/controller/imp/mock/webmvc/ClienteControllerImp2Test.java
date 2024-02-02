package com.github.sfidencio.vendas.api.controller.imp.mock.webmvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import com.github.sfidencio.vendas.domain.service.ClienteVIPService;
import com.github.sfidencio.vendas.infra.repository.mongo.ClienteVIPRespository;
import com.github.sfidencio.vendas.infra.repository.relational.ClienteRepository;
import com.github.sfidencio.vendas.infra.repository.relational.PedidoRepository;
import com.github.sfidencio.vendas.infra.repository.relational.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//--> Somente utilizado nos teste de integração, so esta usando aqui pq estamos usando o redis e nao o mockamos
@ActiveProfiles("test1")
//@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
public class ClienteControllerImp2Test {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private ClienteVIPService clienteVIPService;
    @MockBean
    private ClienteVIPRespository clienteVIPRespository;
    @MockBean
    private ProdutoRepository produtoRepository;
    @MockBean
    private PedidoRepository pedidoRepository;
    @MockBean
    private ClienteRepository clienteRepository;

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
        //assertEquals("value", retrievedValue);
    }


    @BeforeEach
    void setUp() {

        this.clienteResponse = new ClienteResponse(1, "Joao Carlos", "79681821076", "fulano@gmail.com", null);
        this.clienteRequest = new ClienteRequest(null, "Joao Carlos", "79681821076", "fulano@gmail.com");
    }


    @Test
    @Order(1)
    @DisplayName("Deveria cadastrar cliente com sucesso")
    void deveria_cadastrar_cliente_com_sucesso() throws Exception {

        //Mockito.when(this.clienteService.salvar(this.clienteRequest)).thenReturn(this.clienteResponse);

        String json = new ObjectMapper().writeValueAsString(this.clienteRequest);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URL + "/salva")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc
                .perform(request)
                .andExpect(status().isCreated());

        //.andExpect(jsonPath("$.id").value(1))
        //.andExpect(jsonPath("$.nome").value("Joao Carlos"));

    }

    @Test
    @Order(2)
    @DisplayName("Deveria retornar cliente cadastrado com sucesso")
    void deveria_retornar_cliente_cadastrado_com_sucesso() throws Exception {
        //As chamadas ao redis estao sendo simuladas pelo embedded redis, que sobe um servidor redis em memoria para
        //fins de testes

        when(this.clienteService.buscarClienteEPedidos(anyInt())).thenReturn(this.clienteResponse);
        this.mvc
                .perform(get(URL + "/consulta/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("fulano@gmail.com"));

    }


}
