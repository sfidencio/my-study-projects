package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.PedidoRequest;
import com.github.sfidencio.vendas.api.dto.PedidoResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@EnableCaching
@Tag(name = "Pedidos", description = "API de Pedidos")
@RequestMapping(value = "/v1/api/pedidos", produces = "application/json")
public interface PedidoController {
    @Operation(summary = "Salva pedido", description = "Salva pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de negócio"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/salva")
    @ResponseStatus(HttpStatus.CREATED)
    void salvar(@RequestBody PedidoRequest pedidoRequest) throws NotFoundException;

    @GetMapping("/consulta-todos")
    @ResponseStatus(HttpStatus.OK)
    List<PedidoResponse> consultaTodos();

    @GetMapping("/consulta/pendentes")
    @ResponseStatus(HttpStatus.OK)
    Set<PedidoResponse> consultaPedentes() throws NotFoundException;

    @GetMapping("/consulta/aprovados")
    @ResponseStatus(HttpStatus.OK)
    Set<PedidoResponse> consultaPedidosAprovados() throws NotFoundException;

    @PatchMapping("/cancela-pedido/{id}")
    @ResponseStatus(HttpStatus.OK)
    void cancelaPedido(@PathVariable Integer id) throws NotFoundException;

    @PatchMapping("/aprova-pedido/{id}")
    @ResponseStatus(HttpStatus.OK)
    void aprovarPedido(@PathVariable Integer id) throws NotFoundException;
}
