package com.github.sfidencio.exemplo1.proposta1;

public class CalculadoraTeste {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        imprimeResultado(calculadora.calcular(CalculadoraStrategy.DIVISAO, 9, 8));
        imprimeResultado(calculadora.calcular(CalculadoraStrategy.SOMA, 9, 8));
        imprimeResultado(calculadora.calcular(CalculadoraStrategy.SUBTRACAO, 9, 8));
        imprimeResultado(calculadora.calcular(CalculadoraStrategy.MULTIPLICACAO, 9, 8));
    }

    static void imprimeResultado(double resultado) {
        System.out.println(resultado);
    }
}
