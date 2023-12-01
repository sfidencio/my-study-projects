package com.github.sfidencio.vendas.api.dto;

import java.util.List;

//https://www.baeldung.com/spring-date-parameters
public record PedidoRequest(Integer id,
                            String descricao,
                            ClienteRequest cliente,
                            List<ItemPedidoRequest> itens) {
}


/*
public record   PedidoRequest(Integer id,
                              String descricao,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @JsonSerialize(using = LocalDateSerializer.class) @JsonDeserialize(using = LocalDateDeserializer.class) @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo") LocalDate dataPedido,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @JsonSerialize(using = LocalTimeSerializer.class) @JsonDeserialize(using = LocalTimeDeserializer.class) @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo") LocalDateTime horaPedido,
                              ClienteRequest cliente,
                              BigDecimal total,
                              List<ItemPedidoRequest> itens) {
}
*/
