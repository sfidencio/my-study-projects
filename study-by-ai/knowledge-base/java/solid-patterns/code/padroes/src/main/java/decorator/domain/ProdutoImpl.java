package decorator.domain;

import decorator.Produto;

public class ProdutoImpl implements Produto {

    private double preco;


    @Override
    public double getPreco() {
        return this.preco;
    }

    @Override
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
