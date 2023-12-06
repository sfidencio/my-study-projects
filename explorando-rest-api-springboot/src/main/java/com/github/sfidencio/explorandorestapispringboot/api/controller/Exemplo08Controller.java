package com.github.sfidencio.explorandorestapispringboot.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping(value = "/exemplo08", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class Exemplo08Controller {

    /*
     * curl -kvs --location http://localhost:8080/exemplo08 -X GET --header 'Content-Type: application/json'
     *
     * Deve retornar -> {"id":"3a700f43-6da6-4ff3-ac0a-3bb56bea5ce2","name":"Fulano","email":"fulano@gmail.com"}
     *
     * Basico @lombok
     */
    @GetMapping
    public ResponseEntity<User> exemplo08() {
        return ResponseEntity.ok(User.builder()
                .login("fulano")
                //Meramente ilustrativo, Base64 não é um algoritmo de criptografia
                .password(Base64.getEncoder().encodeToString("123456".getBytes()))
                .build());
    }
}
