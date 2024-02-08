package br.com.sfidencio.clientes.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "BINARY(16)")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    private UUID id;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;
    @Column(nullable = false)
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;
}
