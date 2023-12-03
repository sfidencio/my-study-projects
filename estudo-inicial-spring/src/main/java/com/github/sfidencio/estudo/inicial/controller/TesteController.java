package com.github.sfidencio.estudo.inicial.controller;


import com.github.sfidencio.estudo.inicial.model.MyModel;
import com.github.sfidencio.estudo.inicial.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class TesteController {

    @Autowired
    @Qualifier("helloWorld")
    private String helloWorld;

    @Autowired
    @Qualifier("jsonProduto")
    private String jsonProduto;

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    /*@Autowired
    private MyService myService;
    */
    private final MyService myService;

    public TesteController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<String> myGetHelloWorld() {
        return ResponseEntity.ok(helloWorld);
    }

    @GetMapping("/produto")
    public ResponseEntity<String> myGetJsonProduto() {
        return ResponseEntity.ok(jsonProduto);
    }


    @GetMapping("/application-name")
    public ResponseEntity<String> myGetApplicationName() {
        return ResponseEntity.ok(this.applicationName);
    }

    @PostMapping("/salvar-cliente")
    public ResponseEntity<String> mySalvarCliente(@RequestBody MyModel model) {
        myService.salvar(new MyModel(UUID.randomUUID(), model.getName()));
        return ResponseEntity.ok("Salvo com sucesso!");
    }
}
