package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.ProdutoRequest;
import com.github.sfidencio.vendas.api.dto.ProdutoResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProdutoController {
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    void salvar(@Valid @RequestBody ProdutoRequest produtoRequest);

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void atualizar(@Valid @RequestBody ProdutoRequest produtoRequest,@PathVariable("id") Integer id) throws NotFoundException;

    @GetMapping("/consulta/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProdutoResponse consultar(@PathVariable("id") Integer id) throws NotFoundException;

    @GetMapping("/consulta-todos-produtos")
    @ResponseStatus(HttpStatus.OK)
    List<ProdutoResponse> consultarTodos() throws NotFoundException;

    @GetMapping("/consulta/paginada/{numeroPagina}/{tamanhoPagina}")
    @ResponseStatus(HttpStatus.OK)
    Page<ProdutoResponse> consultarProdutosPaginado(@PathVariable("numeroPagina") int numeroPagina,
                                                    @PathVariable("tamanhoPagina") int tamanhoPagina)
            throws NotFoundException;
}
