package com.github.sfidencio.vendas.domain.entity;

import com.github.sfidencio.vendas.api.dto.ProdutoResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "PRODUTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "PRODUTO_SEQ", sequenceName = "PRODUTO_SEQ", allocationSize = 1)
    private Integer id;
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    @Column(name = "PRECO", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    //@OneToMany(mappedBy = "produto")
    //private List<ItemPedido> itemPedido;

    public ProdutoResponse toResponse() {
        return new ProdutoResponse(this.id, this.descricao, this.preco);
    }
}