package com.github.sfidencio.exemplo1.interfacesegregation.new2;

public class TabelaFaixa3 implements TabelaImpostoRendaHandler {

    private TabelaImpostoRendaHandler proximo;

    public TabelaFaixa3(TabelaImpostoRendaHandler proximo) {
        this.proximo = proximo;
    }

    @Override
    public double encontrarAliquota(double rendaTributavel) {
        return 0.27;
    }
}