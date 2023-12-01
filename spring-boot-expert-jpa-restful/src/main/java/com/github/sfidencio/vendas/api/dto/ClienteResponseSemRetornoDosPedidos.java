package com.github.sfidencio.vendas.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sfidencio.vendas.domain.entity.Pedido;

import java.util.Set;

public record ClienteResponseSemRetornoDosPedidos(Integer id, String nome, String cpf, String email, @JsonIgnore Set<Pedido> pedidos) {
}
