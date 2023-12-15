package com.github.sfidencio.vendas.api.controller.imp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegexTest {


    /**
     * Desmontando a Regex
     * [a-zA-Z\s] - aceita letras e espacos
     * ^ - inicio da string
     * + - uma ou mais ocorrencias
     * $ - fim da string
     * \s - espaco
     * [ - inicio de um grupo
     * ] - fim de um grupo
     * a-zA-Z - aceita letras de a-z e A-Z
     */
    @Test
    void deveria_nao_aceitar_nome_que_inicia_com_numero() {
        final var pattern = Pattern.compile("^[a-zA-Z\\s]+");
        final var matcher = pattern.matcher("1Fulano");
        Assertions.assertFalse(matcher.matches());
    }

    /**
     * Desmontando a Regex
     * [a-zA-Z\s] - aceita letras e espacos
     * ^ - inicio da string
     * + - uma ou mais ocorrencias
     * $ - fim da string
     * \s - espaco
     * [ - inicio de um grupo
     * ] - fim de um grupo
     * a-zA-Z - aceita letras de a-z e A-Z
     */
    @Test
    void deveria_aceitar_nome_que_inicia_com_espaco() {
        //regex exceto letras e espacos
        //final var pattern = Pattern.compile("^[^a-zA-Z\\s]+");
        final var pattern = Pattern.compile("^[a-zA-Z\\s]+");
        final var matcher = pattern.matcher(" Fidencio");
        Assertions.assertTrue(matcher.matches());
    }

    /**
     * Desmontando a Regex
     * ^[bB][a-zA-Z\\s]+ - aceita letras e espacos e permite somente que inicie com a letra b ou B
     * ^ - inicio da string
     * [bB] - aceita somente a letra b ou B
     * + - uma ou mais ocorrencias
     * $ - fim da string
     * \s - espaco
     * [ - inicio de um grupo
     * ] - fim de um grupo
     * a-zA-Z - aceita letras de a-z e A-Z
     */
    @Test
    void deveria_aceitar_nome_que_inicia_apenas_com_letra_b() {
        //inicia com a letra b apenas
        //final pattern = Pattern.compile("^[bB][a-zA-Z\\s]+");
        final var pattern = Pattern.compile("^[bB][a-zA-Z\\s]+");
        final var matcher = pattern.matcher("Bulano");
        //Assertions.assertFalse(matcher.matches());
        Assertions.assertTrue(matcher.matches());
    }

    /*
     * Desmontando a Regex
     * ^[a-zA-Z\\s]+x[a-zA-Z\\s]+ - aceita letras e espacos e permite somente que tenha a letra x
     * ^ - inicio da string
     * [a-zA-Z\\s]+ - aceita letras e espacos
     * +x - aceita somente a letra x
     * [a-zA-Z\\s]+ - aceita letras e espacos
     * $ - fim da string
     */
    @Test
    void deveria_aceitar_nome_que_penultima_letra_seja_x() {
        //aceiar somente nome que a penultima letra seja x
        //final var pattern = Pattern.compile("^[a-zA-Z\\s]+x[a-zA-Z\\s]+");
        final var pattern = Pattern.compile("^[a-zA-Z\\s]+x[a-zA-Z\\s]+");
        final var matcher = pattern.matcher("Axulano");
        Assertions.assertTrue(matcher.matches());
    }

    /*
     * Desmontando a Regex
     * ^[a-zA-Z\s]+[aA]$ - aceita letras e espacos e permite somente que termine com a letra a ou A
     * ^ - inicio da string
     * [a-zA-Z\s]+ - aceita letras e espacos
     * [aA] - aceita somente a letra a ou A
     * $ - fim da string
     */

    @Test
    void deveria_aceitar_nome_que_terminasse_com_a_letra_a() {
        //final pattern = Pattern.compile("^[a-zA-Z\\s]+[aA]$");
        final var pattern = Pattern.compile("^[a-zA-Z\\s]+[aA]$");
        final var matcher = pattern.matcher("Fulana");
        Assertions.assertTrue(matcher.matches());
    }

    /*
     * Desmontando a Regex de Email
     * ^[^0-9|^_][a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$
     * ^ - inicio da string
     * [^0-9|^_] - nao aceita numeros e nem o caracter _
     * [a-zA-Z0-9_.-]+ - aceita letras, numeros, _ , . e -
     * @ - aceita somente o caracter @
     * [a-zA-Z0-9-]+ - aceita letras, numeros e -
     * . - aceita somente o caracter .
     * [a-zA-Z0-9-.]+ - aceita letras, numeros, . e -
     * $ - fim da string
     */

    @Test
    void deveria_nao_aceitar_email_que_inicia_com_digito_ou_underscore() {
        final var pattern = Pattern.compile("^[^0-9|^_][a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
        final var matcher = pattern.matcher("_fulano@gmail.com");
        Assertions.assertFalse(matcher.matches());
    }

    @Test
    void deveria_nao_aceitar_cadeia_de_caracteres_com_tres_digitos_iniciais_quarto_digito_letra_qualquer_elemento() {
        //final var pattern = Pattern.compile("^[0-9]{3}[a-zA-Z]{1}.*");
        final var pattern = "^[0-9]{3}";
        final var patternObject = Pattern.compile(pattern);
        Assertions.assertFalse(patternObject.matcher("1X2Joao").matches());
    }

}
