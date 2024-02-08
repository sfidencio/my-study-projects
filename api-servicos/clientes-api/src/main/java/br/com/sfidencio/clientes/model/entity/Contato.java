package br.com.sfidencio.clientes.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contato {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "BINARY(16)")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    private UUID id;
    //private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "{campo.nome.contato.obrigatorio}")
    private String nome;
    @Column(nullable = false)
    @Email(message = "{campo.email.invalido.contato.obrigatorio}")
    @NotEmpty(message = "{campo.email.vazio.contato.obrigatorio}")
    private String email;
    @Column(nullable = false)
    private Boolean favorito = false;
    @Column
    @Lob
    private byte[] foto;


}
