package com.github.sfidencio.vendas.api.controller.imp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegexTest {


    @Test
    void deveria_nao_aceitar_nome_que_inicia_com_numero() {
        final var pattern = Pattern.compile("^[a-zA-Z\\s]+");
    }

}
