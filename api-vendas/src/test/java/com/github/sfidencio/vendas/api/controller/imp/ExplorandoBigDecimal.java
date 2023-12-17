package com.github.sfidencio.vendas.api.controller.imp;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExplorandoBigDecimal {


    public static void main(String[] args) {
        //BigDecimal - precisao arbitraria
        //Criando objeto de várias formas
        final var valor1 = new BigDecimal(10);
        final var valor2 = BigDecimal.valueOf(50);  //apartir de um long
        final var valor3 = new BigDecimal("10"); //apartir de uma string
        final var valor4 = BigDecimal.ZERO; //inicio com 0
        final var valor5 = BigDecimal.ONE;//inicia com 1
        final var valor6 = BigDecimal.TEN;//inicia com 10
        final var valor7 = BigDecimal.valueOf(10.5); //apartir de um double

        //Soma
        final var valor8 = valor1.add(valor2);
        System.out.printf("Valor 8: %s\n", valor8);
        //Subtracao
        final var valor9 = valor2.subtract(valor1);
        System.out.printf("Valor 9: %s\n", valor9);

        //Quando resultado for negativo, o sinal fica no valor, posso utilizar a funcao abs para pegar o valor absoluto
        final var valor10 = valor1.subtract(valor2);
        System.out.printf("Valor 10 - original: %s\n", valor10);
        System.out.printf("Valor 10 - absoluto: %s\n", valor10.abs());

        //Multiplicacao
        final var valor11 = valor1.multiply(valor2);
        System.out.printf("Valor 11: %s\n", valor11);

        //Divisao
        final var valor12 = valor1.divide(valor2);
        System.out.printf("Valor 12: %s\n", valor12);

        //Divisao com precisao
        final var d1 = new BigDecimal("10");
        final var d2 = new BigDecimal("3");
        //Vou comentar o codigo pra nao dar erro, basta decomentar abaixo e testar.
        //final var valor13 = d1.divide(d2);
        //aqui vamos receber um erro, pois o resultado da divisao nao e exato
        //Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        //System.out.printf("Valor 13: %s\n", valor13);

        /*Precisamos definir a precisao
         Scale aqui é 2, ou seja, 2 casas decimais, ou seja, o numero que sera considerado para arredondar, vai ser o numero que esta na terceira casa decimal, ou seja o ponto de corte.
         Entenda os modos de arredondamento no link abaixo
         https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html
         Poderiamos utilizar o setScale() para definir a precisao, mas o ideal e utilizar o divide() que ja faz isso

             Aqui estão as opções do enum RoundingMode e o que cada uma significa:
             UP (para cima): O valor é arredondado para o próximo número inteiro mais próximo, sempre arredondando para cima, independentemente do valor após a casa decimal.
             DOWN (para baixo): O valor é arredondado para o próximo número inteiro mais próximo, sempre arredondando para baixo, independentemente do valor após a casa decimal.
             CEILING (teto): O valor é arredondado para o próximo número inteiro mais próximo, arredondando para cima se for positivo e para baixo se for negativo.
             FLOOR (piso): O valor é arredondado para o próximo número inteiro mais próximo, arredondando para baixo se for positivo e para cima se for negativo.
             HALF_UP (meio para cima): O valor é arredondado para o próximo número inteiro mais próximo, arredondando para cima se o dígito após a casa decimal for 5 ou mais.
             HALF_DOWN (meio para baixo): HALF_DOWN é semelhante a HALF_UP, exceto que arredonda para baixo se o dígito após a casa decimal for 5 ou mais.
             HALF_EVEN (meio para o mais próximo par): HALF_EVEN é semelhante a HALF_UP, exceto que, se o dígito após a casa decimal for 5, ele arredonda para o próximo número inteiro mais próximo, arredondando para cima se o dígito após a casa decimal for ímpar e para baixo se for par.
             UNNECESSARY (desnecessário): Uma exceção é lançada se a precisão for perdida ao arredondar o valor.

        */

        //Vamos exemplificar os arredondamentos, lembrem-se que o digito a ser considerado aqui apos a casa decimal e o terceiro, ou seja, o ponto de corte
        //Pois definimos a escalada como 2, ou seja, 2 casas decimais, logo o terceiro digito é o ponto de corte ou o digito que sera considerado para arredondar

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //UP
        var valor14 = new BigDecimal("1.142857");
        System.out.printf("Valor 14: %s\n", valor14.setScale(2, RoundingMode.UP));

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //DOWN
        var valor15 = new BigDecimal("1.142857");
        System.out.printf("Valor 15: %s\n", valor15.setScale(2, RoundingMode.DOWN));

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //CEILING(teto) (se negativo arredonda para baixo ou se positivo arredonda para cima)
        var valor16 = new BigDecimal("-1.142857");
        var valor17 = new BigDecimal("1.142857");
        System.out.printf("Valor 16: %s\n", valor16.setScale(2, RoundingMode.CEILING));
        System.out.printf("Valor 17: %s\n", valor17.setScale(2, RoundingMode.CEILING));

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //FLOOR(piso) (se negativo arredonda para cima ou se positivo arredonda para baixo)
        var valor18 = new BigDecimal("-1.142857");
        var valor19 = new BigDecimal("1.142857");
        System.out.printf("Valor 18: %s\n", valor18.setScale(2, RoundingMode.FLOOR));
        System.out.printf("Valor 19: %s\n", valor19.setScale(2, RoundingMode.FLOOR));

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //HALF_UP (meio para cima): O valor é arredondado para o próximo número inteiro mais próximo, arredondando para cima se o dígito após a casa decimal for 5 ou mais.
        var valor20 = new BigDecimal("1.142857");
        var valor21 = new BigDecimal("1.145857");
        var valor22 = new BigDecimal("1.146857");
        System.out.printf("Valor 20: %s\n", valor20.setScale(2, RoundingMode.HALF_UP));
        System.out.printf("Valor 21: %s\n", valor21.setScale(2, RoundingMode.HALF_UP));
        System.out.printf("Valor 22: %s\n", valor22.setScale(2, RoundingMode.HALF_UP));

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //HALF_DOWN (meio para baixo): HALF_DOWN é semelhante a HALF_UP, exceto que arredonda para baixo se o dígito a ser cortado for menos que 5
        var valor23 = new BigDecimal("1.142857");
        var valor24 = new BigDecimal("1.145857");
        var valor25 = new BigDecimal("1.146857");
        System.out.printf("Valor 23: %s\n", valor23.setScale(2, RoundingMode.HALF_DOWN));
        System.out.printf("Valor 24: %s\n", valor24.setScale(2, RoundingMode.HALF_DOWN));
        System.out.printf("Valor 25: %s\n", valor25.setScale(2, RoundingMode.HALF_DOWN));

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //HALF_EVEN (meio para o mais próximo par): HALF_EVEN arrredonda para o mais próximo par, arredondando para cima se o dígito após a casa decimal for ímpar e para baixo se for par.


        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //exemplos de codigo com HALF_EVEN
        var valor32 = new BigDecimal("1.140");
        var valor33 = new BigDecimal("1.141");
        var valor34 = new BigDecimal("1.142");
        var valor35 = new BigDecimal("1.143");
        var valor36 = new BigDecimal("1.144");
        var valor37 = new BigDecimal("1.145");
        var valor38 = new BigDecimal("1.146");
        var valor39 = new BigDecimal("1.147");
        var valor40 = new BigDecimal("1.148");
        var valor41 = new BigDecimal("1.149");
        System.out.printf("Valor 32: %s\n", valor32.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 33: %s\n", valor33.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 34: %s\n", valor34.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 35: %s\n", valor35.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 36: %s\n", valor36.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 37: %s\n", valor37.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 38: %s\n", valor38.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 39: %s\n", valor39.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 40: %s\n", valor40.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 41: %s\n", valor41.setScale(2,RoundingMode.HALF_EVEN));

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        var valor42 = new BigDecimal("1.150");
        var valor43 = new BigDecimal("1.151");
        var valor44 = new BigDecimal("1.152");
        var valor45 = new BigDecimal("1.153");
        var valor46 = new BigDecimal("1.154");
        var valor47 = new BigDecimal("1.155");
        var valor48 = new BigDecimal("1.156");
        var valor49 = new BigDecimal("1.157");
        var valor50 = new BigDecimal("1.158");
        var valor51 = new BigDecimal("1.159");
        System.out.printf("Valor 42: %s\n", valor42.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 43: %s\n", valor43.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 44: %s\n", valor44.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 45: %s\n", valor45.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 46: %s\n", valor46.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 47: %s\n", valor47.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 48: %s\n", valor48.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 49: %s\n", valor49.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 50: %s\n", valor50.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 51: %s\n", valor51.setScale(2,RoundingMode.HALF_EVEN));

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        var valor52 = new BigDecimal("1.260");
        var valor53 = new BigDecimal("1.261");
        var valor54 = new BigDecimal("1.262");
        var valor55 = new BigDecimal("1.263");
        var valor56 = new BigDecimal("1.264");
        var valor57 = new BigDecimal("1.265");
        var valor58 = new BigDecimal("1.266");
        var valor59 = new BigDecimal("1.267");
        var valor60 = new BigDecimal("1.268");
        var valor61 = new BigDecimal("1.269");
        System.out.printf("Valor 52: %s\n", valor52.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 53: %s\n", valor53.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 54: %s\n", valor54.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 55: %s\n", valor55.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 56: %s\n", valor56.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 57: %s\n", valor57.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 58: %s\n", valor58.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 59: %s\n", valor59.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 60: %s\n", valor60.setScale(2,RoundingMode.HALF_EVEN));
        System.out.printf("Valor 61: %s\n", valor61.setScale(2,RoundingMode.HALF_EVEN));

        System.out.println("------------------------------------------------------------------------------------------------------------------");

        //UNNECESSARY (desnecessário): Uma exceção é lançada se a precisão for perdida ao arredondar o valor.
        //Vamos exemplificar
        //var valor62 = new BigDecimal("1.264");
        //System.out.printf("Valor 62: %s\n", valor62.setScale(2,RoundingMode.UNNECESSARY));




    }

}
