package com.github.sfidencio.vendas.api.dto;

import java.util.Map;

public record ClienteResponse(Integer id, String nome, String cpf,String email) {
}
