package com.github.sfidencio.estudo.inicial.configuration.exemple3;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("normal")
public class ProdutoImp implements Produto {

    @Override
    public void salvar() {
        System.out.println("Salvando produto imp");
    }
}
