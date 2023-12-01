package com.github.sfidencio.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@Table(name = "agencia")
@AllArgsConstructor
@NoArgsConstructor
@ToString
//https://medium.com/@FernandoUnix/gerando-chave-prim%C3%A1ria-uuid-com-jpa-e-hibernate-719285a13eb2
//https://cursos.alura.com.br/forum/topico-org-hibernate-persistentobjectexception-detached-entity-passed-to-persist-com-topgun-airline-domain-user-user-304602
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "agencia", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Cliente> clientes;
}
