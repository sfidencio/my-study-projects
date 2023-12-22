package com.github.sfidencio.exemplo1.openclosed;

public sealed interface Calculadora permits Soma, Subtracao, Multiplicacao, Divisao {

        double calcular(double num1, double num2);
}
