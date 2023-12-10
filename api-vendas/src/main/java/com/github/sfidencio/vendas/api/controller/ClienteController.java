package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@EnableCaching
public interface ClienteController {
    @GetMapping("/consulta/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = "cliente", key = "#id",cacheNames = "cliente")
    ClienteResponse consultar(@PathVariable Integer id) throws NotFoundException;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    //@CacheEvict(value = "cliente", allEntries = true, cacheNames = "cliente")
    void salvar(@RequestBody @Valid ClienteRequest clienteRequest) throws NotFoundException;

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CachePut(value = "cliente", key = "#id",cacheNames = "cliente")
    void atualizar(@RequestBody @Valid ClienteRequest clienteRequest, @PathVariable("id") Integer id);


    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "cliente", key = "#id",cacheNames = "cliente")
    void excluir(@PathVariable Integer id) throws NotFoundException;

    @GetMapping("/consulta-todos-clientes")
    @Cacheable(value = "cliente", key = "#root.method.name",cacheNames = "cliente")
    @ResponseStatus(HttpStatus.OK)
    List<ClienteResponse> consultarTodos();

    @GetMapping("/consulta-maturity/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MappingJacksonValue> consultarComNivelMaturidadeAplicadoNoEndPoint(@PathVariable Integer id) throws NotFoundException;
}
