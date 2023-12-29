package com.github.sfidencio.exemplo1.interfacesegregation.new2;

public class ICMS implements TributoICMS {

    @Override
    public double calcular(double baseCalculo, double aliquota) {
        return baseCalculo * aliquota;
    }
}