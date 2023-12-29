package com.github.sfidencio.exemplo1.interfacesegregation.original;

public interface Tributo extends TabelaImpostoRenda {
    double calcular(double rendaTributavel, double parcelaADeduzir, double deducoes);
    double calcular(double baseCalculo , double aliquota);
}
