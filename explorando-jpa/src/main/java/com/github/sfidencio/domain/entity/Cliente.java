package com.github.sfidencio.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
//https://medium.com/@FernandoUnix/gerando-chave-prim%C3%A1ria-uuid-com-jpa-e-hibernate-719285a13eb2
//https://cursos.alura.com.br/forum/topico-org-hibernate-persistentobjectexception-detached-entity-passed-to-persist-com-topgun-airline-domain-user-user-304602
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "limite_credito")
    private BigDecimal limiteCredito;
    @ManyToOne
    @JoinColumn(name = "agencia_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_agencia_id"))
    private Agencia agencia;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", limiteCredito=" + limiteCredito +
                '}';
    }
}
