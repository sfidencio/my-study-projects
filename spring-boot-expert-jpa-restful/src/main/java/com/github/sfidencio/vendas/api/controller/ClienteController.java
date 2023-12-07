package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClienteController {
    @GetMapping("/consulta/{id}")
    @ResponseStatus(HttpStatus.OK)
    ClienteResponse consultar(@PathVariable Integer id) throws NotFoundException;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    void salvar(@RequestBody @Valid ClienteRequest clienteRequest) throws NotFoundException;

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void atualizar(@RequestBody @Valid ClienteRequest clienteRequest, @PathVariable("id") Integer id);


    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void excluir(@PathVariable Integer id) throws NotFoundException;

    @GetMapping("/consulta-todos-clientes")
    @ResponseStatus(HttpStatus.OK)
    List<ClienteResponse> consultarTodos();

    @GetMapping("/consulta-maturity/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MappingJacksonValue> consultarComNivelMaturidadeAplicadoNoEndPoint(@PathVariable Integer id) throws NotFoundException;
}
