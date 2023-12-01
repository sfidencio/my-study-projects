package com.github.sfidencio.vendas.api.dto;

import com.github.sfidencio.vendas.domain.entity.Produto;

import java.math.BigDecimal;

public record ProdutoRequest(Integer id,
                             String descricao,
                             BigDecimal preco) {
    public Produto toEntity() {
        return Produto.builder()
                .id(id)
                .descricao(descricao)
                .preco(preco)
                .build();
    }
}
