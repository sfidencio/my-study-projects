package com.github.sfidencio.vendas.api.controller.imp;

import com.github.sfidencio.vendas.api.controller.ProdutoController;
import com.github.sfidencio.vendas.api.dto.ProdutoRequest;
import com.github.sfidencio.vendas.api.dto.ProdutoResponse;
import com.github.sfidencio.vendas.domain.service.ProdutoService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@Log4j2
public class ProdutoControllerImp implements ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoControllerImp(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @Override
    public void salvar(@Valid @RequestBody ProdutoRequest produtoRequest) {
        log.info("Salvando produto: {}", produtoRequest);
        this.produtoService.salvar(produtoRequest);
    }

    @Override
    public void atualizar(ProdutoRequest produtoRequest, Integer id) throws NotFoundException {
        log.info("Atualizando produto: {}", produtoRequest);
        this.produtoService.atualizar(produtoRequest, id);
    }

    @Override
    public ProdutoResponse consultar(Integer id) throws NotFoundException {
        log.info("Consultando produto: {}", id);
        return this.produtoService.obterProduto(id).toResponse();
    }


    @Override
    public List<ProdutoResponse> consultarTodos() throws NotFoundException {
        log.info("Consultando todos os produtos");
        return this.produtoService.listar();
    }


    @Override
    public Page<ProdutoResponse> consultarPaginado(@PathVariable("numeroPagina") int numeroPagina,
                                                   @PathVariable("tamanhoPagina") int tamanhoPagina)
            throws NotFoundException {
        log.info("Consultando todos os produtos paginados");
        return this.produtoService.listarProdutosPaginado(numeroPagina, tamanhoPagina);
    }


    public static void main(String[] args) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
        System.out.println(p.matcher("1234567890").matches());
    }
}
