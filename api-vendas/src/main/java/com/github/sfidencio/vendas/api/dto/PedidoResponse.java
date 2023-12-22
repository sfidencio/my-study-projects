package com.github.sfidencio.vendas.api.dto;

import com.github.sfidencio.vendas.domain.entity.ItemPedido;
import com.github.sfidencio.vendas.domain.entity.Pedido;
import com.github.sfidencio.vendas.domain.entity.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Integer id, String descricao, BigDecimal valorTotal,
                             ClienteResponseSemRetornoDosPedidos cliente,
                             LocalDate dataPedido, LocalDateTime horaPedido, List<ItemPedido> itensPedido,
                             StatusPedido statusPedido) {
    public Pedido toPedido() {
        return new Pedido(
                this.id,
                this.descricao,
                this.valorTotal,
                null,
                this.dataPedido,
                this.horaPedido,
                this.itensPedido,
                this.statusPedido);
    }
}
