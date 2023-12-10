package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.PedidoRequest;
import com.github.sfidencio.vendas.api.dto.PedidoResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface PedidoController {
    @PostMapping("/salvar")
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
