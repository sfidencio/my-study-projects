package decorator;

import decorator.domain.ProdutoImpl;
import decorator.domain.ProdutoPrecoComDescontoImpl;

public class Application {
    public static void main(String[] args) {
        Produto produto = new ProdutoImpl();
        produto.setPreco(220.89);
        System.out.println("Preço do produto: " + produto.getPreco());
        Produto produtoComDesconto = new ProdutoPrecoComDescontoImpl(produto);
        System.out.println("Preço do produto com desconto: " + produtoComDesconto.getPreco());
    }
}
