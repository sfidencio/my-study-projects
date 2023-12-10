package com.github.sfidencio.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.sfidencio.vendas.api.dto.ClienteResponseSemRetornoDosPedidos;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "CLIENTE")
//@Data - Se usar ese aqui nao funciona a listagem de pedidos no endpoint /v1/api/clientes/consulta-todos
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "CLIENTE_SEQ", sequenceName = "CLIENTE_SEQ", allocationSize = 1)
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
    @JsonManagedReference
    //@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Pedido.class)
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Pedido> pedidos;

    public ClienteResponseSemRetornoDosPedidos toResponse() {
        return new ClienteResponseSemRetornoDosPedidos(
                this.id,
                this.nome,
                this.cpf,
                this.email,
                this.pedidos
        );
    }
}
