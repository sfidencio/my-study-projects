package com.github.sfidencio.explorandorestapispringboot.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exemplo07/orders/{id-1}/customers/{id-2}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class Exemplo07Controller {

    /*
     * curl -kvs --location http://localhost:8080/exemplo07/orders/1/customers/2/list -X GET --header 'Content-Type: application/json'
     *
     * Deve retornar -> {"id":"3a700f43-6da6-4ff3-ac0a-3bb56bea5ce2","name":"Fulano","email":"fulano@gmail.com"}
     * Caso contrário, retorna um erro 400
     */
    @GetMapping("/list")
    public ResponseEntity<Customer> exemplo07(@PathVariable("id-1") String id1, @PathVariable("id-2") String id2) {
        if (id1.compareTo("1") != 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new Customer(java.util.UUID.randomUUID(), "Fulano", "fulano@gmail.com"));
    }
}
