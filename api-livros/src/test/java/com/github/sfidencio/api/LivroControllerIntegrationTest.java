package com.github.sfidencio.api;

import com.github.sfidencio.api.dto.LivroDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LivroControllerIntegrationTest {
    public static final String URI = "/api/v1/livros";
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    @Order(1)
    public void deveCriarLivroComSucesso() {
        //Cenario
        LivroDTO dto = LivroDTO.builder()
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();

        //Execucao
        RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(dto)
                .post(URI)
                .then()
                .statusCode(201)
                .log()
                .all();
    }

    @Test
    @DisplayName("Deve lançar erro de negocio quando ISBN duplicado")
    @Order(2)
    public void deveLancarErroNegocioQuandoISBNDuplicado() {
        // Cenario
        LivroDTO dto = LivroDTO.builder()
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();

        //Execucao
        RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(dto)
                .post(URI)
                .then()
                .statusCode(400)
                .log()
                .all();
    }

    @Test
    @DisplayName("Deve obter informações de um livro")
    @Order(3)
    public void deveObterInformacoesLivro() {
        // Cenario
        LivroDTO dto = LivroDTO.builder()
                .titulo("Meu Livro 2")
                .autor("Autor")
                .isbn("1234")
                .build();

        //Execucao
        var response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(dto)
                .post(URI)
                .then()
                .statusCode(201)
                .extract()
                .response();

        var id = response.jsonPath().getLong("id");

        //Verificacao
        RestAssured.given()
                .accept("application/json")
                .get(URI.concat("/" + id))
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    @DisplayName("Deve alterar um livro")
    @Order(4)
    public void deveAlterarLivro() {
        // Cenario
        LivroDTO dto = LivroDTO.builder()
                .titulo("Meu Livro 3")
                .autor("Autor")
                .isbn("12345")
                .build();

        //Execucao
        var response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(dto)
                .post(URI)
                .then()
                .statusCode(201)
                .extract()
                .response();

        var id = response.jsonPath().getLong("id");

        dto.setAutor("Autor Atualizado");

        //Verificacao
        var responseAlterado = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(dto)
                .put(URI.concat("/" + id))
                .then()
                .statusCode(200)
                .extract();

        var autor = responseAlterado.jsonPath().getString("autor");
        assertThat(autor).isEqualTo("Autor Atualizado");
    }


    @Test
    @DisplayName("Deve deletar um livro")
    @Order(4)
    public void deveDeletarLivro() {
        // Cenario
        LivroDTO dto = LivroDTO.builder()
                .titulo("Meu Livro 4")
                .autor("Autor")
                .isbn("123456")
                .build();

        //Execucao
        var response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(dto)
                .post(URI)
                .then()
                .statusCode(201)
                .extract()
                .response();

        var id = response.jsonPath().getLong("id");

        //Verificacao
        RestAssured.given()
                .accept("application/json")
                .delete(URI.concat("/" + id))
                .then()
                .statusCode(204)
                .log()
                .all();
    }
}
