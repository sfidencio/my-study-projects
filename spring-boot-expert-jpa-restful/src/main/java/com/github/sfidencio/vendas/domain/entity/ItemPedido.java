package com.github.sfidencio.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "ITEM_PEDIDO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "ITEM_PEDIDO_SEQ", sequenceName = "ITEM_PEDIDO_SEQ", allocationSize = 1)
    private Integer id;
    @Column(name = "QUANTIDADE", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidade;
    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_PRODUTO"))
    private Produto produto;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false, targetEntity = Pedido.class)
    @JoinColumn(name = "PEDIDO_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_PEDIDO"))
    private Pedido pedido;
    @Column(name = "VALOR_UNITARIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

}
