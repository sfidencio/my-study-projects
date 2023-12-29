package com.github.sfidencio.exemplo1.liskov;

public class ICMS extends Imposto {

    private final double PERCENTUAL_ALIQUOTA = 0.27;

    public ICMS(double valorBaseCalculo) {
        super(valorBaseCalculo);
    }

    @Override
    public double calcular() {
        return getValorBaseCalculo() * PERCENTUAL_ALIQUOTA;
    }
}
