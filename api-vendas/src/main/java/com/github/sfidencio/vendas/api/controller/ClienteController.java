package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.ClienteRequest;
import com.github.sfidencio.vendas.api.dto.ClienteResponse;
import com.github.sfidencio.vendas.domain.entity.mongodb.ClienteVIP;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cliente", description = "API de Cliente")
@RequestMapping(value = "/v1/api/clientes", produces = "application/json")
public interface ClienteController {

    @Operation(summary = "Consulta um cliente pelo id", description = "Consulta um cliente pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/consulta/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = "cliente", key = "#id", cacheNames = "cliente")
    ClienteResponse consultar(@PathVariable Integer id) throws NotFoundException;

    @Operation(summary = "Salva cliente", description = "Salva cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de negócio"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/salva")
    @ResponseStatus(HttpStatus.CREATED)
        //@CacheEvict(value = "cliente", allEntries = true, cacheNames = "cliente")
    void salvar(@RequestBody @Valid ClienteRequest clienteRequest) throws NotFoundException;

    @Operation(summary = "Atualiza cliente", description = "Atualiza cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de negócio"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/atualiza/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CachePut(value = "cliente", key = "#id", cacheNames = "cliente")
    void atualizar(@RequestBody @Valid ClienteRequest clienteRequest, @PathVariable("id") Integer id);


    @Operation(summary = "Exclui cliente", description = "Exclui cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/exclui/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "cliente", key = "#id", cacheNames = "cliente")
    void excluir(@PathVariable Integer id) throws NotFoundException;

    @GetMapping("/consulta-todos-clientes")
    @Cacheable(value = "cliente", key = "#root.method.name", cacheNames = "cliente")
    @ResponseStatus(HttpStatus.OK)
    List<ClienteResponse> consultarTodos();

    @GetMapping("/consulta-maturity/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MappingJacksonValue> consultarComNivelMaturidadeAplicadoNoEndPoint(@PathVariable Integer id) throws NotFoundException;

    @PostMapping("/salva-cliente-vip")
    @ResponseStatus(HttpStatus.CREATED)
    void salvarClienteVIP(@RequestBody @Valid ClienteVIP request) throws NotFoundException;

    @GetMapping("/consulta-cliente-vip/{id}")
    @ResponseStatus(HttpStatus.OK)
    ClienteVIP consultarClienteVIP(@PathVariable String id) throws NotFoundException;


    @GetMapping("/consulta-todos-clientes-vip")
    @ResponseStatus(HttpStatus.OK)
    List<ClienteVIP> consultarTodosClientesVIP();
}
