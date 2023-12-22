package com.github.sfidencio.exemplo1.responsability;

public class CalculadoraTeste {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        System.out.println(calculadora.calcular("+", 3, 9));
        System.out.println(calculadora.calcular("-", 4, 2));
        System.out.println(calculadora.calcular("*", 9, 2));
    }
}
