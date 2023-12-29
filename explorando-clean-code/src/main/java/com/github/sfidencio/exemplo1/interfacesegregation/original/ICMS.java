package com.github.sfidencio.exemplo1.interfacesegregation.original;

public class ICMS implements Tributo {

    @Override
    public double calcular(double rendaTributavel, double parcelaADeduzir, double deducoes) {
        return 0;
    }

    @Override
    public double calcular(double baseCalculo, double aliquota) {
        return baseCalculo * aliquota;
    }

}