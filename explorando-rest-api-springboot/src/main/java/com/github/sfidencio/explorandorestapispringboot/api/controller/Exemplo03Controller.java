package com.github.sfidencio.explorandorestapispringboot.api.controller;

import com.github.sfidencio.explorandorestapispringboot.api.controller.Customer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exemplo03Controller {
    /*
     * O método exemplo03() é mapeado para a URL /exemplo03
     * curl -kvs --location http://localhost:8080/exemplo03 -X GET --header 'Content-Type: application/xml'
     * retorna um XML
     *
       <Customer>
        <id>3a700f43-6da6-4ff3-ac0a-3bb56bea5ce2</id>
        <name>Fulano</name>
        <email>fulano@gmail.com</email>
       </Customer>
     */
    @RequestMapping(value = "/exemplo03", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Customer> exemplo03() {
        return ResponseEntity.ok(new Customer(java.util.UUID.randomUUID(), "Fulano", "fulano@gmail.com"));
    }
}
