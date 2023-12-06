package com.github.sfidencio.estudo.inicial.configuration.exemple3;


import org.springframework.stereotype.Component;

@Component("acabado")
public class ProdutoAcabadoImp implements Produto {


    @Override
    public void salvar() {
        System.out.println("Salvando produto acabado");
    }
}
