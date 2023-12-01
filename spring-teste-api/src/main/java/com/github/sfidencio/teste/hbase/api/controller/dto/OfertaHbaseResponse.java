package com.github.sfidencio.teste.hbase.api.controller.dto;

public record OfertaHbaseResponse(String cpf, String dataAnalise, String dataAnaliseOnline, String limiteCredito,
                                  String status, String contratado, String resultadoAnalise, String tipoCliente) {
}
