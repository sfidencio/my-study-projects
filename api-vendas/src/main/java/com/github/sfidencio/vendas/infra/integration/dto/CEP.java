package com.github.sfidencio.vendas.infra.integration.dto;

public record CEP(String cep, String logradouro, String complemento, String bairro, String localidade, String uf,
                  String ibge, String gia, String ddd, String siafi) {
}
