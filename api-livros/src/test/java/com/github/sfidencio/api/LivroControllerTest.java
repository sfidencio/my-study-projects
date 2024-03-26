package com.github.sfidencio.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sfidencio.api.dto.LivroDTO;
import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.domain.service.LivroService;
import com.github.sfidencio.infrastructure.exceptions.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
//@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
public class LivroControllerTest {

    //@LocalServerPort
    //private String port;

    @MockBean
    private LivroService livroService;

    @Autowired
    private MockMvc mvc;

    //@Autowired
    //private WebApplicationContext context;
    //private MockMvc mvc;

    static final String API = "/api/v1/livros";


    @BeforeEach
    public void setUp() {
        //this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    public void deveCriarLivroComSucesso() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        // Mockando o service ao salvar o livro
        BDDMockito.given(this.livroService.salvar(Mockito.any(Livro.class))).willReturn(livro); // Aqui e um teste de
        // controller, por isso
        // utilizamos o
        // BDDMockito.given
        // Mockito.when(this.livroRepository.save(Mockito.any(Livro.class))).thenReturn(livro);
        // -> mais utilizado no teste de service ou unitario

        ObjectMapper mapper = new ObjectMapper();
        // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        var dto = LivroDTO.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();
        String json = mapper.writeValueAsString(dto);

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
    @DisplayName("Deve atualizar um livro com sucesso")
    public void deveAtualizarLivroComSucesso() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        // Mockando o service ao salvar o livro
        BDDMockito.given(this.livroService.atualizar(Mockito.any(Livro.class))).willReturn(livro);

        ObjectMapper mapper = new ObjectMapper();
        var dto = LivroDTO.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();
        String json = mapper.writeValueAsString(dto);

        var result = this.mvc.perform(MockMvcRequestBuilders.put(API + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("titulo").value("Meu Livro"));


        var jsonReturn = result.andReturn().getResponse().getContentAsString();
        log.info("jsonReturn: {}", jsonReturn);

    }

    @Test
    @DisplayName("Deve buscar um livro por id com sucess")
    public void deveBuscarLivroPorIdComSucesso() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        // Mockando o service ao salvar o livro
        BDDMockito.given(this.livroService.buscarPorId(Mockito.any(Long.class))).willReturn(livro);

        var result = this.mvc.perform(MockMvcRequestBuilders.get(API + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("titulo").value("Meu Livro"));


        var jsonReturn = result.andReturn().getResponse().getContentAsString();
        log.info("jsonReturn: {}", jsonReturn);

    }

    @Test
    @DisplayName("Deve excluir um livro com sucesso")
    public void deveExcluirLivroComSucesso() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        // Mockando o service ao salvar o livro
        BDDMockito.doNothing().when(this.livroService).excluir(Mockito.any(Long.class));

        this.mvc.perform(MockMvcRequestBuilders.delete(API + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("Deve retornar erro ao tentar excluir um livro inexistente")
    public void deveriaRetornarErroAoTentarExcluirLivroInexistente() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        // Mockando o service ao salvar o livro
        String mensagemErro = "Livro não encontrado.";
        BDDMockito.doThrow(new BusinessException(mensagemErro)).when(this.livroService).excluir(Mockito.any(Long.class));

        var result = this.mvc.perform(MockMvcRequestBuilders.delete(API + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.hasSize(1)))
                .andExpect(jsonPath("errors[0]").value(mensagemErro));

        var jsonReturn = result.andReturn().getResponse().getContentAsString();
        log.info("jsonReturn: {}", jsonReturn);
    }


    @Test
    @DisplayName("Deve retornar erro ao tentar atualizar um livro inexistente")
    public void deveriaRetornarErroAoTentarAtualizarLivroInexistente() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        // Mockando o service ao salvar o livro
        String mensagemErro = "Livro não encontrado.";
        BDDMockito.given(this.livroService.atualizar(Mockito.any(Livro.class)))
                .willThrow(new BusinessException(mensagemErro));

        ObjectMapper mapper = new ObjectMapper();
        var dto = LivroDTO.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();
        String json = mapper.writeValueAsString(dto);

        var result = this.mvc.perform(MockMvcRequestBuilders.put(API + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.hasSize(1)))
                .andExpect(jsonPath("errors[0]").value(mensagemErro));

        var jsonReturn = result.andReturn().getResponse().getContentAsString();
        log.info("jsonReturn: {}", jsonReturn);
    }

    @Test
    @DisplayName("Deve retornar erro do campo isbn nao preenchido")
    public void deveriaRetornarErrosCasoCamposObrigatoriosNaoPreenchidos() throws Exception {
        /*var livro = Livro.builder()
                .autor("Fulano")
                .titulo("Meu livro")
                .build();*/

        ObjectMapper mapper = new ObjectMapper();
        var dto = LivroDTO.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                //.isbn("123")
                .build();
        String json = mapper.writeValueAsString(dto);

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
    @DisplayName("Deve retornar erro ao tentar criar um livro com isbn duplicado")
    public void deveriaRetornarErroAoTentarCriarLivroComIsbnDuplicado() throws Exception {
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();

        // Mockando o repositorio ao salvar o livro
        String mensagemErro = "ISBN já cadastrado.";
        BDDMockito.given(this.livroService.salvar(Mockito.any(Livro.class)))
                .willThrow(new BusinessException(mensagemErro));

        ObjectMapper mapper = new ObjectMapper();
        var dto = LivroDTO.builder()
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();
        String json = mapper.writeValueAsString(dto);

        var result = this.mvc.perform(MockMvcRequestBuilders.post(API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.hasSize(1)))
                .andExpect(jsonPath("errors[0]").value(mensagemErro));

        var jsonReturn = result.andReturn().getResponse().getContentAsString();
        log.info("jsonReturn: {}", jsonReturn);
    }

}
