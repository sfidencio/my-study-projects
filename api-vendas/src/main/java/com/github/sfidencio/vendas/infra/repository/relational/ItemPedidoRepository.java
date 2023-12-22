package com.github.sfidencio.vendas.infra.repository.relational;

import com.github.sfidencio.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
