package com.github.sfidencio.vendas.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestValidationForJdbcTemplate(@Valid Integer id, @NotNull(message = "{cliente.nome.obrigatorio}") String nome, @CPF(message = "{cliente.cpf.invalido}") String cpf, @Email(message = "{cliente.email.invalido}") String email) {
}
