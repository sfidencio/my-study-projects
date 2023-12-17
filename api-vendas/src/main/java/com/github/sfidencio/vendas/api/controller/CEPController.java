package com.github.sfidencio.vendas.api.controller;


import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.integration.dto.CEP;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "CEP", description = "API de consulta CEP")
@RequestMapping(value = "/v1/api/cep", produces = "application/json")
public interface CEPController {
    @GetMapping("/consulta/{cep}")
    @ResponseStatus(HttpStatus.OK)
    CEP consultar(@PathVariable("cep") String cep) throws NotFoundException;
}
