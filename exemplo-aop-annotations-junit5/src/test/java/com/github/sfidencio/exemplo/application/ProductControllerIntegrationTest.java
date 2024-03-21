package com.github.sfidencio.exemplo.application;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String url = "/api/v1/products";


    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should return 201 when create product on success")
    public void shouldReturn201WhenCreateProductOnSuccess() {
        RestAssured.given()
                .contentType("application/json")
                .body("{\"description\":\"Test Product\",\"price\":7.99,\"stock\":200}")
                .when()
                .post(url)
                .then()
                .statusCode(201)
                .log()
                .all();

    }

    @Test
    @DisplayName("Should return 400 when create product with description null")
    public void shouldReturn400WhenCreateProductWithDescriptionNull() {
        RestAssured.given()
                .contentType("application/json")
                .body("{\"description\":\"\",\"price\":7.99,\"stock\":200}")
                .when()
                .post(url)
                .then()
                .statusCode(422)
                .log()
                .all();
    }

    @Test
    @DisplayName("Should return 400 when create product with sock negative")
    public void shouldReturn400WhenCreateProductWithStockNegative() {
        RestAssured.given()
                .contentType("application/json")
                .body("{\"description\":\"Test Product\",\"price\":7.99,\"stock\":-200}")
                .when()
                .post(url)
                .then()
                .statusCode(400)
                .log()
                .all();
    }

}