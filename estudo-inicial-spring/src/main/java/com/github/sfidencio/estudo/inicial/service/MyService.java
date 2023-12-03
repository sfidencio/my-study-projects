package com.github.sfidencio.estudo.inicial.service;

import com.github.sfidencio.estudo.inicial.model.MyModel;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void salvar(MyModel model) {
        System.out.println("Salvando...");
    }
}
