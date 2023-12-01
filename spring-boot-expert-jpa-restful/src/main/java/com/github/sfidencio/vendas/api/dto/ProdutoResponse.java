package com.github.sfidencio.vendas.api.dto;

import java.math.BigDecimal;

public record ProdutoResponse(Integer id, String descricao, BigDecimal preco) {
}
