package com.github.sfidencio.vendas.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "PRODUTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Integer id;
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    @Column(name = "PRECO", nullable = false)
    private BigDecimal preco;
}