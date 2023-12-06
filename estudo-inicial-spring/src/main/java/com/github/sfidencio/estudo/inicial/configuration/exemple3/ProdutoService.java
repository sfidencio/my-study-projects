package com.github.sfidencio.estudo.inicial.configuration.exemple3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    @Qualifier("producao")
    private Produto produto;


    public ProdutoService(Produto produto) {
        this.produto = produto;
    }

    public void salvar() {
        this.produto.salvar();
    }

}
