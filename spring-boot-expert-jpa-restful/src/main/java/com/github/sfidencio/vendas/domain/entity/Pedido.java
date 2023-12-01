package com.github.sfidencio.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.sfidencio.vendas.api.dto.PedidoResponse;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//https://pt.stackoverflow.com/questions/207188/depend%C3%AAncia-circular-em-api-rest-com-spring-boot

@Entity
@Table(name = "PEDIDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "PEDIDO_SEQ", sequenceName = "PEDIDO_SEQ", allocationSize = 1)
    private Integer id;
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    @Column(name = "VALOR_TOTAL", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
    @JsonBackReference
    @ManyToOne
    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false, targetEntity = Cliente.class)
    @JoinColumn(name = "CLIENTE_ID", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_CLIENTE"))
    private Cliente cliente;
    @Column(name = "DATA_PEDIDO", nullable = false)
    private LocalDate dataPedido;
    @Column(name = "HORA_PEDIDO", nullable = false)
    private LocalDateTime horaPedido;
    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<ItemPedido> itemPedido;
    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    public PedidoResponse toResponse() {
        return new PedidoResponse(
                this.id,
                this.descricao,
                this.valorTotal,
                this.cliente.toResponse(),
                this.dataPedido,
                this.horaPedido,
                this.itemPedido,
                this.statusPedido);
    }
}