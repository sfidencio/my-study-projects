package com.github.sfidencio.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.domain.services.LivroService;
import com.github.sfidencio.infrastructure.exceptions.BusinessException;

import lombok.extern.log4j.Log4j2;

@AutoConfigureMockMvc
@WebMvcTest
@ActiveProfiles("test")
@Log4j2
public class LivroControllerTest {

    @LocalServerPort
    private String port;

    @MockBean
    private LivroService livroService;

    @Autowired
    private MockMvc mvc;

    static final String API = "/v1/livros";

    @Test
    @DisplayName("Deve retornar erro do campo isbn nao preenchido")
    public void deveriaRetornarErrosCasoCamposObrigatoriosNaoPreenchidos() throws Exception {
        var livro = Livro.builder()
                .autor("Fulano")
                .titulo("Meu livro")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livro);

        // var result = this.mvc.perform(MockMvcRequestBuilders.post(API)
        this.mvc.perform(MockMvcRequestBuilders.post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.hasSize(1)));
        // log.info("jsonReturn error -> {}
        // ",result.andReturn().getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    public void deveCriarLivroComSucesso() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        // Mockando o repositorio ao salvar o livro
        BDDMockito.given(this.livroService.salvar(Mockito.any(Livro.class))).willReturn(livro); // Aqui e um teste de
                                                                                                // controller, por isso
                                                                                                // utilizamos o
                                                                                                // BDDMockito.given
        // Mockito.when(this.livroRepository.save(Mockito.any(Livro.class))).thenReturn(livro);
        // -> mais utilizado no teste de service ou unitario

        ObjectMapper mapper = new ObjectMapper();
        // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = mapper.writeValueAsString(livro);

        var result = this.mvc.perform(MockMvcRequestBuilders.post(API) // Use the correct method for performing the HTTP
                                                                       // POST request
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("titulo").value("Meu Livro"));

        var jsonReturn = result.andReturn().getResponse().getContentAsString();
        log.info("jsonReturn: {}", jsonReturn);

    }

    @Test
    @DisplayName("Deve retornar erro ao tentar criar um livro com isbn duplicado")
    public void deveriaRetornarErroAoTentarCriarLivroComIsbnDuplicado() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();

        // Mockando o repositorio ao salvar o livro
        BDDMockito.given(this.livroService.salvar(Mockito.any(Livro.class)))
                .willThrow(new BusinessException("Isbn ja cadastrado"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livro);

        var result = this.mvc.perform(MockMvcRequestBuilders.post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.hasSize(1)))
                .andExpect(jsonPath("errors[0]").value("Isbn ja cadastrado"));

        var jsonReturn = result.andReturn().getResponse().getContentAsString();
        log.info("jsonReturn: {}", jsonReturn);
    }
}
