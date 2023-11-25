package com.github.sfidencio.vendas.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "ITEM_PEDIDO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Integer id;
    @Column(name = "QUANTIDADE", nullable = false)
    private BigDecimal quantidade;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, targetEntity = Produto.class)
    @JoinColumn(name = "PRODUTO_ID", nullable = false)
    private Produto produto;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, targetEntity = Pedido.class)
    @JoinColumn(name = "PEDIDO_ID", nullable = false)
    private Pedido pedido;
    @Column(name = "VALOR_UNITARIO", nullable = false)
    private BigDecimal valorUnitario;

}
