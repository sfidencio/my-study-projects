package com.github.sfidencio.exemplo1.liskov;

public class CalculoImposto {
    public double calcular(Imposto imposto) {
        return imposto.calcular();
    }
}
