package com.github.sfidencio.vendas.api.controller.imp;

import com.github.sfidencio.vendas.api.controller.ProdutoController;
import com.github.sfidencio.vendas.api.dto.ProdutoRequest;
import com.github.sfidencio.vendas.api.dto.ProdutoResponse;
import com.github.sfidencio.vendas.domain.service.ProdutoService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/produtos")
@Log4j2
public class ProdutoControllerImp implements ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoControllerImp(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody List<ProdutoRequest> produtoRequest) {
        log.info("Salvando produto: {}", produtoRequest);
        produtoRequest.forEach(p -> {
            this.produtoService.salvar(p);
        });
    }

    @GetMapping("/consulta-todos-produtos")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponse> consultarTodos() throws NotFoundException {
        log.info("Consultando todos os produtos");
        return this.produtoService.listar();
    }

    @GetMapping("/consulta/paginada/{numeroPagina}/{tamanhoPagina}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProdutoResponse> consultarProdutosPaginado(@PathVariable("numeroPagina") int numeroPagina,
                                                           @PathVariable("tamanhoPagina") int tamanhoPagina)
            throws NotFoundException {
        log.info("Consultando todos os produtos paginados");
        return this.produtoService.listarProdutosPaginado(numeroPagina, tamanhoPagina);
    }
}
