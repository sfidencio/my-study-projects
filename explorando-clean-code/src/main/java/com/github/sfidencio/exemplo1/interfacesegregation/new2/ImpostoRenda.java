package com.github.sfidencio.exemplo1.interfacesegregation.new2;

public class ImpostoRenda implements TributoImpostoRenda {


    private TabelaImpostoRendaHandler tabelaImpostoRendaHandler;

    public ImpostoRenda(TabelaImpostoRendaHandler tabelaImpostoRendaHandler) {
        this.tabelaImpostoRendaHandler = tabelaImpostoRendaHandler;
    }

    @Override
    public double calcular(double rendaTributavel, double parcelaADeduzir, double deducoes) {
        double aliquota = this.tabelaImpostoRendaHandler.encontrarAliquota(rendaTributavel);
        if (aliquota == 0.0)
            throw new RuntimeException("Imposto de Renda isento");
        return rendaTributavel * aliquota - parcelaADeduzir - deducoes;
    }

}
