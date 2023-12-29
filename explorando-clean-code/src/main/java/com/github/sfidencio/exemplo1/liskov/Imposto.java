package com.github.sfidencio.exemplo1.liskov;

public abstract class Imposto {

    public abstract double calcular();

    private double valorBaseCalculo;

    public Imposto(double valorBaseCalculo) {
        this.valorBaseCalculo = valorBaseCalculo;
    }

    protected double getValorBaseCalculo() {
        return valorBaseCalculo;
    }
}
