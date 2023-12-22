package com.github.sfidencio.vendas.api.dto;


import com.github.sfidencio.vendas.domain.entity.Pedido;

import java.io.Serializable;
import java.util.Set;

public record ClienteResponse(Integer id, String nome, String cpf, String email,
                              Set<Pedido> pedidos) implements Serializable {
}
