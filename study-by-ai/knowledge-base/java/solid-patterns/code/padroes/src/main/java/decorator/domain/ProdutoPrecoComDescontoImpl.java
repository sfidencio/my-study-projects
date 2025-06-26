package decorator.domain;

import decorator.Produto;

public class ProdutoPrecoComDescontoImpl implements Produto {

    private Produto produto;

    public ProdutoPrecoComDescontoImpl(Produto produto) {
        this.produto = produto;
    }

    @Override
    public double getPreco() {
        return this.produto.getPreco() * 0.8; // Aplicando 20% de desconto
    }

    @Override
    public void setPreco(double preco) {
        this.produto.setPreco(preco);
    }
}
