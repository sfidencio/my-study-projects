package com.github.sfidencio.vendas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Table(name = "CLIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "NOME", nullable = false)
    @NotEmpty(message = "{cliente.nome.obrigatorio}")
    @NotNull(message = "{cliente.nome.obrigatorio}")
    private String nome;
    @Column(name = "CPF", length = 11, nullable = false)
    @CPF(message = "{cliente.cpf.invalido}")
    private String cpf;
    @Column(name = "EMAIL", length = 100)
    @Email(message = "{cliente.email.invalido}")
    private String email;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Pedido.class)
    private List<Pedido> pedidos;
}
