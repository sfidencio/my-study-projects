package com.github.sfidencio.exemplo1.dip;

import com.github.sfidencio.exemplo1.dip.db.DatabaseFake;

public class CalculadoraTeste {
    public static void main(String[] args) {
        CalculadoraService calculadora = new CalculadoraService(new DatabaseFake());
        imprimirResultado(calculadora.calcular(new Divisao(), 9, 8));
        imprimirResultado(calculadora.calcular(new Soma(), 9, 8));
        imprimirResultado(calculadora.calcular(new Subtracao(), 9, 8));
        imprimirResultado(calculadora.calcular(new Multiplicacao(), 9, 8));
    }

    static void imprimirResultado(double resultado) {
        System.out.println(resultado);
    }
}
