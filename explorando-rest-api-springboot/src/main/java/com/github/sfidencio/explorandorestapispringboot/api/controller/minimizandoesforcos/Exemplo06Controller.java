package com.github.sfidencio.explorandorestapispringboot.api.controller.minimizandoesforcos;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exemplo06")
public class Exemplo06Controller {
    /*
     * O método exemplo06() é mapeado para a URL /exemplo06
     * curl -kvs --location http://localhost:8080/exemplo06 -X GET --header 'Content-Type: application/json'
     */
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Customer exemplo06() {
       return new Customer(java.util.UUID.randomUUID(), "Fulano", "fulano@gmail.com");
    }
}
