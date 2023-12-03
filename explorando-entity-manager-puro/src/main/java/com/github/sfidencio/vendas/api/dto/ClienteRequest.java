package com.github.sfidencio.vendas.api.dto;

public record ClienteRequest(Integer id, String nome, String cpf, String email) {
}
