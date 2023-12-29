package com.github.sfidencio.exemplo1.liskov;

public class ExecutaTesteCalculo {
    public static void main(String[] args) {
        CalculoImposto calculoImposto = new CalculoImposto();
        System.out.println("ICMS: " + calculoImposto.calcular(new ICMS(2200.30)));
        System.out.println("TSU: " + calculoImposto.calcular(new TSU(700.00)));
    }
}
