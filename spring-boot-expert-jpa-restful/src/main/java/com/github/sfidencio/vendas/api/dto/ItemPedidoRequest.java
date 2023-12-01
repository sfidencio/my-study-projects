package com.github.sfidencio.vendas.api.dto;

import com.github.sfidencio.vendas.domain.entity.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoRequest(Integer id, BigDecimal quantidade, ProdutoRequest produto) {
    public ItemPedido toEntity() {
        return ItemPedido.builder()
                .id(id)
                .quantidade(quantidade)
                .valorUnitario(produto.preco())
                .produto(produto.toEntity())
                .build();
    }
}
