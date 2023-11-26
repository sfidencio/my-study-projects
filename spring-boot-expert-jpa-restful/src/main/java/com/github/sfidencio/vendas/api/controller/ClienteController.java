package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.service.ClienteService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/clientes")
@Log4j2
//https://medium.com/@seonggil/creating-a-maturity-level-3-rest-api-with-hateoas-fcd76d1b2db9
//Implementing HATEOAS in a REST API acima.
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/consulta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponse consultar(@PathVariable Integer id) throws NotFoundException {
        log.info("Consultando cliente por id: {}", id);
        return this.clienteService.buscarPorId(id);
    }

    @GetMapping("/consulta-maturity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MappingJacksonValue> consultarComNivelMaturidadeAplicadoNoEndPoint(@PathVariable Integer id) throws NotFoundException {
        log.info("Consultando cliente por id: {}", id);
        var clienteResponse = this.clienteService.buscarPorId(id);
        //Map<String,ClienteResponse> dados = Map.of("dados",clienteResponse);
        Map<String, ClienteResponse> dados = new HashMap<>();
        dados.put("dados", clienteResponse);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class).consultarComNivelMaturidadeAplicadoNoEndPoint(id));
        EntityModel entityModel = EntityModel.of(dados);
        entityModel.add(linkTo.withRel("cadastro-jpa-repository"));
        entityModel.add(linkTo.withRel("cadastro-entity-manager"));
        entityModel.add(linkTo.withRel("cadastro-jdbc-template"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);
        return ResponseEntity.ok(mappingJacksonValue);
    }

}
