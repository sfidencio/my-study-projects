package com.github.sfidencio.exemplo1.liskov;

public class TSU extends Imposto {

    public TSU(double valorBaseCalculo) {
        super(valorBaseCalculo);
    }

    @Override
    public double calcular() {
        throw new RuntimeException("Não é possível calcular TSU devido o mesmo não ser um imposto.");
    }
}
