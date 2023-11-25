package com.github.sfidencio.vendas.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PEDIDO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    private Integer id;
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    @Column(name = "VALOR_TOTAL", nullable = false)
    private BigDecimal valorTotal;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, targetEntity = Cliente.class)
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private Cliente cliente;
    @Column(name = "DATA_PEDIDO", nullable = false)
    private LocalDate dataPedido;
    @Column(name = "HORA_PEDIDO", nullable = false)
    private LocalDateTime horaPedido;
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ItemPedido.class)
    private List<ItemPedido> itemPedido;
}