package com.github.sfidencio.estudo.inicial.configuration.exemple3;


import org.springframework.stereotype.Component;

@Component("producao")
public class ProdutoProducao implements Produto {


    @Override
    public void salvar() {
        System.out.println("Salvando produto producao");
    }
}
