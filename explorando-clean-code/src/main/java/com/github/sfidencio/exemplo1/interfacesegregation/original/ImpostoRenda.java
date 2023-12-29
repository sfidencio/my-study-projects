package com.github.sfidencio.exemplo1.interfacesegregation.original;

public class ImpostoRenda implements Tributo {


    @Override
    public double calcular(double rendaTributavel, double parcelaADeduzir, double deducoes) {
        double aliquota = getAliquota(rendaTributavel);
        if (aliquota == 0.0)
            throw new RuntimeException("Imposto de Renda isento");
        return rendaTributavel * getAliquota(rendaTributavel) - parcelaADeduzir - deducoes;
    }

    @Override
    public double calcular(double baseCalculo, double aliquota) {
        return 0;
    }
}
