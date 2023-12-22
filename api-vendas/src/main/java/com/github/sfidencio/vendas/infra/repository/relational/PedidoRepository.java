package com.github.sfidencio.vendas.infra.repository.relational;

import com.github.sfidencio.vendas.domain.entity.Pedido;
import com.github.sfidencio.vendas.domain.entity.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    //implementar paginacao
    //https://www.bezkoder.com/spring-boot-pagination-filter-jpa-pageable/
    Set<Pedido> findByStatusPedido(StatusPedido status);

}
