package br.com.sfidencio.minhaprimeiraapirestfull;

import lombok.*;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produto {
    private UUID id;
    private String descricao;
    private double estoque;
}
