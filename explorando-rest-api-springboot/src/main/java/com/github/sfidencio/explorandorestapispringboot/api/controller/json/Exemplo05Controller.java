package com.github.sfidencio.explorandorestapispringboot.api.controller.json;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("json.Exemplo05Controller")
public class Exemplo05Controller {
    /*
     * O método exemplo01() é mapeado para a URL /exemplo01
     * curl  --location http://localhost:8080/exemplo01 -X GET --header 'Content-Type: application/json'
     * curl  --location http://localhost:8080/exemplo01-1 -X GET --header 'Content-Type: application/json'
     */
    @RequestMapping(value = {"/exemplo05"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Customer> exemplo05(@RequestBody Customer customer) {
        return ResponseEntity.ok(new Customer(java.util.UUID.randomUUID(), "Fulano", "fulano@gmail.com"));
    }
}
