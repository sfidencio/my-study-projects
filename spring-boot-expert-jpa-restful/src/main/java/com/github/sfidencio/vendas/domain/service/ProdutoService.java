package com.github.sfidencio.vendas.domain.service;

import com.github.sfidencio.vendas.api.dto.ProdutoRequest;
import com.github.sfidencio.vendas.api.dto.ProdutoResponse;
import com.github.sfidencio.vendas.domain.entity.Produto;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProdutoService {

    void salvar(ProdutoRequest produtoRequest);

    Produto obterProduto(Integer id) throws NotFoundException;

    List<ProdutoResponse> listar() throws NotFoundException;

    Page<ProdutoResponse> listarProdutosPaginado(int numeroPagina, int tamanhoPagina) throws NotFoundException;
}
