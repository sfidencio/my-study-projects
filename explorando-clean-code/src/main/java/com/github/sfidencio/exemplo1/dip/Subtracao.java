package com.github.sfidencio.exemplo1.dip;

public final class Subtracao implements Calculadora {
    @Override
    public double calcular(double num1, double num2) {
        return num1 - num2;
    }
}
