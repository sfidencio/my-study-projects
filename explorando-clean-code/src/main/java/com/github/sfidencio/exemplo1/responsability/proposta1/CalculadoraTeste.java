package com.github.sfidencio.exemplo1.proposta1;

public class CalculadoraTeste {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        imprimirResultado(calculadora.calcular(CalculadoraStrategy.DIVISAO, 9, 8));
        imprimirResultado(calculadora.calcular(CalculadoraStrategy.SOMA, 9, 8));
        imprimirResultado(calculadora.calcular(CalculadoraStrategy.SUBTRACAO, 9, 8));
        imprimirResultado(calculadora.calcular(CalculadoraStrategy.MULTIPLICACAO, 9, 8));
    }

    static void imprimirResultado(double resultado) {
        System.out.println(resultado);
    }
}
