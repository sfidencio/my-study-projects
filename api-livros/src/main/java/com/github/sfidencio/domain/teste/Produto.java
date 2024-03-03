package com.github.sfidencio.domain.teste;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Produto {

    private Long id;
    private String descricao;
    private BigDecimal precoUnitario;
    private BigDecimal estoque;

}

class Teste {
    public static void main(String[] args) {
        Produto produto = new Produto(1L, "Produto 1", new BigDecimal(10), new BigDecimal(100));
        System.out.println(produto);
        // Produto [descricao=Produto 1, estoque=100, id=1, precoUnitario=10] -> na
        // unhada
        // Produto(id=1, descricao=Produto 1, precoUnitario=10, estoque=100) -> lombok

        // Vamos simplificar a criação de objeto do tipo Produto.class
        Produto produtoNovo = Produto.builder()
                .id(1L)
                .descricao("Arroz")
                .precoUnitario(new BigDecimal(10.20))
                .estoque(new BigDecimal(100))
                .build();
                //Produto(id=1, descricao=Produto 1, precoUnitario=10, estoque=100)
                //Saida build() ->
                //Produto(id=1, descricao=Arroz, precoUnitario=10.199999999999999289457264239899814128875732421875, estoque=100)

        System.out.println(produtoNovo);

    }
}
