package com.github.sfidencio.vendas.api.controller.imp.integration.withtestconteiner;


import com.github.sfidencio.vendas.api.controller.ClienteController;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment
        = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test2")
public class ClienteControllerImp3Test {

    @LocalServerPort
    private int port;

    @Autowired
    private ClienteController clienteController;

   /*static {
       GenericContainer<?> redis = new GenericContainer<>("redis:latest")
               .withExposedPorts(6379);
       redis.start();
   }*/

    static GenericContainer<?> redis = new GenericContainer<>("redis:latest")
            .withExposedPorts(6379);

    @BeforeAll
    static void beforeAll() {
        redis.start();
    }

    @AfterAll
    static void afterAll() {
        redis.stop();
    }


    /*@DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }*/

    @BeforeEach
    void setUp() {
        //RestAssured.port = port;
        baseURI = "http://localhost:" + port;
    }


    @Test
    @Order(1)
    void deveria_esta_rodando_redis() {
        assertTrue(redis.isRunning());
    }

    @Test
    @Order(2)
    void deveria_cadastrar_cliente_com_sucesso() {

        given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"nome\": \"Joao Carlos\",\n" +
                        "    \"cpf\": \"79681821076\",\n" +
                        "    \"email\": \"fulano@gmail.com" +
                        "\"\n" +
                        "}")
                .when()
                .post("/v1/api/clientes/salva")
                .then()
                .statusCode(201);

    }

    @Test
    @Order(3)
    void deveria_retornar_cliente_cadastrado_com_sucesso() {
        //As chamadas ao redis estao sendo simuladas pelo embedded redis, que sobe um servidor redis em memoria para
        //fins de testes

        given()
                .contentType("application/json")
                .when()
                .get("/v1/api/clientes/consulta/{id}", 1)
                .then()
                .statusCode(200);

    }


}
