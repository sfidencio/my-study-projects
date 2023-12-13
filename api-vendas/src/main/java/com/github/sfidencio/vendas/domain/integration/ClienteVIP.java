package com.github.sfidencio.vendas.domain.integration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document("cliente")
public record ClienteVIP(
        @MongoId(FieldType.OBJECT_ID)
        String id,

        @NotEmpty(message = "{cliente.nome.obrigatorio}")
        String nome,

        @CPF(message = "{cliente.cpf.invalido}")
        @NotEmpty(message = "{cliente.cpf.obrigatorio}")
        String cpf,

        @Email(message = "{cliente.email.invalido}")
        @NotEmpty(message = "{cliente.email.obrigatorio}")
        String email
) {
}
