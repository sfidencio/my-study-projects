package com.github.sfidencio.vendas.domain.service.imp;

import com.github.sfidencio.vendas.api.dto.ProdutoRequest;
import com.github.sfidencio.vendas.api.dto.ProdutoResponse;
import com.github.sfidencio.vendas.domain.entity.Produto;
import com.github.sfidencio.vendas.domain.service.ProdutoService;
import com.github.sfidencio.vendas.infra.config.exceptions.NotFoundException;
import com.github.sfidencio.vendas.infra.repository.relational.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImp implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImp(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void salvar(ProdutoRequest produtoRequest) {
        this.produtoRepository.save(new Produto(null, produtoRequest.descricao(), produtoRequest.preco()));
    }

    @Override
    public Produto obterProduto(Integer id) throws NotFoundException {
        return this.produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    @Override
    public List<ProdutoResponse> listar() {
        return this.produtoRepository.findAll().stream().map(p -> new ProdutoResponse(p.getId(), p.getDescricao(), p.getPreco())).collect(Collectors.toList());
    }

    @Override
    public Page<ProdutoResponse> listarProdutosPaginado(int numeroPagina, int tamanhoPagina) {
        Pageable pageAble = PageRequest.of(numeroPagina, tamanhoPagina);

        return this.produtoRepository.findAll(pageAble).map(p -> new ProdutoResponse(p.getId(), p.getDescricao(), p.getPreco()));
    }

    @Override
    public void atualizar(ProdutoRequest produtoRequest, Integer id) throws NotFoundException {

        //Outra forma de atualizar usando lambda expression (ifPresent)
        /*this.produtoRepository.findById(id).ifPresent(p -> {
            p.setDescricao(produtoRequest.descricao());
            p.setPreco(produtoRequest.preco());
            this.produtoRepository.save(p);
        });*/
        var produtoAtualizar = this.produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        produtoAtualizar.setDescricao(produtoRequest.descricao());
        produtoAtualizar.setPreco(produtoRequest.preco());
        this.produtoRepository.save(produtoAtualizar);
        this.produtoRepository.save(produtoAtualizar);
    }
}
