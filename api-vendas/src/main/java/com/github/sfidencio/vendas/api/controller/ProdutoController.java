package com.github.sfidencio.vendas.api.controller;

import com.github.sfidencio.vendas.api.dto.ProdutoRequest;
import com.github.sfidencio.vendas.api.dto.ProdutoResponse;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableCaching
@Tag(name = "Produtos", description = "API de Produtos")
@RequestMapping(value = "/v1/api/produtos", produces = "application/json")
public interface ProdutoController {
    @Operation(summary = "Salva produto", description = "Salva produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de negócio"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    void salvar(@Valid @RequestBody ProdutoRequest produtoRequest);

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void atualizar(@Valid @RequestBody ProdutoRequest produtoRequest,@PathVariable("id") Integer id) throws NotFoundException;

    @Operation(summary = "Consulta um produto pelo id", description = "Consulta um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/consulta/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProdutoResponse consultar(@PathVariable("id") Integer id) throws NotFoundException;

    @Operation(summary = "Consulta todos produtos", description = "Utilize este endpoint para consultar todos os produtos apenas para exemplo, pois não é recomendado retornar todos os produtos de uma vez")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/consulta-todos-produtos")
    @ResponseStatus(HttpStatus.OK)
    List<ProdutoResponse> consultarTodos() throws NotFoundException;

    @Operation(summary = "Consulta utilizando recursos de paginação", description = "Consulta produtos utilizando recursos de paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/consulta/paginada/{numeroPagina}/{tamanhoPagina}")
    @ResponseStatus(HttpStatus.OK)
    Page<ProdutoResponse> consultarPaginado(@PathVariable("numeroPagina") int numeroPagina,
                                            @PathVariable("tamanhoPagina") int tamanhoPagina)
            throws NotFoundException;
}
